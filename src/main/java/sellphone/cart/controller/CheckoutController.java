package sellphone.cart.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import sellphone.cart.model.CartView;
import sellphone.cart.service.CheckoutService;
import sellphone.orders.model.Order;
import sellphone.user.model.Users;

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
        Users user = (Users) session.getAttribute("user");
//        String userId = (String) session.getAttribute("UserId");
        if (user == null) {
            System.out.println("User not found in session. Redirecting to login.");
            return "redirect:/sellphone/login"; // 若session中無user則重定向到登入頁面
        }

        String userId = user.getUserName();
        System.out.println("User found in session. UserId: " + userId); // 記錄userId
        List<CartView> carts = checkoutService.getCartByUserId(userId);
//        List<CartView> carts = checkoutService.getCartByUserId(userId);
        model.addAttribute("carts", carts);
        model.addAttribute("totalAmount", checkoutService.calculateTotalAmount(carts));
        return "OrderFrontend/Checkout1";
    }




//    @PostMapping("/checkout1")
//    public String checkout(@RequestBody Map<String, Object> requestData, Model model, HttpSession session) {
//        String userId = (String) session.getAttribute("UserId");
//        if (userId == null) {
//            userId = "DefaultUserId";
//        }
//        Order order = checkoutService.createOrder(requestData, userId);
//        List<OrderDetail> orderDetails = checkoutService.createOrderDetails(requestData, order.getOrderId());
//        model.addAttribute("order", order);
//        model.addAttribute("orderDetails", orderDetails);
//        return "OrderFrontend/Checkout1";
//    }

//    @PostMapping("/save")
//    @ResponseBody
//    public String saveOrder(@RequestBody Map<String, Object> payload, Model model) {
//        Map<String, Object> response = new HashMap<>();
//        try {
//            Order order = new ObjectMapper().convertValue(payload.get("order"), Order.class);
//            checkoutService.saveOrderAndDetails(order);
//            response.put("success", true);
//            model.addAttribute("message", "訂單已成功插入");
//        } catch (Exception e) {
//            response.put("success", false);
//            response.put("message", e.getMessage());
//            model.addAttribute("errorMessage", "訂單插入失敗: " + e.getMessage());
//        }
//        model.addAttribute("response", response);
//        return "checkoutResult";
//    }


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
}


