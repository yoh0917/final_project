package sellphone.dashboard.user.service;

import java.util.Collection;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

@Service
public class UserMailService {

    @Autowired
    private JavaMailSender mailSender;
    
    public void sendPlainTextWithAttachments(Collection<String> receivers, String subject, String content) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(receivers.toArray(new String[0]));
        message.setSubject(subject);
        message.setText(content);

        mailSender.send(message);
    }
    
    public void sendSimpleHtml(Collection<String> receivers, String subject, String content) {
        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message);
            helper.setTo(receivers.toArray(new String[0]));
            helper.setSubject(subject);
            helper.setText(content, true);

            mailSender.send(message);
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }
    
    @Test
    public void testSimpleHtml() {
        sendSimpleHtml(
                List.of("leo312654@gmail.com"),
                "Simple html",
                "<html><body><p>你好！</p><p>My name is <b>Vincent</b>.</p></body></html>"
        );
    }
}
