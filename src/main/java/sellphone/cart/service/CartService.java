package sellphone.cart.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sellphone.cart.model.Cart;
import sellphone.cart.repository.CartRepository;

import java.util.List;

@Service
public class CartService {
    @Autowired
    private CartRepository cartRepository;

    public List<Cart> getCartsByUserId(String userId) {
        return cartRepository.findByUserId(userId);
    }
}
