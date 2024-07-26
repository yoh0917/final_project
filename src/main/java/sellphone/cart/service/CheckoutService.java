package sellphone.cart.service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;
import sellphone.cart.model.CartView;
import sellphone.cart.repository.CartViewRepository;
import sellphone.orders.model.Order;
import sellphone.orders.model.OrderDetail;
import sellphone.orders.repository.OrderDetailRepository;
import sellphone.orders.repository.OrderRepository;

import ecpay.payment.integration.AllInOne;
import ecpay.payment.integration.domain.AioCheckOutALL;
import ecpay.payment.integration.exception.EcpayException;

@Service
public class CheckoutService {

	@Autowired
	private OrderRepository orderRepository;

	@Autowired
	private OrderDetailRepository orderDetailRepository;

	@Autowired
	private CartViewRepository cartViewRepository;

	private static final AtomicInteger sequence = new AtomicInteger(0);

	@Transactional
	public void saveOrder(Order order) {
		orderRepository.save(order);
	}

	@Transactional
	public void saveOrderDetails(String orderId, String userId) {
		List<CartView> cartItems = cartViewRepository.findByUserId(userId);
		for (CartView item : cartItems) {
			OrderDetail orderDetail = new OrderDetail();
			orderDetail.setDetailId(generateDetailId(orderId));
			orderDetail.setOrderId(orderId);
			orderDetail.setProductId(item.getProductId());
			orderDetail.setColor(item.getColor());
			orderDetail.setStorage(item.getStorage());
			orderDetail.setQuantity(item.getQuantity());
			orderDetail.setPrice(item.getPrice());
			orderDetailRepository.save(orderDetail);
		}
	}
//	@Transactional
//	public void saveOrderAndDetails(Order order, String userId) {
//		try {
//			// Generate order ID
//			order.setOrderId(generateOrderId());
//
//			// Print Order JSON
//			ObjectMapper objectMapper = new ObjectMapper();
//			String orderJson = objectMapper.writeValueAsString(order);
//			System.out.println("Order JSON: " + orderJson);
//
//			// Save order to O0001_ORDER
//			order.setUserId(userId);
//			orderRepository.save(order);
//
//			// Print UserId
//			System.out.println("UserId: " + userId);
//
//			// Fetch shopping cart details for the user from S1001_SHOPPINGCART_V
//			List<CartView> cartItems = cartViewRepository.findByUserId(userId);
//
//			// Print cart items JSON
//			String cartItemsJson = objectMapper.writeValueAsString(cartItems);
//			System.out.println("Cart Items JSON: " + cartItemsJson);
//
//			// Check if cartItems is not empty
//			if (cartItems.isEmpty()) {
//				System.out.println("No items found in shopping cart for user: " + userId);
//				return;
//			}
//
//			// Create order details and save to O0002_ORDERDETAIL
//			int detailIdCounter = 1;
//			for (CartView cart : cartItems) {
//				OrderDetail orderDetail = new OrderDetail();
//				orderDetail.setDetailId(generateDetailId(detailIdCounter++));
//				orderDetail.setOrderId(order.getOrderId());
//				orderDetail.setProductId(cart.getProductId());
//				orderDetail.setColor(cart.getColor());
//				orderDetail.setStorage(cart.getStorage());
//				orderDetail.setQuantity(cart.getQuantity());
//				orderDetail.setPrice(cart.getPrice());
//
//				// Print OrderDetail JSON
//				String orderDetailJson = objectMapper.writeValueAsString(orderDetail);
//				System.out.println("OrderDetail JSON: " + orderDetailJson);
//
//				orderDetailRepository.save(orderDetail);
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}

	public String generateOrderId() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		String dateStr = sdf.format(new Date());
		return "S" + dateStr + String.format("%03d", sequence.incrementAndGet());
	}

	private String generateDetailId(String orderId) {
		Random random = new Random();
		int randomInt = random.nextInt(900) + 100; // 生成100到999之間的隨機數
		return "D" + orderId.substring(1, 14) + randomInt;
	}

//	private final AllInOne allInOne = new AllInOne("");
//
//	private String ecpayCheckout(String orderId, String userId) {
//		AllInOne allInOne = new AllInOne("");
//		// 定義 SimpleDateFormat 來格式化 Date
//		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
//		Date createTime = orders.getCreateTime();
//		// 將 Date 格式化為指定格式的字符串
//		String formattedDate = simpleDateFormat.format(createTime);
//		System.out.println(formattedDate);
//
//		return "AAA";
//	}



//	public Order createOrder(Map<String, Object> requestData, String userId) {
//		// 將orderDetailsData轉換為CartView類型
//		List<CartView> orderDetailsData = (List<CartView>) requestData.get("orderDetails");
////		int totalAmount = calculateTotalAmount(orderDetailsData);
//
//		Order order = new Order();
//		order.setOrderId(generateOrderId());
//		order.setUserId(userId);
//		order.setStatus("N");
//		order.setCreateDate(new Date());
////		order.setTotalAmount(totalAmount);
//		order.setPayStatus("N");
//		order.setUserName((String) requestData.get("userName"));
//		order.setEmail((String) requestData.get("email"));
//		order.setPhone((String) requestData.get("phone"));
//		order.setCity((String) requestData.get("city"));
//		order.setDistrict((String) requestData.get("district"));
//		order.setAddress((String) requestData.get("address"));
//		order.setDetailAddress((String) requestData.get("detailAddress"));
//		orderRepository.save(order);
//		return order;
//	}
//
//	public List<OrderDetail> createOrderDetails(Map<String, Object> requestData, String orderId) {
//		List<OrderDetail> orderDetails = ((List<Map<String, Object>>) requestData.get("orderDetails")).stream().map(data -> {
//			OrderDetail detail = new OrderDetail();
//			detail.setDetailId(generateOrderId());
//			detail.setOrderId(orderId);
//			detail.setProductId((Integer) data.get("productId"));
//			detail.setProductName((String) data.get("productName"));
//			detail.setQuantity((Integer) data.get("quantity"));
//			detail.setPrice((Integer) data.get("price"));
//			detail.setTotal(detail.getPrice() * detail.getQuantity());
//			return detail;
//		}).collect(Collectors.toList());
//		orderDetailRepository.saveAll(orderDetails);
//		return orderDetails;
//	}
//
//	public void saveOrder(Map<String, Object> requestData) {
//		try {
//			// 提取表單資料
//			Map<String, Object> orderData = (Map<String, Object>) requestData.get("order");
//			List<Map<String, Object>> orderDetailsData = (List<Map<String, Object>>) requestData.get("orderDetails");
//
//			// 創建訂單
//			Order order = new Order();
//			order.setOrderId((String) orderData.get("orderId"));
//			order.setUserId((String) orderData.get("userId"));
//			order.setStatus((String) orderData.get("status"));
//			order.setCreateDate(new Date((Long) orderData.get("createDate")));
//			order.setTotalAmount((Integer) orderData.get("totalAmount"));
//			order.setPayStatus((String) orderData.get("payStatus"));
//			order.setUserName((String) orderData.get("userName"));
//			order.setEmail((String) orderData.get("email"));
//			order.setPhone((String) orderData.get("phone"));
//			order.setCity((String) orderData.get("city"));
//			order.setDistrict((String) orderData.get("district"));
//			order.setAddress((String) orderData.get("address"));
//			order.setDetailAddress((String) orderData.get("detailAddress"));
//			orderRepository.save(order);
//
//			// 創建訂單明細
//			for (Map<String, Object> detailData : orderDetailsData) {
//				OrderDetail detail = new OrderDetail();
//				detail.setDetailId((String) detailData.get("detailId"));
//				detail.setOrderId((String) detailData.get("orderId"));
//				detail.setProductId((Integer) detailData.get("productId"));
//				detail.setProductName((String) detailData.get("productName"));
//				detail.setQuantity((Integer) detailData.get("quantity"));
//				detail.setPrice((Integer) detailData.get("price"));
//				detail.setTotal((Integer) detailData.get("total"));
//				orderDetailRepository.save(detail);
//			}
//		} catch (Exception e) {
//			throw new RuntimeException("插入資料庫時發生錯誤: " + e.getMessage(), e);
//		}
//	}

	public List<CartView> getCartByUserId(String userId) {
		return cartViewRepository.findByUserId(userId);
	}

	public int calculateTotalAmount(List<CartView> carts) {
		return carts.stream().collect(Collectors.summingInt(cart -> cart.getPrice() * cart.getQuantity()));
	}
}