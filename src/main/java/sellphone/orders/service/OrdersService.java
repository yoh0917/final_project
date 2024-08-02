package sellphone.orders.service;

import sellphone.orders.repository.OrderRepository;
import sellphone.orders.model.Order;
import sellphone.orders.model.OrderDetail;
import sellphone.orders.repository.OrderDetailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;



@Service
public class OrdersService {

	@Autowired
	private OrderRepository orderRepository;

	@Autowired
	private OrderDetailRepository orderDetailRepository;

	//查詢所有訂單
	public List<Order> getAllOrders() {
		return orderRepository.findAll();
	}

	//ID查詢訂單
	public Optional<Order> getOrderById(String orderId) {
		return orderRepository.findById(orderId);
	}

	//訂單ID查詢單筆訂單
	public List<OrderDetail> getOrderDetailsByOrderId(String orderId) {
		return orderDetailRepository.findByOrderId(orderId);
	}

	//創建訂單
	public void saveOrder(Order order) {
		orderRepository.save(order);
	}

	//修改
	public void updateOrder(Order order) {
		orderRepository.save(order);
	}

	// 假刪除訂單，更新訂單狀態為 "D"
	public void deleteOrder(String orderId) {
		Order order = orderRepository.findById(orderId).orElseThrow(() -> new RuntimeException("Order not found with id " + orderId));
		order.setStatus("D");
		orderRepository.save(order);
	}
}