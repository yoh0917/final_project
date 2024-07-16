package sellphone.cart.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import jakarta.servlet.http.HttpSession;
import sellphone.cart.model.Cart;
import sellphone.cart.service.CartService;

import java.util.List;

@Controller
public class CartController {
    @Autowired
    private CartService cartService;

    @GetMapping("/cart")
    public String getCart(HttpSession session, Model model) {
        // 從 session 中獲取 userId
        String userId = (String) session.getAttribute("userId");



        List<Cart> carts = cartService.getCartsByUserId(userId);
        model.addAttribute("carts", carts);
        model.addAttribute("userId", userId); // 將 userId 添加到模型中
        return "OrderFrontend/Cart1"; // 確保返回正確的視圖名稱
    }
}
