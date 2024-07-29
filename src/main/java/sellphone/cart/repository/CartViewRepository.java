package sellphone.cart.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sellphone.cart.model.CartView;

import java.util.List;

@Repository
public interface CartViewRepository extends JpaRepository<CartView, Long> {
    List<CartView> findByUserId(String userId);
}