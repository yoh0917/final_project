package sellphone.orders.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttribute;
import sellphone.orders.model.Order;
import sellphone.orders.model.OrderDetail;
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
    public String getCustomerOrders(HttpSession session, Model model) {
        Users user = (Users) session.getAttribute("user");
        if (user == null) {
            // 如果用戶未登錄，重定向到登錄頁面
            return "redirect:/login";
        }

        String userId = user.getUserId();
        System.out.println("User found in session. UserId: " + userId);

        List<Order> orders = ordersService.getOrdersByUserId(userId);
        model.addAttribute("orders", orders);
        return "OrderFrontend/CustomerOrder";
    }

    @GetMapping("/customerOrder/details/{orderId}")
    @ResponseBody
    public List<OrderDetail> getOrderDetails(@PathVariable String orderId) {
        return ordersService.getOrderDetailsByOrderId(orderId);
    }
}