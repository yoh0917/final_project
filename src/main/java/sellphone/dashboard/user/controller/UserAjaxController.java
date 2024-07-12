package sellphone.dashboard.user.controller;

import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import sellphone.dashboard.user.model.UserRepository;
import sellphone.dashboard.user.model.UserView;
import sellphone.dashboard.user.model.UserViewRepository;
import sellphone.dashboard.user.model.Users;
import sellphone.dashboard.user.service.UserService;
import sellphone.dashboard.user.service.UserUtil;

@Controller
public class UserAjaxController {

	@Autowired
	private UserService uService;
	
	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private UserViewRepository userViewRepo;
	
	@Autowired
	private UserUtil userUtil;
	
	
	@PostMapping("/CheckRegist")
	@ResponseBody
	public String checkRegist(@RequestBody Users user,HttpServletRequest req) throws ServletException, IOException {

		HttpSession session = req.getSession();
		int status = 0;
//		String userId = createUserId(requset.getSession().getId().substring(0, 2));
		String userId = userUtil.createUserId(session.getId());
		System.out.println(userId);
		user.setUserId(userId);
//		user.setUserName(userName);
//		user.setUserAccount(userAccount);
//		user.setPassword(password);
//		user.setContactNum(contactNumber);
//		user.setEmail(email);
		user.setStatus(status);
		user.setCreateTime(LocalDateTime.now());

		uService.insert(user);
//		try {
//			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
//
//			LocalDate localDate = LocalDate.parse(birthday, formatter);
//			Date birthDaySqlDate = Date.valueOf(localDate);
//			user.setBirthday(birthDaySqlDate);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}

//		try {
//		} catch (Exception e) {
//			
//			return "redirect:/UserRegistFailed";
//		}
		return user.getUserName();
	}	
	@GetMapping("/checkContactNum")
	@ResponseBody
	public String checkContactNum(@RequestParam("param") String contactNum) {
		
		if ( !contactNum.startsWith("09") || contactNum.length() != 10) {
			return "請輸入正確手機號碼";
		}

		Users user = userRepo.findByContactNum(contactNum);
		if (user != null)
			return "此手機號碼已經存在";
		else {
			return null;			
		}
		
	}
	@GetMapping("/checkUserAccount")
	@ResponseBody
	public String checkUserAccount(@RequestParam("param") String userAccount) {
		
		Users user = userRepo.findByUserAccount(userAccount);
		if (user != null)
			return "此帳號已經存在";
		else {
			return null;			
		}
		
	}
	@GetMapping("/checkEmailValid")
	@ResponseBody
	public String checkEmailFormatValid(@RequestParam("param") String email) {
		 // Email must match this regex pattern
        String emailRegex = "^[a-zA-Z0-9][a-zA-Z0-9._%+-]*@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
        // Email must not contain spaces
        String noSpaceRegex = "^[^\\s]+$";
        // Email must not contain multiple @
        String singleAtRegex = "^[^@]*@[^@]*$";
        // Email must not contain double dots
        String noDoubleDotRegex = "^[^..]*$";

        if (email != null &&
            email.matches(emailRegex) &&
            email.matches(noSpaceRegex) &&
            email.matches(singleAtRegex)
            ) {
        	System.out.println("test");
        	return null;
        } else {
        	System.out.println("tes2");
        	return "請輸入正確email";
        }
       		
	}
	
	@GetMapping("/findUserViewByUserId")
	@ResponseBody
	public UserView findUserViewByUserId(@RequestParam("userId") String userId ) throws JsonProcessingException {
		
		UserView userView = userViewRepo.findByUserId(userId);
		
		
		return userView;
	}
}
