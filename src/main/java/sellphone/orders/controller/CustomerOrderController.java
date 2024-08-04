package sellphone.orders.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.SessionAttribute;
import sellphone.orders.model.Order;
import sellphone.orders.service.OrdersService;
import sellphone.user.model.Users;

import jakarta.servlet.http.HttpSession;

import java.util.ArrayList;
import java.util.List;

@Controller
public class CustomerOrderController {

    @Autowired
    private OrdersService ordersService;

    @GetMapping("/CustomerOrder")
    public String getUserOrders(HttpSession session, Model model) {
        Users user = (Users) session.getAttribute("user");
        if (user != null) {
            String userId = user.getUserId();
            System.out.println("User found in session. UserId: " + userId); // 記錄userId
            List<Order> orders = ordersService.getOrdersByUserId(userId);
            model.addAttribute("orders", orders);
        } else {
            System.out.println("No user found in session.");
        }
        return "OrderFrontend/CustomerOrder";
    }
}
