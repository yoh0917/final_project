package sellphone.user.controller;

import java.io.IOException;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import sellphone.admin.service.AdminService;
import sellphone.user.DTO.UserDTO;
import sellphone.user.model.UserPasswordToken;
import sellphone.user.model.UserPasswordTokenRepository;
import sellphone.user.model.UserPhonePlanList;
import sellphone.user.model.UserPhonePlanListRepository;
import sellphone.user.model.UserRepository;
import sellphone.user.model.Users;
import sellphone.user.service.UserMailService;
import sellphone.user.service.UserService;
import sellphone.user.service.UserUtil;

@Controller
public class UserController {

	@Autowired
	private UserService uService;

	@Autowired
	private AdminService adService;

	@Autowired
	private UserMailService uMailService;

	@Autowired
	private UserRepository userRepository ;

	@Autowired
	private UserPhonePlanListRepository userPplR;

	@Autowired
	private UserPasswordTokenRepository userPasswordTokenRepository;

	@GetMapping("/Test")
	@ResponseBody
	public String testEmail() {	
		System.out.println("test");
		return "";
	}

//	--------------------------------------  UserInfo controller ----------------------------------------------------
	@GetMapping("/UserInfoList")
	public String UserPlanInfo(Model m, HttpServletRequest req, HttpServletResponse resp) {

		String userId = (String) req.getSession().getAttribute("userId");
		System.out.println(userId);
		Users user = uService.findById(userId);
		System.out.println(user);
		m.addAttribute("user", user);

		return "/user/fronted/UserInfo";
	}

	@GetMapping("/UserOrderList")
	public String UserOrderList(Model m, HttpServletRequest req, HttpServletResponse resp) {

		return "/user/fronted/UserOrder";
	}

	@GetMapping("/UserPostList")
	public String UserPostList(Model m, HttpServletRequest req, HttpServletResponse resp) {

		return "/user/fronted/UserPost";
	}

	@GetMapping("/UserPlanList")
	public String UserPlanList(Model m, HttpServletRequest req, HttpServletResponse resp) {

		String userId = (String) req.getSession().getAttribute("userId");
		System.out.println(userId);
		List<UserPhonePlanList> userPhonePlanList = userPplR.findAllByuserId(userId);
		m.addAttribute("planList", userPhonePlanList);

		return "/user/fronted/UserPlan";
	}

	@GetMapping("/UserFixList")
	public String UserFixList(Model m, HttpServletRequest req, HttpServletResponse resp) {

		return "/user/fronted/UserFix";
	}

//	--------------------------------------  Login-related controller ----------------------------------------------------

	@PostMapping("/CheckLogin")
	public String checkLogin(@RequestParam("phoneNumber") String account, @RequestParam("password") String pwd, Model m,
			HttpServletRequest req, HttpServletResponse resp) {

		HttpSession session = req.getSession();

		// check if admin or not
		boolean checkAdminLogin = adService.checkAdminLogin(account, pwd);
		if (checkAdminLogin) {
			session.setAttribute("loginUsername", "admin");
			session.setAttribute("ifAdminOrNot", true);
			return "redirect:/mainPage";
		}

		// check user exist or not
		Users user = uService.checkLogin(account, pwd);
		if (user == null) {
			return "redirect:/UserLoginFailed";
		}

		// check user status
		switch (user.getStatus()) {
		case 1: {
			session.setAttribute("loginUsername", user.getUserName());
			session.setAttribute("userId",user.getUserId());
			session.setAttribute("user",user);

			user.setPrevlogTime(LocalDateTime.now());
			uService.insert(user);
			return "redirect:/mainPage";
		}
		case 0: {
			return "redirect:/UserConfirmFailed";
		}
		default: {
			return "redirect:/UserLoginStatusFailed";
		}
		}

	}
	
//	--------------------------------------  ForgotPassword-related Page ----------------------------------------------------
	@PostMapping("/forgotPassword")
	public String forgetPassword(@ModelAttribute UserDTO userDTO ,Model m) {		
		String output = "";
		Users user = userRepository.findByEmail(userDTO.getEmail());
		if (user != null) {
			output = uMailService.sendResetPasswordEmail(user);
		}
		if (output.equals("success")) {
			return "redirect:/forgotPassword?success";
		}
		return "redirect:/forgotPassword?failed";
		
	}

	
	@PostMapping("/resetPassword")
	public String passwordResetProcess(@ModelAttribute UserDTO userDTO) {
		Users user = userRepository.findByEmail(userDTO.getEmail());
		if(user != null) {
			user.setPassword(userDTO.getPassword());
			userRepository.save(user);
		}
		return "redirect:/UserLogin";
	}

}
