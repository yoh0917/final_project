package sellphone.cart.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import sellphone.cart.model.CartView;
import sellphone.cart.service.CheckoutService;
import sellphone.orders.model.Order;
import sellphone.orders.model.OrderDetail;
import sellphone.user.model.Users;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/orders")
public class CheckoutTableController {

    @Autowired
    private CheckoutService checkoutService;

    @PostMapping("/saveOrder")
    @ResponseBody
    public String saveOrder(@ModelAttribute Order order, HttpSession session, Model model) {
        //存訂單
        System.out.println(1);
        Users user = (Users) session.getAttribute("user");
        String userId = user.getUserId();
        String userName = user.getUserName();

        order.setUserName(userName);
        order.setUserId(userId);
        Order order1 = checkoutService.saveOrder(order);
        //存訂單明細
        System.out.println(2);
        String orderId = order1.getOrderId();
        checkoutService.saveOrderDetails(orderId, userId);

        // 刪除購物車資料
//        System.out.println(3);
//        String userId = user.getUserId();
//        checkoutService.deleteCartByUserId(userId);
        boolean cartDeleted = deleteUserCart(userId);


        //ECPay 結帳
        System.out.println(4);
        String aioCheckOutALLForm = checkoutService.ecpayCheckout(orderId,userId);
        model.addAttribute("aioCheckOutALLForm", aioCheckOutALLForm);
        System.out.println(5);
        return aioCheckOutALLForm;
    }

    private boolean deleteUserCart(String userId) {
        try {
            checkoutService.deleteCartByUserId(userId);
            return true;
        } catch (Exception e) {
            // 記錄錯誤，可能需要後續處理
            return false;
        }
    }

//    @PostMapping("/deleteCart")
//    @ResponseBody
//    public ResponseEntity<String> deleteCart(HttpSession session) {
//        Users user = (Users) session.getAttribute("user");
//        String userId = user.getUserId();
//
//        try {
//            checkoutService.deleteCartByUserId(userId);
//            return ResponseEntity.ok("購物車已清空");
//        } catch (Exception e) {
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("清空購物車失敗");
//        }
//    }

//    @PostMapping("/deleteCart")
//    @ResponseBody
//    public String deleteCart(HttpSession session) {
//        Users user = (Users) session.getAttribute("user");
//        String userId = user.getUserId();
//
//        // 刪除購物車資料
//        checkoutService.deleteCartByUserId(userId);
//
//        return "購物車已清空";
//    }

//    @PostMapping("/saveOrderDetails")
//    public Map<String, Object> saveOrderDetails(@RequestBody Map<String, String> request, HttpSession session) {
//        Map<String, Object> response = new HashMap<>();
//        try {
//            String orderId = request.get("orderId");
//            Users user = (Users) session.getAttribute("user");
//            if (user == null) {
//                response.put("success", false);
//                response.put("message", "User not found in session.");
//                return response;
//            }
//
//            String userId = user.getUserName();
//            checkoutService.saveOrderDetails(orderId, userId);
//            response.put("success", true);
//        } catch (Exception e) {
//            response.put("success", false);
//            response.put("message", e.getMessage());
//        }
//        return response;
//    }
//    @PostMapping("/save")
//    public Map<String, Object> saveOrder(@RequestBody Order order, HttpSession session) {
//        Map<String, Object> response = new HashMap<>();
//        String userId = (String) session.getAttribute("userId");
//
//        if (userId == null) {
//            response.put("success", false);
//            response.put("message", "User not logged in");
//            return response;
//        }
//
//        try {
//            checkoutService.saveOrderAndDetails(order, userId);
//            response.put("success", true);
//        } catch (Exception e) {
//            response.put("success", false);
//            response.put("message", e.getMessage());
//        }
//        return response;
//    }
}


//    public Map<String, Object> saveOrder(@RequestBody Map<String, Object> payload) {
//        Map<String, Object> response = new HashMap<>();
//        try {
//            Order order = new ObjectMapper().convertValue(payload.get("order"), Order.class);
//            checkoutService.saveOrderAndDetails(order);
//            response.put("success", true);
//        } catch (Exception e) {
//            response.put("success", false);
//            response.put("message", e.getMessage());
//        }
//        return response;
//    }

//    @PostMapping("/save")
//    public String saveOrder(@RequestBody Map<String, Object> requestData, Model model) {
//        try {
//            checkoutService.saveOrder(requestData);
//            model.addAttribute("message", "已成功插入資料庫");
//        } catch (RuntimeException e) {
//            model.addAttribute("message", "插入資料庫失敗: " + e.getMessage());
//        }
//        return "OrderFrontend/Checkout1";
//    }


