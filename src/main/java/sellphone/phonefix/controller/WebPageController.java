package sellphone.phonefix.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WebPageController {

    @GetMapping("/phonefix")
    public String getWebSocketPage() {
        return "phonefix/WebSocket"; // 返回 templates/phonefix/WebSocket.html 
    }
    
    @GetMapping("/client")
    public String getClientPage() {
        return "phonefix/client"; // 對應 templates/phonefix/client.html
    }

    @GetMapping("/admin")
    public String getAdminPage() {
        return "phonefix/admin"; // 對應 templates/phonefix/admin.html
    }
}
