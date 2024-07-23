package sellphone.cart.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import jakarta.servlet.http.HttpSession;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import org.springframework.web.bind.annotation.ResponseBody;
import sellphone.cart.model.CartSummary;
import sellphone.cart.model.CartView;
import sellphone.cart.repository.CartViewRepository;
import sellphone.cart.service.CartService;
import sellphone.product.model.Photo;
import sellphone.product.model.PhotoRepository;
import sellphone.user.model.Users;

import java.util.Base64;
import java.util.List;
import java.util.Map;


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
        session.setAttribute("carts", carts);
        for (CartView cart : carts) {
            List<Photo> photos = photoRepository.findFirstPhotoByProductid(cart.getProductId());
            if (!photos.isEmpty()) {
                cart.setPhotoBase64(encodePhotoToBase64(photos.get(0).getPhotoFile()));
            }
        }

//        int totalPrice = (int) carts.stream().mapToDouble(CartView::getPrice).sum();
//        model.addAttribute("carts", carts);
//        model.addAttribute("totalPrice", totalPrice);
        int totalItems = carts.stream().mapToInt(CartView::getQuantity).sum();
        double subtotal = carts.stream().mapToDouble(cart -> cart.getPrice() * cart.getQuantity()).sum();
        double total = subtotal;

        model.addAttribute("carts", carts);
        model.addAttribute("totalItems", totalItems);
        model.addAttribute("subtotal", subtotal);
        model.addAttribute("total", total);
        model.addAttribute("userId", userId);

        return "OrderFrontend/Cart1"; // 返回Cart1.html視圖
    }

    private String encodePhotoToBase64(byte[] photoFile) {
        return Base64.getEncoder().encodeToString(photoFile);
    }

//    @GetMapping("/shoppingcart/cart")
//    public String getCart(Model model, HttpSession session) {
//        String userId = (String) session.getAttribute("UserId");
//        List<CartView> carts = cartViewRepository.findByUserId(userId);
//        model.addAttribute("carts", carts);
//        return "OrderFrontend/Cart1";
//    }

    @PostMapping("/cart/update")
    @ResponseBody
    public String updateQuantity(@RequestParam("productId") int productId,
                                 @RequestParam("userId") String userId,
                                 @RequestParam("delta") int delta) {
        try {
            cartService.updateQuantity(productId, userId, delta);
            return "數量更新成功";
        } catch (Exception e) {
            return "更新失敗";
        }
    }

    @PostMapping("/remove")
    public ResponseEntity<String> removeItem(@RequestParam int productId, @RequestParam String userId) {
        try {
            cartService.removeItem(productId, userId);
            return ResponseEntity.ok("刪除成功");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("刪除失敗");
        }
    }

//    @PostMapping("/checkout")
//    public ResponseEntity<String> checkout(@RequestParam String userId) {
//        try {
//            cartService.checkout(userId);
//            return ResponseEntity.ok("結帳成功");
//        } catch (Exception e) {
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("結帳失敗");
//        }
//    }

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

