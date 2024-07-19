package sellphone.cart.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import jakarta.servlet.http.HttpSession;
import sellphone.cart.model.CartView;
import sellphone.cart.repository.CartViewRepository;
import sellphone.cart.service.CartService;
import sellphone.dashboard.user.model.Users;
import sellphone.product.model.Photo;
import sellphone.product.model.PhotoRepository;

import java.util.Base64;
import java.util.List;
import java.util.Optional;

@Controller
public class CartController {

    @Autowired
    private CartService cartService;

    @Autowired
    private PhotoRepository photoRepository;

    @Autowired
    private CartViewRepository cartViewRepository;

    @GetMapping("/cart")
    public String showCart(Model model, HttpSession session) {
        Users user = (Users) session.getAttribute("user");
        if (user == null) {
            System.out.println("User not found in session. Redirecting to login.");
            return "redirect:/sellphone/login"; // 若session中無user則重定向到登入頁面
        }

        String userId = user.getUserName();
        System.out.println("User found in session. UserId: " + userId); // 記錄userId
        List<CartView> carts = cartService.getCartItems(userId);
        session.setAttribute("carts",carts);
        for (CartView cart : carts) {
            List<Photo> photos = photoRepository.findFirstPhotoByProductid(cart.getProductId());
            if (!photos.isEmpty()) {
                cart.setPhotoBase64(encodePhotoToBase64(photos.get(0).getPhotoFile()));
            }
        }

        double totalPrice = carts.stream().mapToDouble(CartView::getPrice).sum();
        model.addAttribute("carts", carts);
        model.addAttribute("totalPrice", totalPrice);

        return "OrderFrontend/Cart1"; // 返回Cart1.html視圖
    }

    private String encodePhotoToBase64(byte[] photoFile) {
        return Base64.getEncoder().encodeToString(photoFile);
    }

    @GetMapping("/shoppingcart/cart")
    public String getCart(Model model, HttpSession session) {
        String userId = (String) session.getAttribute("UserId");
        List<CartView> carts = cartViewRepository.findByUserId(userId);
        model.addAttribute("carts", carts);
        return "Cart1";
    }
}



//    @GetMapping("/cart")
//    public String showCart(Model model, HttpSession session) {
//        Users user = (Users) session.getAttribute("user");
//        if (user == null) {
//            System.out.println("User not found in session. Redirecting to login.");
//            return "redirect:/sellphone/login"; // 若session中無user則重定向到登入頁面
//        }
//
//        String userId = user.getUserName();
//        System.out.println("User found in session. UserId: " + userId); // 記錄userId
//        List<Cart> carts = cartService.getCartByUserId(userId);
//        model.addAttribute("carts", carts);
//
//        return "OrderFrontend/Cart1"; // 返回Cart1.html視圖
//    }

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

