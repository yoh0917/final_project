package sellphone.dashboard.user.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import sellphone.dashboard.user.model.UserPasswordToken;
import sellphone.dashboard.user.model.UserPasswordTokenRepository;
import sellphone.dashboard.user.model.UserRepository;
import sellphone.dashboard.user.model.UserView;
import sellphone.dashboard.user.model.Users;
import sellphone.dashboard.user.service.UserMailService;
import sellphone.dashboard.user.service.UserService;
import sellphone.dashboard.user.service.UserViewService;

@Controller
public class UserPageController {

	@Autowired
	private UserService userService;
	
	@Autowired
	private UserViewService uVService;
	
	@Autowired
	private UserMailService uMailService;
	
	@Autowired
	private UserPasswordTokenRepository userPasswordTokenRepository;

	@GetMapping("/mainPage")
	public String mainPage(Model m) {
		return "index";
	}
//  -------------------------------------- UserInfo-related Page ----------------------------------------------------
	@GetMapping("/UserInfo")
	public String userInfo(Model m, HttpServletRequest req) throws ServletException, IOException {
		return "forward:UserInfoList";
	}
	
	@GetMapping("/UserOrder")
	public String userOrder(Model m, HttpServletRequest req) throws ServletException, IOException {
		return "forward:/UserOrderList";
	}
	
	@GetMapping("/UserPlan")
	public String userPlan(Model m, HttpServletRequest req) throws ServletException, IOException {
		return "forward:/UserPlanList";
	}
	
	@GetMapping("/UserPost")
	public String userPost(Model m, HttpServletRequest req) throws ServletException, IOException {
		return "forward:/UserPostList";
	}
	
	@GetMapping("/UserFix")
	public String userFix(Model m, HttpServletRequest req) throws ServletException, IOException {
		return "forward:/UserFixList";
	}
	
//	--------------------------------------  Login-related Page ----------------------------------------------------

	@GetMapping("/UserLogin")
	public String userLogin(Model m) throws ServletException, IOException {
		return "user/fronted/UserLogin";
	}
	

	@GetMapping("/UserLoginFailed")
	public String userLoginFailed(Model m) throws ServletException, IOException {
		m.addAttribute("error", "登入失敗，帳號或密碼錯誤");
		return "user/fronted/UserLogin";
	}
	@GetMapping("/UserConfirmFailed")
	public String userConfirmFailed(Model m) throws ServletException, IOException {
		m.addAttribute("error", "此帳號尚未進行驗證，請至Email進行開通帳號");
		return "user/fronted/UserLogin";
	}
	
	@GetMapping("/UserLoginStatusFailed")
	public String userLoginStatusFailed(Model m) throws ServletException, IOException {
		m.addAttribute("error", "此帳戶已被封鎖或刪除，請聯繫網站管理員");
		return "user/fronted/UserLogin";
	}

	@GetMapping("/UserLogout")
	public String userLogout(HttpSession session, Model m) {
		session.invalidate();
		return "redirect:/mainPage";
	}

//	--------------------------------------  Regist-related Page ----------------------------------------------------

	@GetMapping("/UserRegist")
	public String userRegist(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		return "user/fronted/UserRegist";
	}

	@GetMapping("/UserRegistFailed")
	public String userRegistFailed(HttpServletRequest req, HttpServletResponse resp, Model m)
			throws ServletException, IOException {
		m.addAttribute("error", "註冊失敗，請依指示填入正確資訊");
		return "user/fronted/UserRegist";
	}
	
	@GetMapping("/confirmAccount")
	@ResponseBody
	public String confirmAccount(@RequestParam("userId") String userId, Model model) {
		Users user = userService.findById(userId);
		
		if (user != null) {
			if(user.getStatus() == 0) {
				user.setStatus(1);
				userService.update(user);
				return "帳戶啟動成功";
			}
		}
		
		return "redirect:/mainPage";
	}
	
//	--------------------------------------  ForgotPassword-related Page ----------------------------------------------------
	@GetMapping("/forgotPassword")
	public String forgetPassword(Model m) {		
		return "user/fronted/forgotPassword";
	}

	@GetMapping("/resetPassword/{token}")
	public String resetPasswordForm(@PathVariable String token, Model model) {
		UserPasswordToken reset = userPasswordTokenRepository.findByToken(token);
		if (reset != null && uMailService.hasExipred(reset.getExpiryDateTime())) {
			model.addAttribute("email", reset.getUser().getEmail());
			return "/user/fronted/resetPassword";
		}
		return "redirect:/forgotPassword?error";
	}
//	--------------------------------------  DashBoard-related Page ----------------------------------------------------

	@GetMapping("/DashBoard/UserList")
	public String userList(Model m) throws ServletException, IOException {

		List<UserView> userList = uVService.findAll();
		m.addAttribute("userList", userList);

		return "user/dashBoard/UserList";
	}

}
