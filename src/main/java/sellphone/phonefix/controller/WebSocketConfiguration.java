package sellphone.phonefix.controller;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;
import sellphone.phonefix.controller.*;

@Configuration
public class WebSocketConfiguration {

    @Bean  
    public ServerEndpointExporter serverEndpointExporter (){
        
        ServerEndpointExporter exporter = new ServerEndpointExporter();
        
        // 註冊 WebSocket 的端點
        exporter.setAnnotatedEndpointClasses(EchoChannel.class);
        
        return exporter;
    }  }  
    