package sellphone.cart.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import jakarta.servlet.http.HttpSession;
import sellphone.cart.model.Cart;
import sellphone.cart.service.CartService;
import sellphone.dashboard.user.model.Users;

import java.util.List;

@Controller
public class CartController {
    @Autowired
    private CartService cartService;

    @GetMapping("/cart")
    public String showCart(Model model, HttpSession session) {
        Users user = (Users) session.getAttribute("user");
        if (user == null) {
            System.out.println("User not found in session. Redirecting to login.");
            return "redirect:/sellphone/login"; // 若session中無user則重定向到登入頁面
        }

        String userId = user.getUserId();
        System.out.println("User found in session. UserId: " + userId); // 記錄userId
        List<Cart> carts = cartService.getCartByUserId(userId);
        model.addAttribute("carts", carts);

        return "OrderFrontend/Cart1"; // 返回Cart1.html視圖
    }

//    @GetMapping("/cart")
//    public String getCart(HttpSession session, Model model) {
//        // 從 session 中獲取 userId
//        String userId = (String) session.getAttribute("userId");
//
//
//
//        List<Cart> carts = cartService.getCartsByUserId(userId);
//        model.addAttribute("carts", carts);
//        model.addAttribute("userId", userId); // 將 userId 添加到模型中
//        return "OrderFrontend/Cart1"; // 確保返回正確的視圖名稱
//    }
}
