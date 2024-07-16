package sellphone.dashboard.user.service;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import sellphone.dashboard.user.model.UserPasswordToken;
import sellphone.dashboard.user.model.UserPasswordTokenRepository;
import sellphone.dashboard.user.model.Users;

@Service
public class UserMailService {

    @Autowired
    private JavaMailSender mailSender;
    
    @Autowired
    private UserPasswordTokenRepository userPasswordTokenRepository;
    
	public String sendEmail(Users user) {
		try {
			String resetLink = generateResetToken(user);

			SimpleMailMessage msg = new SimpleMailMessage();
			msg.setFrom("");// input the senders email ID
			msg.setTo(user.getEmail());

			msg.setSubject("Welcome To My Channel");
			msg.setText("Hello \n\n" + "Please click on this link to Reset your Password :" + resetLink + ". \n\n"
					+ "Regards \n" + "ABC");

			mailSender.send(msg);

			return "success";
		} catch (Exception e) {
			e.printStackTrace();
			return "error";
		}

	}


	public String generateResetToken(Users user) {
		UUID uuid = UUID.randomUUID();
		LocalDateTime currentDateTime = LocalDateTime.now();
		LocalDateTime expiryDateTime = currentDateTime.plusMinutes(5);
		UserPasswordToken resetToken = new UserPasswordToken();
		resetToken.setUser(user);
		resetToken.setToken(uuid.toString());
		resetToken.setExpiryDateTime(expiryDateTime);
		resetToken.setUser(user);
		System.out.println("before save");
		UserPasswordToken token = userPasswordTokenRepository.save(resetToken);
		System.out.println("after save");
		if (token != null) {
			String endpointUrl = "http://localhost:8081/sellphone/resetPassword";
			return endpointUrl + "/" + resetToken.getToken();
		}
		return "";
	}


	public boolean hasExipred(LocalDateTime expiryDateTime) {
		LocalDateTime currentDateTime = LocalDateTime.now();
		return expiryDateTime.isAfter(currentDateTime);
	}
    
    
//    public void sendPlainTextWithAttachments(Collection<String> receivers, String subject, String content) {
//        SimpleMailMessage message = new SimpleMailMessage();
//        message.setTo(receivers.toArray(new String[0]));
//        message.setSubject(subject);
//        message.setText(content);
//
//        mailSender.send(message);
//    }
//    
//    public void sendSimpleHtml(Collection<String> receivers, String subject, String content) {
//        try {
//            MimeMessage message = mailSender.createMimeMessage();
//            MimeMessageHelper helper = new MimeMessageHelper(message);
//            helper.setTo(receivers.toArray(new String[0]));
//            helper.setSubject(subject);
//            helper.setText(content, true);
//
//            mailSender.send(message);
//        } catch (MessagingException e) {
//            throw new RuntimeException(e);
//        }
//    }
//    
//    @Test
//    public void testSimpleHtml() {
//        sendSimpleHtml(
//                List.of("leo312654@gmail.com"),
//                "Simple html",
//                "<html><body><p>你好！</p><p>My name is <b>Vincent</b>.</p></body></html>"
//        );
//    }
}
