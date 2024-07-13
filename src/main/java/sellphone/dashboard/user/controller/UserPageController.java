package sellphone.dashboard.user.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import sellphone.dashboard.user.model.UserView;
import sellphone.dashboard.user.service.UserViewService;

@Controller
public class UserPageController {

	@Autowired
	private UserViewService uVService;

	@GetMapping("/mainPage")
	public String mainPage(Model m) {

		return "index";
	}
//  -------------------------------------- fronted-related Page ----------------------------------------------------
	@GetMapping("/UserInfo")
	public String userInfo(Model m, HttpServletRequest req) throws ServletException, IOException {

		HttpSession session = req.getSession(false);
		System.out.println("Current Session: " + session);

		return "user/fronted/userInfo";
	}
	
	@GetMapping("/UserOrder")
	public String userOrder(Model m, HttpServletRequest req) throws ServletException, IOException {

		HttpSession session = req.getSession(false);
		System.out.println("Current Session: " + session);

		return "user/fronted/userInfo";
	}
	
	@GetMapping("/UserPlan")
	public String userPlan(Model m, HttpServletRequest req) throws ServletException, IOException {

		HttpSession session = req.getSession(false);
		System.out.println("Current Session: " + session);

		return "user/fronted/userInfo";
	}
	
	@GetMapping("/UserPost")
	public String userPost(Model m, HttpServletRequest req) throws ServletException, IOException {

		HttpSession session = req.getSession(false);
		System.out.println("Current Session: " + session);

		return "user/fronted/userInfo";
	}
	
	@GetMapping("/UserFix")
	public String userFix(Model m, HttpServletRequest req) throws ServletException, IOException {

		HttpSession session = req.getSession(false);
		System.out.println("Current Session: " + session);

		return "user/fronted/userInfo";
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

	@GetMapping("/UserLoginStatusFailed")
	public String userLoginStatusFailed(Model m) throws ServletException, IOException {
		m.addAttribute("error", "此帳戶已被封鎖或刪除，請聯繫網站管理員");
		return "user/fronted/UserLogin";
	}

	@GetMapping("/UserLogout")
	public String userLogout(HttpServletRequest req, HttpServletResponse resp, Model m) {

		HttpSession session = req.getSession();
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

//	--------------------------------------  DashBoard-related Page ----------------------------------------------------

	@GetMapping("/DashBoard/UserList")
	public String userList(Model m) throws ServletException, IOException {

		List<UserView> userList = uVService.findAll();
		
		System.out.println(userList);
		m.addAttribute("userList", userList);

		return "user/dashBoard/UserList";
	}

}
