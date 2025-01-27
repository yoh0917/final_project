package sellphone.orders.controller;

import sellphone.orders.model.Order;
import sellphone.orders.model.OrderDetail;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import sellphone.orders.service.OrdersService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;

import java.util.*;

@Controller
@RequestMapping("/DashBoard/orders")
public class OrdersController {

    @Autowired
    private OrdersService ordersService;
//
//    @PostMapping("/save")
//    @ResponseBody
//    public String saveOrder(@RequestBody Order order, @RequestBody List<OrderDetail> orderDetails, HttpSession session) {
//        // 設定訂單的基本資料
//        order.setOrderId(ordersService.generateOrderId());  // 現在可以調用 generateOrderId()
//        order.setCreateDate(new Date());
//
//        // 保存訂單和訂單明細
//        ordersService.saveOrder(order, orderDetails);
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


    //查詢所有訂單
    @GetMapping
    public String getOrders(Model model) {
        List<Order> orders = ordersService.getAllOrders();
        model.addAttribute("orders", orders);
        return "OrderBackend/Orders";
    }

    //查詢訂單明細
    @GetMapping("/details/{orderId}")
    @ResponseBody
    public List<OrderDetail> getOrderDetails(@PathVariable String orderId) {
        return ordersService.getOrderDetailsByOrderId(orderId);
    }

    // 更新訂單
    @PostMapping("/update")
    public ResponseEntity<?> updateOrder(@RequestBody Order order) {
        try {
            ordersService.updateOrder(order);
            return ResponseEntity.ok("更新成功");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("更新失敗");
        }
    }

    // 新增訂單頁面
    @GetMapping("/add")
    public String addOrderForm(Model model) {
        model.addAttribute("order", new Order());
        return "OrderBackend/AddOrder";
    }

    @PostMapping("/add")
    @ResponseBody
    public ResponseEntity<Map<String, Object>> addOrder(@RequestBody Order order) {
        Map<String, Object> response = new HashMap<>();
        try {
            ordersService.saveOrder(order);
            response.put("success", true);
            response.put("message", "新增成功");
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            response.put("success", false);
            response.put("message", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    // 刪除訂單（假刪除，更新訂單狀態為 "D"）
    @PostMapping("/delete")
    @ResponseBody
    public String deleteOrder(@RequestBody Map<String, String> request) {
        String orderId = request.get("orderId");
        ordersService.deleteOrder(orderId);
        return "Order marked as deleted";
    }

    //DashboardOrder
    @GetMapping("/revenue")
    public String revenueAnalysis() {
        return "OrderBackend/DashboardOrder";
    }

    //購物車
    @GetMapping("/cart1")
    public String cartPage() {
        return "OrderFrontend/Cart1";
    }

    //結帳
    @GetMapping("/checkout1")
    public String checkoutPage() {
        return "OrderFrontend/Checkout1";
    }

    //測試資料
    @GetMapping("/testproduct")
    @ResponseBody
    public List<Map<String, Object>> getOrderDetails() {
        return Arrays.asList(
                Map.of(
                        "顧客ID", "P00000288",
                        "總計", 41503,
                        "商品明細", Arrays.asList(
                                Map.of("產品ID", 39, "數量", 1, "價格", 40160, "總計", 40160),
                                Map.of("產品ID", 33, "數量", 1, "價格", 5917, "總計", 5917),
                                Map.of("產品ID", 26, "數量", 1, "價格", 42881, "總計", 42881)
                        )
                )
        );
    }

}
