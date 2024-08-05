package sellphone.orders.repository;

import sellphone.orders.model.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface OrderDetailRepository extends JpaRepository<OrderDetail, String> {
    List<OrderDetail> findByOrderId(String orderId);

    @Query(value = "SELECT TOP 4 PRODUCTID, SUM (QUANTITY) FROM O0002_ORDERDETAIL GROUP BY PRODUCTID ORDER BY 2 DESC ",nativeQuery = true)
    List<Object[]> getMostSale();
}