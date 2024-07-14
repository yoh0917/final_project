package sellphone.phoneplan.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import sellphone.phoneplan.model.PhonePlanBean;
import sellphone.phoneplan.service.CustomerService;
import sellphone.dashboard.user.model.UserPhonePlanList;
import sellphone.dashboard.user.model.Users;
import sellphone.dashboard.user.service.UserService;
import sellphone.phoneplan.service.PhoneplanService;

import java.util.List;

@Controller
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @Autowired
    private PhoneplanService phoneplanService;

    @Autowired
    private UserService userService;

    @GetMapping("/DashBoard/customers/create")
    public String createCustomerForm(Model model) {
        List<PhonePlanBean> plans = phoneplanService.findAllPlans();
        model.addAttribute("plans", plans);
        UserPhonePlanList userPhonePlanList = new UserPhonePlanList();
        model.addAttribute("customer", userPhonePlanList);
        
        // 假设在这里我们有一个硬编码的 userId（实际应用中应从登录用户或上下文中获取）
        String userId = "someUserId";
        model.addAttribute("userId", userId);
        
        return "phoneplan/customers";
    }

    @PostMapping("/DashBoard/customers/create")
    public String createCustomer(@ModelAttribute UserPhonePlanList customer, @RequestParam("selectedPlan") int selectedPlanId, @RequestParam("userId") String userId) {
        PhonePlanBean selectedPlan = phoneplanService.findPhonePlanById(selectedPlanId);
        Users user = userService.findById(userId);

        if (user == null) {
            // 用户不存在，处理错误
            return "redirect:/DashBoard/customers/create?error=UserNotFound";
        }

        customer.setUser(user); // 设置用户对象
        customer.setUserId(userId); // 设置用户ID
        customer.setPhonePlanBean(selectedPlan); // 确保 phonePlanBean 被设置

        customerService.saveCustomer(customer);
        selectedPlan.addUserPhonePlanList(customer); // 使用 addUserPhonePlanList 方法
        phoneplanService.createPhonePlan(selectedPlan);
        return "redirect:/DashBoard/customers/create";
    }
}
