package sellphone.cart.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import sellphone.cart.model.CartView;
import sellphone.dashboard.user.model.Users;
import sellphone.orders.model.Order;
import sellphone.orders.model.OrderDetail;
import sellphone.cart.service.CheckoutService;
import sellphone.cart.service.CartService;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/orders")
public class CheckoutController {

    @Autowired
    private CartService cartService;

    @Autowired
    private CheckoutService checkoutService;

    @GetMapping("/checkout1")
    public String checkoutPage(Model model, HttpSession session) {
        Users user = (Users) session.getAttribute("user");
        if (user == null) {
            System.out.println("User not found in session. Redirecting to login.");
            return "redirect:/sellphone/login";
        }

        String userId = user.getUserName();
        List<CartView> carts = cartService.getCartItems(userId);
        int totalItems = carts.stream().mapToInt(CartView::getQuantity).sum();
        int totalPrice = carts.stream().mapToInt(cart -> cart.getQuantity() * cart.getPrice()).sum();

        model.addAttribute("carts", carts);
        model.addAttribute("totalItems", totalItems);
        model.addAttribute("totalPrice", totalPrice);

        return "OrderFrontend/Checkout1";
    }


//    @GetMapping("/checkout1")
//    public String checkoutPage(Model model, HttpSession session) {
//        String userId = (String) session.getAttribute("UserId");
//        if (userId == null) {
//            userId = "DefaultUserId"; // 確保有一個默認值，如果session中沒有UserId
//        }
//        List<CartView> carts = checkoutService.getCartByUserId(userId);
//        model.addAttribute("carts", carts);
//        model.addAttribute("totalAmount", checkoutService.calculateTotalAmount(carts));
//        return "OrderFrontend/Checkout1";
//    }

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
    public String saveOrder(@RequestBody Map<String, Object> requestData, Model model) {
        try {
            checkoutService.saveOrder(requestData);
            model.addAttribute("message", "已成功插入資料庫");
        } catch (RuntimeException e) {
            model.addAttribute("message", "插入資料庫失敗: " + e.getMessage());
        }
        return "OrderFrontend/Checkout1";
    }


}



