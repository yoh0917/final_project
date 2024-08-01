package sellphone.cart.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sellphone.orders.model.Order;
import sellphone.orders.model.OrderDetail;
import sellphone.orders.repository.OrderDetailRepository;
import sellphone.orders.repository.OrderRepository;

import java.util.List;
import java.util.Optional;

@Service
public class PayOrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderDetailRepository orderDetailRepository;

    public Optional<Order> getOrderById(String id) {
        return orderRepository.findById(id);
    }

    public List<OrderDetail> getOrderDetailsByOrderId(String orderId) {
        return orderDetailRepository.findByOrderId(orderId);
    }
}