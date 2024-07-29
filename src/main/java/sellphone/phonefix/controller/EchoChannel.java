package sellphone.phonefix.controller;

import java.io.IOException;
import java.time.Instant;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import jakarta.websocket.CloseReason;
import jakarta.websocket.EndpointConfig;
import jakarta.websocket.OnClose;
import jakarta.websocket.OnError;
import jakarta.websocket.OnMessage;
import jakarta.websocket.OnOpen;
import jakarta.websocket.Session;
import jakarta.websocket.server.ServerEndpoint;

@ServerEndpoint(value = "/channel/echo")
public class EchoChannel {

    private static final Logger LOGGER = LoggerFactory.getLogger(EchoChannel.class);
    private static final Set<Session> sessions = Collections.synchronizedSet(new HashSet<>());

    @OnMessage
    public void onMessage(String message, Session session) throws IOException {
        LOGGER.info("[websocket] 收到消息：id={}，message={}", session.getId(), message);

        // 广播消息给所有连接的会话
        synchronized (sessions) {
            for (Session s : sessions) {
                if (s.isOpen()) {
                    s.getAsyncRemote().sendText("[" + Instant.now().toEpochMilli() + "] " + message);
                }
            }
        }
    }

    @OnOpen
    public void onOpen(Session session, EndpointConfig endpointConfig) {
        sessions.add(session);
        LOGGER.info("[websocket] 新的連接：id={}", session.getId());
    }

    @OnClose
    public void onClose(Session session, CloseReason closeReason) {
        sessions.remove(session);
        LOGGER.info("[websocket] 連結以斷開：id={}，reason={}", session.getId(), closeReason);
    }

    @OnError
    public void onError(Session session, Throwable throwable) throws IOException {
        sessions.remove(session);
        LOGGER.info("[websocket] 連接有異常：id={}，throwable={}", session.getId(), throwable.getMessage());
        session.close(new CloseReason(CloseReason.CloseCodes.UNEXPECTED_CONDITION, throwable.getMessage()));
    }
}
