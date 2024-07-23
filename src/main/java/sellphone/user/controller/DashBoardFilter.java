package sellphone.user.controller;

import java.io.IOException;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebFilter(urlPatterns = "/DashBoard/*", filterName = "loginFilter")
public class DashBoardFilter implements Filter {

	@Override
	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain)
			throws IOException, ServletException {
		HttpServletResponse response = (HttpServletResponse) resp;
		HttpServletRequest request = (HttpServletRequest) req;
		HttpSession session = request.getSession(false);

		String servletPath = request.getServletPath();
		System.out.println("ServletPath:" + servletPath);
		System.out.println("Current Session: " + session);
		try {
			if ((boolean) session.getAttribute("ifAdminOrNot")) {
				System.out.println("Login person:" + session.getAttribute("loginUsername"));
				System.out.println("DashBoard access authentication:" + session.getAttribute("ifAdminOrNot"));
				chain.doFilter(req, resp);
			} else {
				response.sendRedirect(request.getContextPath() + "/UserLogin");
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Warning: someone try to access dashboard wihtout authentication.");
			response.sendRedirect(request.getContextPath() + "/mainPage");
		}

	}

}
