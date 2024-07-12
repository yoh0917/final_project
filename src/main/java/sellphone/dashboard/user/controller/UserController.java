package sellphone.dashboard.user.controller;

import java.io.IOException;

import java.time.LocalDateTime;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import sellphone.dashboard.admin.service.AdminService;
import sellphone.dashboard.user.model.Users;
import sellphone.dashboard.user.service.UserService;
import sellphone.dashboard.user.service.UserUtil;

@Controller
public class UserController {

	@Autowired
	private UserService uService;

	@Autowired
	private AdminService adService;
	
	@Autowired
	private UserUtil userUtil;

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

		
		Users user = uService.checkLogin(account, pwd);
		if (user == null) {
			return "redirect:UserLoginFailed";
		}

		switch (user.getStatus()) {
		case 1: {
			session.setAttribute("loginUsername", user.getUserName());
			session.setAttribute("userId",user.getUserId());
			user.setPrevlogTime(LocalDateTime.now());
			uService.insert(user);
			return "redirect:/mainPage";
		}
		case 0: {
			session.setAttribute("loginUsername", user.getUserName());
			session.setAttribute("userId",user.getUserId());
			user.setPrevlogTime(LocalDateTime.now());
			uService.insert(user);
			return "redirect:/mainPage";
		}
		default: {
			return "redirect:/UserLoginStatusFailed";
		}
		}

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
