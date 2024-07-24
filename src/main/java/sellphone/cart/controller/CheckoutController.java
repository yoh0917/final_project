package sellphone.cart.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import sellphone.cart.model.CartView;
import sellphone.orders.model.Order;
import sellphone.orders.model.OrderDetail;
import sellphone.cart.service.CheckoutService;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/orders")
public class CheckoutController {

    @Autowired
    private CheckoutService checkoutService;

    @GetMapping("/checkout1")
    public String checkoutPage(Model model, HttpSession session) {
        String userId = (String) session.getAttribute("UserId");
        if (userId == null) {
            userId = "DefaultUserId"; // 確保有一個默認值，如果session中沒有UserId
        }
        List<CartView> carts = checkoutService.getCartByUserId(userId);
        model.addAttribute("carts", carts);
        model.addAttribute("totalAmount", checkoutService.calculateTotalAmount(carts));
        return "OrderFrontend/Checkout1";
    }

    @PostMapping("/checkout1")
    public String checkout(@RequestBody Map<String, Object> requestData, Model model, HttpSession session) {
        String userId = (String) session.getAttribute("UserId");
        if (userId == null) {
            userId = "DefaultUserId"; // 確保有一個默認值，如果session中沒有UserId
        }
        Order order = checkoutService.createOrder(requestData, userId);
        List<OrderDetail> orderDetails = checkoutService.createOrderDetails(requestData, order.getOrderId());
        model.addAttribute("order", order);
        model.addAttribute("orderDetails", orderDetails);
        return "OrderFrontend/Checkout1";
    }

    @PostMapping("/save")
    public Map<String, Object> saveOrder(@RequestBody Map<String, Object> payload) {
        Map<String, Object> response = new HashMap<>();
        try {
            Order order = new ObjectMapper().convertValue(payload.get("order"), Order.class);
            checkoutService.saveOrderAndDetails(order);
            response.put("success", true);
        } catch (Exception e) {
            response.put("success", false);
            response.put("message", e.getMessage());
        }
        return response;
    }
//    @PostMapping("/save")
//    public String saveOrder(@RequestBody Map<String, Object> requestData, Model model) {
//        try {
//            checkoutService.saveOrder(requestData);
//            model.addAttribute("message", "已成功插入資料庫");
//        } catch (RuntimeException e) {
//            model.addAttribute("message", "插入資料庫失敗: " + e.getMessage());
//        }
//        return "OrderFrontend/Checkout1";
//    }
}


