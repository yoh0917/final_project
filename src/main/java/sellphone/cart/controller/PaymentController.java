package sellphone.cart.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import sellphone.cart.service.CheckoutService;
import sellphone.orders.model.Order;
import sellphone.orders.repository.OrderRepository;
import sellphone.orders.service.OrdersService;
import sellphone.user.model.Users;


@Controller
@RequestMapping("")
public class PaymentController {

    @Autowired
    private CheckoutService checkoutService;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrdersService ordersService;

//    @GetMapping("/ecpayCheckout")
//    public ResponseEntity<String> ecpayCheckout(@RequestParam String orderId) {
//        try {
//            Order order = orderRepository.findById(String.valueOf(orderId)).orElseThrow(() -> new RuntimeException("Order not found"));
//            String aioCheckOutALLForm = checkoutService.ecpayCheckout(String.valueOf(order));
//            return ResponseEntity.ok(aioCheckOutALLForm);
//        } catch (Exception e) {
//            return ResponseEntity.badRequest().body("Error: " + e.getMessage());
//        }
//    }


    @PostMapping("/api/checkout/ecpayCheckout")
    public String ecpayCheckout(@RequestParam("orderId") String orderId, Model model, HttpSession session) {

        Users user = (Users) session.getAttribute("user");
        String userId = user.getUserName();

        String aioCheckOutALLForm = checkoutService.ecpayCheckout(orderId, userId);

        model.addAttribute("aioCheckOutALLForm", aioCheckOutALLForm);
//        model.addAttribute("carts", carts);
//        model.addAttribute("totalAmount", checkoutService.calculateTotalAmount(carts));
        return "OrderFrontend/ecpayCheckout";
    }

    @GetMapping("/OrderFrontend/Shopping-Success")
    public String shoppingSuccess(@RequestParam("orderId") String orderId, Model model, HttpSession session) {

        return "OrderFrontend/Shopping-Success";
    }

//    @PostMapping("/backendReturn")
//    public void handleBackendReturn(@RequestParam Map<String, String> params) {
//        System.out.println(params);
//        orderService.checkOrderStatus(params);
//    }
//
//    @GetMapping("/frontendReturn")
//    public String handleFrontendReturn(@RequestParam("orderId")String orderId,Model m) {
//        List<OrderDetailView> orderDetail = orderDetailService.findOrderDetail(orderId);
//        m.addAttribute("orderDetail",orderDetail);
//        // 处理来自绿界的前端重定向
//        return "order/orderPaymentSuccessful";
//    }
}






