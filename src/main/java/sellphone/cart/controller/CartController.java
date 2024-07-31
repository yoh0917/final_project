package sellphone.cart.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import jakarta.servlet.http.HttpSession;

import sellphone.cart.model.Cart;
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

    //加入購物車
    @ResponseBody
    @GetMapping("/addproductcart")
    public Cart addProductToCart(
    		@RequestParam String userId,
    		@RequestParam int productId,
    		@RequestParam String color,
    		@RequestParam String storage,
    		@RequestParam int quantity,
    		@RequestParam int price   		
    		) {
    	Cart cart = new Cart();
    	cart.setProductId(productId);
    	cart.setUserId(userId);
    	cart.setPrice(price);
    	cart.setColor(color);
    	cart.setStorage(storage);
    	cart.setQuantity(quantity);
        return cartService.addProductToCart(cart);
    }

    //直接購買
    @GetMapping("/addtocart")
    public String addToCart(
            @RequestParam String userId,
            @RequestParam int productId,
            @RequestParam String color,
            @RequestParam String storage,
            @RequestParam int quantity,
            @RequestParam int price
    ) {
        Cart cart = new Cart();
        cart.setProductId(productId);
        cart.setUserId(userId);
        cart.setPrice(price);
        cart.setColor(color);
        cart.setStorage(storage);
        cart.setQuantity(quantity);
        return "redirect:OrderFrontend/Cart1";
    }
    
    
    @GetMapping("/cart")
    public String showCart(Model model, HttpSession session) {
        Users user = (Users) session.getAttribute("user");
        if (user == null) {
            System.out.println("User not found in session. Redirecting to login.");
            return "redirect:/sellphone/login"; // 若session中無user則重定向到登入頁面
        }

        String userId = user.getUserId();
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

    @PostMapping("/cart/update")
    @ResponseBody
    public String updateCartQuantity(@RequestParam int productId, @RequestParam String userId, @RequestParam int delta) {
        try {
            cartService.updateQuantity(productId, userId, delta);
            return "數量更新成功";
        } catch (Exception e) {
            return "更新數量失敗";
        }
    }

    @GetMapping("/cart/summary")
    @ResponseBody
    public CartSummary getCartSummary(@RequestParam String userId) {
        return cartService.getCartSummary(userId);
    }

    @PostMapping("/cart/remove")
    public ResponseEntity<String> removeItem(@RequestParam int productId, @RequestParam String userId) {
        try {
            cartService.removeItem(productId, userId);
            return ResponseEntity.ok("刪除成功");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("刪除失敗");
        }
    }
}

