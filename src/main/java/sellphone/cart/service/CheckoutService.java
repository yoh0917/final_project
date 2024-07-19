package sellphone.cart.service;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sellphone.cart.model.CartView;
import sellphone.orders.model.Order;
import sellphone.orders.model.OrderDetail;
import sellphone.orders.repository.OrderDetailRepository;
import sellphone.orders.repository.OrderRepository;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;


@Service
public class CheckoutService {

	@Autowired
	private OrderRepository orderRepository;

	@Autowired
	private OrderDetailRepository orderDetailRepository;

//	//查詢所有訂單
//	public List<Order> getAllOrders() {
//		return orderRepository.findAll();
//	}
//
//	//ID查詢訂單
//	public Optional<Order> getOrderById(String orderId) {
//		return orderRepository.findById(orderId);
//	}
//
//	//訂單ID查詢單筆訂單
//	public List<OrderDetail> getOrderDetailsByOrderId(String orderId) {
//		return orderDetailRepository.findByOrderId(orderId);
//	}
//
//	//創建訂單
//	public void saveOrder(Order order) {
//		orderRepository.save(order);
//	}
//
//	//修改
//	public void updateOrder(Order order) {
//		orderRepository.save(order);
//	}
//
//	// 假刪除訂單，更新訂單狀態為 "D"
//	public void deleteOrder(String orderId) {
//		Order order = orderRepository.findById(orderId).orElseThrow(() -> new RuntimeException("Order not found with id " + orderId));
//		order.setStatus("D");
//		orderRepository.save(order);
//	}

	private AtomicInteger sequence = new AtomicInteger(0);

	@Transactional
	public void saveOrder(Order order, List<OrderDetail> orderDetails) {
		orderRepository.save(order);
		orderDetailRepository.saveAll(orderDetails);
	}

	public void createOrderFromCart(List<CartView> carts) {
		String orderId = generateOrderId();
		CartView cartView = carts.get(0);  // 假設每個購物車的用戶ID都是相同的

		Order order = new Order();
		order.setOrderId(orderId);
		order.setCreateDate(new Date());
		order.setTotalAmount(carts.stream().mapToInt(cart -> cart.getQuantity() * cart.getPrice()).sum());
		order.setUserId(cartView.getUserId());
		order.setUserName("");  // 暫時不填入用戶名

		List<OrderDetail> orderDetails = carts.stream().map(cart -> {
			OrderDetail detail = new OrderDetail();
			detail.setDetailId(generateOrderId() + "-" + sequence.incrementAndGet());
			detail.setOrderId(orderId);
			detail.setProductId(cart.getProductId());
			detail.setProductName(cart.getProductName());
			detail.setQuantity(cart.getQuantity());
			detail.setPrice(cart.getPrice());
			detail.setTotal(cart.getQuantity() * cart.getPrice());
			return detail;
		}).toList();

		saveOrder(order, orderDetails);
	}

	public String generateOrderId() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		String dateStr = sdf.format(new Date());
		return "S" + dateStr + String.format("%03d", sequence.incrementAndGet());
	}
}