package sellphone.phoneplan.model;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.*;
import java.net.*;
import java.nio.charset.StandardCharsets;

@Service
public class SmsService {

    @Value("${sms.username}")
    private String username;

    @Value("${sms.password}")
    private String password;

    public boolean sendSMS(String mobile, String message) {
        try {
            StringBuilder msgData = new StringBuilder();
            msgData.append("username=").append(username);
            msgData.append("&password=").append(password);
            msgData.append("&mobile=").append(mobile);
            msgData.append("&message=").append(URLEncoder.encode(message, StandardCharsets.UTF_8.toString()));
            return sendToGateway(msgData.toString());
        } catch (Exception e) {
            System.out.println("程式錯誤: " + e.getMessage());
            return false;
        }
    }

    private boolean sendToGateway(String postData) {
        HttpURLConnection connection = null;
        try {
            URL url = new URL("http://api.twsms.com/json/sms_send.php");
            connection = (HttpURLConnection) url.openConnection();
            connection.setDoInput(true);
            connection.setDoOutput(true);
            connection.setUseCaches(false);
            connection.setRequestMethod("POST");

            try (DataOutputStream dos = new DataOutputStream(connection.getOutputStream())) {
                dos.writeBytes(postData);
                dos.flush();
            }

            try (BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
                StringBuilder response = new StringBuilder();
                String line;
                while ((line = br.readLine()) != null) {
                    response.append(line);
                }
                System.out.println("回傳碼: " + response);
            }
            return true;
        } catch (Exception e) {
            System.out.println("無法連接 TwSMS API Server: " + e.getMessage());
            return false;
        } finally {
            if (connection != null) {
                connection.disconnect();
            }
        }
    }
}
