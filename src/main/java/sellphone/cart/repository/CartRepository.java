package sellphone.cart.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import sellphone.cart.model.Cart;
import sellphone.cart.model.CartPK;

import java.util.List;

@Repository
public interface CartRepository extends JpaRepository<Cart, CartPK> {
    List<Cart> findByUserId(String userId);
}
