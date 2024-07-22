package sellphone.cart.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sellphone.cart.model.Cart;
import sellphone.cart.model.CartPK;
import sellphone.cart.model.CartSummary;
import sellphone.cart.model.CartView;
import sellphone.cart.repository.CartViewRepository;
import sellphone.cart.repository.CartRepository;
import sellphone.product.model.ProductRepository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class CartService {

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private CartViewRepository cartViewRepository;

    @Autowired
    private ProductRepository productRepository; // Assuming you have a ProductRepository to fetch product details

    public List<CartView> getCartItems(String userId) {
        return cartViewRepository.findByUserId(userId);
    }

    public void updateQuantity(int productId, String userId, int delta) {
        Cart cart = cartRepository.findById(new CartPK(userId, productId)).orElse(null);
        if (cart != null) {
            int newQuantity = cart.getQuantity() + delta;
            if (newQuantity > 0) {
                cart.setQuantity(newQuantity);
                cartRepository.save(cart);
            } else {
                cartRepository.delete(cart);
            }
        }
    }

    @Transactional
    public String removeItem(int productId, String userId) {
        Optional<Cart> optionalCart = cartRepository.findById(new CartPK(userId, productId));
        if (optionalCart.isPresent()) {
            cartRepository.delete(optionalCart.get());
            return "商品已移除";
        } else {
            // Handle case where cart item is not found
            return "商品未找到";
        }
    }

    public CartSummary getCartSummary(String userId) {
        List<CartView> cartItems = getCartItems(userId);
        int totalItems = cartItems.stream().mapToInt(CartView::getQuantity).sum();
        int totalPrice = cartItems.stream().mapToInt(cart -> cart.getPrice() * cart.getQuantity()).sum();
        return new CartSummary(totalItems, totalPrice);
    }
//    @Autowired
//    private CartRepository cartRepository;
//
//    @Autowired
//    private CartViewRepository cartViewRepository;
//
//    public List<CartView> getCartItems(String userId) {
//        return cartViewRepository.findByUserId(userId);
//    }
//
//    public int getTotalPrice(String userId) {
//        return cartViewRepository.findByUserId(userId).stream()
//                .mapToInt(cart -> cart.getPrice() * cart.getQuantity())
//                .sum();
//    }
//
//    public int getTotalItems(String userId) {
//        return cartViewRepository.findByUserId(userId).stream()
//                .mapToInt(CartView::getQuantity)
//                .sum();
//    }
//
//    public boolean updateQuantity(int productId, String userId, int delta) {
//        Cart cart = cartRepository.findByProductIdAndUserId(productId, userId);
//        if (cart == null) {
//            return false;
//        }
//        int newQuantity = cart.getQuantity() + delta;
//        if (newQuantity < 1) {
//            return false;
//        }
//        cart.setQuantity(newQuantity);
//        cartRepository.save(cart);
//        return true;
//    }


}
