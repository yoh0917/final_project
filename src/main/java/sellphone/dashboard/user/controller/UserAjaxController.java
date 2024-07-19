package sellphone.dashboard.user.controller;

import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttribute;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.websocket.Session;
import sellphone.dashboard.user.model.UserRepository;
import sellphone.dashboard.user.model.UserView;
import sellphone.dashboard.user.model.UserViewRepository;
import sellphone.dashboard.user.model.Users;
import sellphone.dashboard.user.service.UserMailService;
import sellphone.dashboard.user.service.UserService;
import sellphone.dashboard.user.service.UserUtil;

@Controller
public class UserAjaxController {

	@Autowired
	private UserService uService;
	
	@Autowired
	private UserMailService userMailService;
	
	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private UserViewRepository userViewRepo;
	
	@Autowired
	private UserUtil userUtil;

//	-------------------------------------- UserInfo-controller ----------------------------------------------------		
	@PostMapping("/UserEmailEdit")
	@ResponseBody
	public String userEmailEdit(@RequestBody Map<String, String> map, @SessionAttribute("userId") String userId , Model m) {
		Users user = uService.findById(userId);
		user.setEmail(map.get("email"));
		uService.update(user);
		
		return user.getEmail();
	}
	
	@PostMapping("/UserContactNumEdit")
	@ResponseBody
	public String userContactNumEdit(@RequestBody Map<String, String> map, @SessionAttribute("userId") String userId , Model m) {
		Users user = uService.findById(userId);
		user.setContactNum(map.get("contactNum"));
		uService.update(user);
		
		return user.getContactNum();
	}

	
//	--------------------------------------  Registration-related controller ----------------------------------------------------		
	@PostMapping("/CheckRegist")
	@ResponseBody
	public String checkRegist(@RequestBody Users user,HttpServletRequest req) throws ServletException, IOException {

		HttpSession session = req.getSession();
		int status = 0;
		String userId = userUtil.createUserId(session.getId());
		user.setUserId(userId);
		user.setStatus(status);
		user.setCreateTime(LocalDateTime.now());
		uService.insert(user);
		
		userMailService.sendConfirmAccountEmail(user);
		
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
			return "";			
		}
		
	}
	@GetMapping("/checkUserAccount")
	@ResponseBody
	public String checkUserAccount(@RequestParam("param") String userAccount) {
		
		Users user = userRepo.findByUserAccount(userAccount);
		if (user != null)
			return "此帳號已經存在";
		else {
			return "";			
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

        Users user = userRepo.findByEmail(email);
        if(user != null) {
        	return"此email已經存在";
        }
        
        
        if (email != null &&
            email.matches(emailRegex) &&
            email.matches(noSpaceRegex) &&
            email.matches(singleAtRegex)
            ) {
        	return "";
        } else {
        	return "請輸入正確email";
        }
       		
	}

	//	--------------------------------------  DashBoard-related controller ----------------------------------------------------	
	
	@GetMapping("/findUserViewByUserId")
	@ResponseBody
	public UserView findUserViewByUserId(@RequestParam("userId") String userId ) throws JsonProcessingException {
		
		UserView userView = userViewRepo.findByUserId(userId);
		
		return userView;
	}	
	
	@GetMapping("/UserBlockStatus")
	public void userBlockStatus(@RequestParam("userId") String userId, Model m, HttpServletRequest req,
			HttpServletResponse resp) throws ServletException, IOException {
		Users user = uService.findById(userId);

		int status = user.getStatus();
		if (status != -1) {
			user.setStatus(-1);
		} else if (status == -1) {
			user.setStatus(1);
		}

		uService.update(user);
		resp.setStatus(HttpServletResponse.SC_OK);

	}


	@GetMapping("/UserDelete")
	public void userDelete(@RequestParam("userId") String userId, Model m, HttpServletRequest req,
			HttpServletResponse resp) {

		uService.delete(userId);
		resp.setStatus(HttpServletResponse.SC_OK);
	}

}
