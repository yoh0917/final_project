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

@Service
public class CheckoutService {

	@Autowired
	private CartViewRepository cartViewRepository;

	@Autowired
	private OrderRepository orderRepository;

	@Autowired
	private OrderDetailRepository orderDetailRepository;

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

			// 創建訂單
			Order order = new Order();
			order.setOrderId((String) orderData.get("orderId"));
			order.setUserId((String) orderData.get("userId"));
			order.setStatus((String) orderData.get("status"));
			order.setCreateDate(new Date((Long) orderData.get("createDate")));
			order.setTotalAmount((Integer) orderData.get("totalAmount"));
			order.setPayStatus((String) orderData.get("payStatus"));
			order.setUserName((String) orderData.get("userName"));
			order.setEmail((String) orderData.get("email"));
			order.setPhone((String) orderData.get("phone"));
			order.setCity((String) orderData.get("city"));
			order.setDistrict((String) orderData.get("district"));
			order.setAddress((String) orderData.get("address"));
			order.setDetailAddress((String) orderData.get("detailAddress"));
			orderRepository.save(order);

			// 創建訂單明細
			for (Map<String, Object> detailData : orderDetailsData) {
				OrderDetail detail = new OrderDetail();
				detail.setDetailId((String) detailData.get("detailId"));
				detail.setOrderId((String) detailData.get("orderId"));
				detail.setProductId((Integer) detailData.get("productId"));
				detail.setProductName((String) detailData.get("productName"));
				detail.setQuantity((Integer) detailData.get("quantity"));
				detail.setPrice((Integer) detailData.get("price"));
				detail.setTotal((Integer) detailData.get("total"));
				orderDetailRepository.save(detail);
			}
		} catch (Exception e) {
			throw new RuntimeException("插入資料庫時發生錯誤: " + e.getMessage(), e);
		}
	}

	public List<CartView> getCartByUserId(String userId) {
		return cartViewRepository.findByUserId(userId);
	}

	public int calculateTotalAmount(List<CartView> carts) {
		return carts.stream().collect(Collectors.summingInt(cart -> cart.getPrice() * cart.getQuantity()));
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