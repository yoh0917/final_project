package sellphone.cart.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import sellphone.cart.model.CartView;
import sellphone.cart.service.CheckoutService;
import sellphone.user.model.Users;

import java.util.List;

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
            return "redirect:/sellphone/login";
        }

        String userId = user.getUserId();
        System.out.println("User found in session. UserId: " + userId); // 記錄userId
        List<CartView> carts = checkoutService.getCartByUserId(userId);
        model.addAttribute("carts", carts);
        model.addAttribute("totalAmount", checkoutService.calculateTotalAmount(carts));
        return "OrderFrontend/Checkout1";
    }

}


