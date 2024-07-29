package sellphone.cart.controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import sellphone.cart.service.CheckoutService;
import sellphone.orders.model.Order;

@RestController
@RequestMapping("/api/checkout")
public class PaymentController {

    @Autowired
    private CheckoutService checkoutService;

    @PostMapping("/checkout")
    public ResponseEntity<?> handleCheckout(@RequestBody Order order) {
        try {
            String paymentForm = checkoutService.ecpayCheckout(String.valueOf(order));
            return ResponseEntity.ok(paymentForm); // 返回綠界的支付表單
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error: " + e.getMessage());
        }
    }
}






