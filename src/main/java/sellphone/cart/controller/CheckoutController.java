package sellphone.cart.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import sellphone.cart.model.CartView;
import sellphone.orders.model.Order;
import sellphone.orders.model.OrderDetail;
import sellphone.cart.service.CheckoutService;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/orders")
public class CheckoutController {

    @Autowired
    private CheckoutService checkoutService;

    @GetMapping("/checkout1")
    public String checkoutPage(Model model, HttpSession session) {
        String userId = (String) session.getAttribute("UserId");
        if (userId == null) {
            userId = "DefaultUserId"; // 確保有一個默認值，如果session中沒有UserId
        }
        List<CartView> carts = checkoutService.getCartByUserId(userId);
        model.addAttribute("carts", carts);
        model.addAttribute("totalAmount", checkoutService.calculateTotalAmount(carts));
        return "OrderFrontend/Checkout1";
    }

    @PostMapping("/checkout1")
    public String checkout(@RequestBody Map<String, Object> requestData, Model model, HttpSession session) {
        String userId = (String) session.getAttribute("UserId");
        if (userId == null) {
            userId = "DefaultUserId"; // 確保有一個默認值，如果session中沒有UserId
        }
        Order order = checkoutService.createOrder(requestData, userId);
        List<OrderDetail> orderDetails = checkoutService.createOrderDetails(requestData, order.getOrderId());
        model.addAttribute("order", order);
        model.addAttribute("orderDetails", orderDetails);
        return "OrderFrontend/Checkout1";
    }

    @PostMapping("/save")
    public String saveOrder(@RequestBody Map<String, Object> requestData, Model model) {
        try {
            checkoutService.saveOrder(requestData);
            model.addAttribute("message", "已成功插入資料庫");
        } catch (RuntimeException e) {
            model.addAttribute("message", "插入資料庫失敗: " + e.getMessage());
        }
        return "OrderFrontend/Checkout1";
    }



//    @PostMapping("/save")
//    @ResponseBody
//    public String saveOrder(@RequestBody Order order, @RequestBody List<OrderDetail> orderDetails, HttpSession session) {
//        // 設定訂單的基本資料
//        order.setOrderId(checkoutService.generateOrderId());  // 現在可以調用 generateOrderId()
//        order.setCreateDate(new Date());
//
//        // 保存訂單和訂單明細
//        checkoutService.saveOrder(order, orderDetails);
//        return "訂單已保存";
//    }
//
//    @GetMapping("/checkout1")
//    public String checkoutPage(Model model, HttpSession session) {
//        List<CartView> carts = (List<CartView>) session.getAttribute("carts");
//        model.addAttribute("carts", carts);
//
//        // 計算總計
//        int totalAmount = carts.stream().mapToInt(cart -> cart.getQuantity() * cart.getPrice()).sum();
//        model.addAttribute("totalAmount", totalAmount);
//
//        model.addAttribute("userId", carts.get(0).getUserId());
//        return "OrderFrontend/Checkout1";
//    }

}










//    //查詢所有訂單
//    @GetMapping
//    public String getOrders(Model model) {
//        List<Order> orders = ordersService.getAllOrders();
//        model.addAttribute("orders", orders);
//        return "OrderBackend/Orders";
//    }
//
//    //查詢訂單明細
//    @GetMapping("/details/{orderId}")
//    @ResponseBody
//    public List<OrderDetail> getOrderDetails(@PathVariable String orderId) {
//        return ordersService.getOrderDetailsByOrderId(orderId);
//    }
//
//    // 更新訂單
//    @PostMapping("/update")
//    public ResponseEntity<?> updateOrder(@RequestBody Order order) {
//        try {
//            ordersService.updateOrder(order);
//            return ResponseEntity.ok("更新成功");
//        } catch (Exception e) {
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("更新失敗");
//        }
//    }
//
//    // 新增訂單頁面
//    @GetMapping("/add")
//    public String addOrderForm(Model model) {
//        model.addAttribute("order", new Order());
//        return "OrderBackend/AddOrder";
//    }
//
//    @PostMapping("/add")
//    @ResponseBody
//    public ResponseEntity<Map<String, Object>> addOrder(@RequestBody Order order) {
//        Map<String, Object> response = new HashMap<>();
//        try {
//            ordersService.saveOrder(order);
//            response.put("success", true);
//            response.put("message", "新增成功");
//            return ResponseEntity.ok(response);
//        } catch (Exception e) {
//            response.put("success", false);
//            response.put("message", e.getMessage());
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
//        }
//    }
//
//    // 刪除訂單（假刪除，更新訂單狀態為 "D"）
//    @PostMapping("/delete")
//    @ResponseBody
//    public String deleteOrder(@RequestBody Map<String, String> request) {
//        String orderId = request.get("orderId");
//        ordersService.deleteOrder(orderId);
//        return "Order marked as deleted";
//    }
//
//    //DashboardOrder
//    @GetMapping("/revenue")
//    public String revenueAnalysis() {
//        return "OrderBackend/DashboardOrder";
//    }
//
//    //購物車
//    @GetMapping("/cart1")
//    public String cartPage() {
//        return "OrderFrontend/Cart1";
//    }
//
//    //結帳
//    @GetMapping("/checkout1")
//    public String checkoutPage() {
//        return "OrderFrontend/Checkout1";
//    }
//
//    //測試資料
//    @GetMapping("/testproduct")
//    @ResponseBody
//    public List<Map<String, Object>> getOrderDetails() {
//        return Arrays.asList(
//                Map.of(
//                        "顧客ID", "P00000288",
//                        "總計", 41503,
//                        "商品明細", Arrays.asList(
//                                Map.of("產品ID", 39, "數量", 1, "價格", 40160, "總計", 40160),
//                                Map.of("產品ID", 33, "數量", 1, "價格", 5917, "總計", 5917),
//                                Map.of("產品ID", 26, "數量", 1, "價格", 42881, "總計", 42881)
//                        )
//                )
//        );
//    }

