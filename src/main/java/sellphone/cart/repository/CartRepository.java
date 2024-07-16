package sellphone.cart.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sellphone.cart.model.Cart;
import sellphone.cart.model.CartPK;

import java.util.List;

public interface CartRepository extends JpaRepository<Cart, CartPK> {
    List<Cart> findByUserId(String userId);
}
