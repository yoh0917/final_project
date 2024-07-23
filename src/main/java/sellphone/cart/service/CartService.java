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

import java.util.*;

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
        Cart cart = cartRepository.findById(new CartPK(productId, userId))
                .orElseThrow(() -> new NoSuchElementException("Cart not found"));
        cart.setQuantity(cart.getQuantity() + delta);
        cartRepository.save(cart);
    }

    public CartSummary getCartSummary(String userId) {
        List<Cart> carts = cartRepository.findByUserId(userId);
        System.out.println(carts);
        int totalItems = carts.stream().mapToInt(Cart::getQuantity).sum();
        System.out.println(totalItems);
        int totalPrice = carts.stream().mapToInt(cart -> cart.getQuantity() * cart.getPrice()).sum();
        System.out.println(totalItems);
        return new CartSummary(totalItems, totalPrice);
    }

    public void removeItem(int productId, String userId) {
        CartPK cartPK = new CartPK(productId, userId);
        cartRepository.deleteById(cartPK);
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
