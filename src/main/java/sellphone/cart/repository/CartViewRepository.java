package sellphone.cart.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sellphone.cart.model.CartView;

import java.util.List;

public interface CartViewRepository extends JpaRepository<CartView, Integer> {
    List<CartView> findByUserId(String userId);


}