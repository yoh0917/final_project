package sellphone.cart.service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;
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

	public List<CartView> getCartByUserId(String userId) {
		return cartViewRepository.findByUserId(userId);
	}

	public int calculateTotalAmount(List<CartView> carts) {
		return carts.stream().collect(Collectors.summingInt(cart -> cart.getPrice() * cart.getQuantity()));
	}

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
			orderDetail.setProductName(item.getProductName());
			orderDetail.setColor(item.getColor());
			orderDetail.setStorage(item.getStorage());
			orderDetail.setQuantity(item.getQuantity());
			orderDetail.setPrice(item.getPrice());
			orderDetailRepository.save(orderDetail);
		}
	}

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

	Order order = new Order();
	OrderDetail orderDetail = new OrderDetail();

	public String ecpayCheckout(String orderId ) {

		Optional<Order> orderOptional = orderRepository.findById(orderId);
		Order order = orderOptional.get();
		AllInOne all = new AllInOne("");
		Date createTime = order.getCreateDate();
		Integer getTotalAmount = order.getTotalAmount();


		// 定義 SimpleDateFormat 來格式化 Date
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");

		// 將 Date 格式化為指定格式的字符串
		String formattedDate = simpleDateFormat.format(createTime);
		System.out.println(formattedDate);

		// 網址
		String url = "http://localhost:8081";

//		System.out.println(test);
		AioCheckOutALL obj = new AioCheckOutALL();
//		System.out.println(orders.getOrderId().toString());
		obj.setMerchantTradeNo(order.getOrderId().toString());//綠界傳訂單編號
//		obj.setMerchantTradeDate(formattedDate);
//		obj.setTotalAmount((orders.getTotalCost() + ""));//抓總金額


		// 交易結果回傳網址，只接受 https 開頭的網站，可以使用 ngrok

		// 交易結果
		obj.setReturnURL(url + "/orders/backendReturn");

		// 付款完成跳轉結果
		obj.setClientBackURL(url + "/orders/frontendReturn?orderId=" + order.getOrderId().toString());
		obj.setNeedExtraPaidInfo("N");
		// 商店轉跳網址 (Optional)
		try {
			String form = all.aioCheckOut(obj, null);
			return form;
		} catch (EcpayException e) {
			System.err.println("ECPay Exception: " + e.getMessage());
			throw e;
		}
	}



	//private final AllInOne allInOne = new AllInOne("");

//	private String ecpayCheckout(String orderId, String userId) {
//		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/ddn HH:mm:ss");
//		String dateStr = sdf.format(new Date());
//
//		Orders order = new Orders();
//
//		AioCheckOutALL obj = new AioCheckOutALL();
////		System.out.println(orders.getOrderId().toString());
//		obj.setMerchantTradeNo(orders.getOrderId().toString());//綠界傳訂單編號
//		obj.setMerchantTradeDate(dateStr);//交易時間
//		obj.setTotalAmount((orders.getTotalCost() + ""));//抓總金額
//		obj.setTradeDesc("test Description");
//		obj.setItemName("TestItem");
//		// 交易結果回傳網址，只接受 https 開頭的網站，可以使用 ngrok
//
//		// 網址
//		String url = "http://localhost:8081";
//
//		// 交易結果
//		obj.setReturnURL(url + "/comPETnion/backendReturn");
//
//		// 付款完成跳轉結果
//		obj.setClientBackURL(url + "/comPETnion/frontendReturn?orderId=" + orders.getOrderId().toString());
//		obj.setNeedExtraPaidInfo("N");
//		// 商店轉跳網址 (Optional)
//		try {
//			String form = all.aioCheckOut(obj, null);
//			return form;
//		} catch (EcpayException e) {
//			System.err.println("ECPay Exception: " + e.getMessage());
//			throw e;
//		}
//	}


}