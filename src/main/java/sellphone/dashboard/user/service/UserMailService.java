package sellphone.dashboard.user.service;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
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
    
    String endpointUrl = "http://localhost:8081/sellphone";
    
	public String sendConfirmAccountEmail(Users user) {
		try {
			String resetLink = endpointUrl + "/confirmAccount?userId=" + user.getUserId(); 

			SimpleMailMessage msg = new SimpleMailMessage();
			msg.setFrom("eeit183test@gmail.com");
			System.out.println(user.getEmail());
			msg.setTo(user.getEmail());

			msg.setSubject("Sellphone會員中心 帳戶啟用通知");
			msg.setText("您好： \n\n" + "請點選以下連結進行帳戶啟用：\n\n" + resetLink + "\n\n" + "sellphone 感謝您的註冊 \n\n"
					+ "Best Regards, \n " + "Sellphone cooperation");
			System.out.println("before send");
			mailSender.send(msg);

			return "success";
		} catch (Exception e) {
			e.printStackTrace();
			return "error";
		}

	}
    
    
	public String sendResetPasswordEmail(Users user) {
		try {
			
			String token = generateResetToken(user);
			String resetLink = endpointUrl + "/resetPassword/" + token; 
			
			SimpleMailMessage msg = new SimpleMailMessage();
			msg.setFrom("eeit183test@gmail.com");
			System.out.println(user.getEmail());
			msg.setTo(user.getEmail());

			msg.setSubject("Sellphone會員中心 重設密碼通知");
			msg.setText("您好 \n\n" + "請點選以下連結進行密碼重設：\n\n" + resetLink + "\n\n" + "*如果您未發送此請求，請立刻檢查您的帳戶狀態 \n\n"
					+ "Best Regards, \n" + "Sellphone cooperation");
			System.out.println("before send");
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
		
		UserPasswordToken userToken= checkToken(user);
		UserPasswordToken token;
		if( userToken != null) {
			userToken.setToken(uuid.toString());
			userToken.setExpiryDateTime(expiryDateTime);
			token = userPasswordTokenRepository.save(userToken);
		}else{
			UserPasswordToken resetToken = new UserPasswordToken();
			resetToken.setToken(uuid.toString());
			resetToken.setUserId(user.getUserId());
			resetToken.setExpiryDateTime(expiryDateTime);
			resetToken.setUser(user);
			token = userPasswordTokenRepository.save(resetToken);
		}

				
		if (token != null) {
			return token.getToken();
		}
		return "";
	}

	public boolean hasExipred(LocalDateTime expiryDateTime) {
		LocalDateTime currentDateTime = LocalDateTime.now();
		return expiryDateTime.isAfter(currentDateTime);
	}
    
    private UserPasswordToken checkToken(Users user) {
		Optional<UserPasswordToken> optional = userPasswordTokenRepository.findById(user.getUserId());

		if (optional.isPresent()) {
			return optional.get();
		} else {
			return null;
		}
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
