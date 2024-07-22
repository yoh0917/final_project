package sellphone.cart.service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sellphone.cart.model.CartView;
import sellphone.cart.repository.CartViewRepository;
import sellphone.orders.model.Order;
import sellphone.orders.model.OrderDetail;
import sellphone.orders.repository.OrderDetailRepository;
import sellphone.orders.repository.OrderRepository;
import sellphone.cart.repository.CartRepository;

@Service
public class CheckoutService {

	@Autowired
	private CartViewRepository cartViewRepository;

	@Autowired
	private OrderRepository orderRepository;

	@Autowired
	private OrderDetailRepository orderDetailRepository;

	@Autowired
	private CartRepository cartRepository;

	private AtomicInteger sequence = new AtomicInteger();

	public String generateOrderId() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		String dateStr = sdf.format(new Date());
		return "S" + dateStr + String.format("%03d", sequence.incrementAndGet());
	}

	public Order createOrder(Map<String, Object> requestData, String userId) {
		// 將orderDetailsData轉換為CartView類型
		List<CartView> orderDetailsData = (List<CartView>) requestData.get("orderDetails");
		int totalAmount = calculateTotalAmount(orderDetailsData);

		Order order = new Order();
		order.setOrderId(generateOrderId());
		order.setUserId(userId);
		order.setStatus("N");
		order.setCreateDate(new Date());
		order.setTotalAmount(totalAmount);
		order.setPayStatus("N");
		order.setUserName((String) requestData.get("userName"));
		order.setEmail((String) requestData.get("email"));
		order.setPhone((String) requestData.get("phone"));
		order.setCity((String) requestData.get("city"));
		order.setDistrict((String) requestData.get("district"));
		order.setAddress((String) requestData.get("address"));
		order.setDetailAddress((String) requestData.get("detailAddress"));
		orderRepository.save(order);
		return order;
	}

	public List<OrderDetail> createOrderDetails(Map<String, Object> requestData, String orderId) {
		List<OrderDetail> orderDetails = ((List<Map<String, Object>>) requestData.get("orderDetails")).stream().map(data -> {
			OrderDetail detail = new OrderDetail();
			detail.setDetailId(generateOrderId());
			detail.setOrderId(orderId);
			detail.setProductId((Integer) data.get("productId"));
			detail.setProductName((String) data.get("productName"));
			detail.setQuantity((Integer) data.get("quantity"));
			detail.setPrice((Integer) data.get("price"));
			detail.setTotal(detail.getPrice() * detail.getQuantity());
			return detail;
		}).collect(Collectors.toList());
		orderDetailRepository.saveAll(orderDetails);
		return orderDetails;
	}

	public void saveOrder(Map<String, Object> requestData) {
		try {
			// 提取表單資料
			Map<String, Object> orderData = (Map<String, Object>) requestData.get("order");
			List<Map<String, Object>> orderDetailsData = (List<Map<String, Object>>) requestData.get("orderDetails");

			String userId = (String) requestData.get("userId");
			List<CartView> carts = getCartByUserId(userId);

			// 創建訂單
			Order order = new Order();
			order.setOrderId(generateOrderId());
			order.setUserId(userId);
			order.setStatus("N");
			order.setCreateDate(new Date());
			order.setTotalAmount(calculateTotalAmount(carts));
			order.setPayStatus("N");
			order.setUserName((String) requestData.get("userName"));
			order.setEmail((String) requestData.get("email"));
			order.setPhone((String) requestData.get("phone"));
			order.setCity((String) requestData.get("city"));
			order.setDistrict((String) requestData.get("district"));
			order.setAddress((String) requestData.get("address"));
			order.setDetailAddress((String) requestData.get("detailAddress"));
			orderRepository.save(order);

			// 創建訂單明細
			List<OrderDetail> orderDetails = carts.stream().map(cart -> {
				OrderDetail detail = new OrderDetail();
				detail.setDetailId(order.getOrderId().replaceFirst("S", "D"));
				detail.setOrderId(order.getOrderId());
				detail.setProductId(cart.getProductId());
				detail.setProductName(cart.getProductName());
				detail.setColor(cart.getColor());
				detail.setStorage(cart.getStorage());
				detail.setQuantity(cart.getQuantity());
				detail.setPrice(cart.getPrice());
				detail.setTotal(cart.getPrice() * cart.getQuantity());
				return detail;
			}).collect(Collectors.toList());

			orderDetailRepository.saveAll(orderDetails);
		} catch (Exception e) {
			throw new RuntimeException("插入資料庫時發生錯誤: " + e.getMessage(), e);
		}
	}

	public List<CartView> getCartByUserId(String userId) {
		return cartViewRepository.findByUserId(userId);
	}

	public int calculateTotalAmount(List<CartView> carts) {
		return carts.stream().mapToInt(cart -> cart.getPrice() * cart.getQuantity()).sum();
	}


//	@Transactional
//	public void saveOrder(Order order, List<OrderDetail> orderDetails) {
//		orderRepository.save(order);
//		orderDetailRepository.saveAll(orderDetails);
//	}
//
//	public void createOrderFromCart(List<CartView> carts) {
//		String orderId = generateOrderId();
//		CartView cartView = carts.get(0);  // 假設每個購物車的用戶ID都是相同的
//
//		Order order = new Order();
//		order.setOrderId(orderId);
//		order.setCreateDate(new Date());
//		order.setTotalAmount(carts.stream().mapToInt(cart -> cart.getQuantity() * cart.getPrice()).sum());
//		order.setUserId(cartView.getUserId());
//		order.setUserName("");  // 暫時不填入用戶名
//
//		List<OrderDetail> orderDetails = carts.stream().map(cart -> {
//			OrderDetail detail = new OrderDetail();
//			detail.setDetailId(generateOrderId() + "-" + sequence.incrementAndGet());
//			detail.setOrderId(orderId);
//			detail.setProductId(cart.getProductId());
//			detail.setProductName(cart.getProductName());
//			detail.setQuantity(cart.getQuantity());
//			detail.setPrice(cart.getPrice());
//			detail.setTotal(cart.getQuantity() * cart.getPrice());
//			return detail;
//		}).toList();
//
//		saveOrder(order, orderDetails);
//	}
//
//	public String generateOrderId() {
//		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
//		String dateStr = sdf.format(new Date());
//		return "S" + dateStr + String.format("%03d", sequence.incrementAndGet());
//	}
}