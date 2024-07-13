package sellphone.phoneplan.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import sellphone.phoneplan.model.PhonePlanBean;
import sellphone.phoneplan.service.CustomerService;
import sellphone.dashboard.user.model.UserPhonePlanList;
import sellphone.phoneplan.service.PhoneplanService;


import java.util.List;

@Controller
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @Autowired
    private PhoneplanService phoneplanService;

    @GetMapping("/DashBoard/customers/create")
    public String createCustomerForm(Model model) {
        List<PhonePlanBean> plans = phoneplanService.findAllPlans();
        model.addAttribute("plans", plans);
        model.addAttribute("customer", new UserPhonePlanList());
        return "phoneplan/customers"; // 使用正确的模板路径
    }

    @PostMapping("/DashBoard/customers/create")
    public String createCustomer(@ModelAttribute UserPhonePlanList customer, @RequestParam("selectedPlan") int selectedPlanId) {
        PhonePlanBean selectedPlan = phoneplanService.findPhonePlanById(selectedPlanId);
        customer.getPhonePlans().add(selectedPlan);
        selectedPlan.getUserPhonePlanLists().add(customer);
        customerService.saveCustomer(customer);
        return "redirect:/DashBoard/customers/create";
    }

}
