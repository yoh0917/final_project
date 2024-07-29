package sellphone.cart.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import sellphone.cart.model.CartView;
import sellphone.cart.service.CheckoutService;
import sellphone.orders.model.Order;
import sellphone.orders.model.OrderDetail;
import sellphone.user.model.Users;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/orders")
public class CheckoutTableController {

    @Autowired
    private CheckoutService checkoutService;
    @PostMapping("/saveOrder")
    public Map<String, Object> saveOrder(@RequestBody Order order, HttpSession session) {
        Map<String, Object> response = new HashMap<>();
        try {
            Users user = (Users) session.getAttribute("user");
            if (user == null) {
                response.put("success", false);
                response.put("message", "User not found in session.");
                return response;
            }

            String userId = user.getUserName();
            order.setUserId(userId);
            checkoutService.saveOrder(order);
            response.put("success", true);
        } catch (Exception e) {
            response.put("success", false);
            response.put("message", e.getMessage());
        }
        return response;
    }

    @PostMapping("/saveOrderDetails")
    public Map<String, Object> saveOrderDetails(@RequestBody Map<String, String> request, HttpSession session) {
        Map<String, Object> response = new HashMap<>();
        try {
            String orderId = request.get("orderId");
            Users user = (Users) session.getAttribute("user");
            if (user == null) {
                response.put("success", false);
                response.put("message", "User not found in session.");
                return response;
            }

            String userId = user.getUserName();
            checkoutService.saveOrderDetails(orderId, userId);
            response.put("success", true);
        } catch (Exception e) {
            response.put("success", false);
            response.put("message", e.getMessage());
        }
        return response;
    }
//    @PostMapping("/save")
//    public Map<String, Object> saveOrder(@RequestBody Order order, HttpSession session) {
//        Map<String, Object> response = new HashMap<>();
//        String userId = (String) session.getAttribute("userId");
//
//        if (userId == null) {
//            response.put("success", false);
//            response.put("message", "User not logged in");
//            return response;
//        }
//
//        try {
//            checkoutService.saveOrderAndDetails(order, userId);
//            response.put("success", true);
//        } catch (Exception e) {
//            response.put("success", false);
//            response.put("message", e.getMessage());
//        }
//        return response;
//    }
}


//    public Map<String, Object> saveOrder(@RequestBody Map<String, Object> payload) {
//        Map<String, Object> response = new HashMap<>();
//        try {
//            Order order = new ObjectMapper().convertValue(payload.get("order"), Order.class);
//            checkoutService.saveOrderAndDetails(order);
//            response.put("success", true);
//        } catch (Exception e) {
//            response.put("success", false);
//            response.put("message", e.getMessage());
//        }
//        return response;
//    }

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


