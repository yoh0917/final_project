package sellphone.cart.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sellphone.cart.model.Cart;
import sellphone.cart.model.CartPK;
import sellphone.cart.model.CartView;
import sellphone.cart.repository.CartViewRepository;
import sellphone.cart.repository.CartRepository;

import java.util.List;

@Service
public class CartService {
    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private CartViewRepository cartViewRepository;

    public List<CartView> getCartItems(String userId) {
        return cartViewRepository.findByUserId(userId);
    }

    public int getTotalPrice(String userId) {
        return cartViewRepository.findByUserId(userId).stream()
                .mapToInt(cart -> cart.getPrice() * cart.getQuantity())
                .sum();
    }

    public int getTotalItems(String userId) {
        return cartViewRepository.findByUserId(userId).stream()
                .mapToInt(CartView::getQuantity)
                .sum();
    }

    public boolean updateQuantity(int productId, String userId, int delta) {
        Cart cart = cartRepository.findByProductIdAndUserId(productId, userId);
        if (cart == null) {
            return false;
        }
        int newQuantity = cart.getQuantity() + delta;
        if (newQuantity < 1) {
            return false;
        }
        cart.setQuantity(newQuantity);
        cartRepository.save(cart);
        return true;
    }


}
