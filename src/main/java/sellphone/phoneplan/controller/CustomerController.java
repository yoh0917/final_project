package sellphone.phoneplan.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import sellphone.phoneplan.model.PhonePlanBean;
import sellphone.phoneplan.service.CustomerService;
import sellphone.dashboard.user.model.UserPhonePlanList;
import sellphone.dashboard.user.model.UserPhonePlanListRepository;
import sellphone.phoneplan.service.PhoneplanService;
import sellphone.dashboard.user.model.Users;
import sellphone.phoneplan.model.UsersRepository;

import java.time.LocalDateTime;
import java.util.List;

@Controller
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @Autowired
    private PhoneplanService phoneplanService;

    @Autowired
    private UsersRepository usersRepository;
    
    @Autowired
    private UserPhonePlanListRepository userPPPLR;

    @GetMapping("/DashBoard/customers/create")
    public String createCustomerForm(Model model) {
        List<PhonePlanBean> plans = phoneplanService.findAllPlans();
        model.addAttribute("plans", plans);
        model.addAttribute("customer", new Users());
        return "phoneplan/customers"; 
    }
//    @SessionAttribute("userId") String userId
    @PostMapping("/DashBoard/customers/create")
    public String createCustomer(@ModelAttribute UserPhonePlanList customer, @RequestParam("selectedPlan") int selectedPlanId, @RequestParam("phoneNum") String phoneNum) {
        
    	PhonePlanBean selectedPlan = phoneplanService.findPhonePlanById(selectedPlanId);
        Users user = usersRepository.findByUserId("2406140003");
        
        UserPhonePlanList userPPL = new UserPhonePlanList();
        userPPL.setPhonePlanBean(selectedPlan);
        userPPL.setUser(user);
        userPPL.setPhoneNum(phoneNum);
        userPPPLR.save(userPPL);
        
        
        
//        Users user = usersRepository.findById(customer.getUserId()).orElse(null);
//        if (user == null) {
//            // 如果用户不存在，创建用户
//            user = new Users();
//            user.setUserId(customer.getUserId());
//            user.setUserName(customer.getUserName());
//            user.setUserAccount(customer.getUserAccount());
//            user.setPassword(customer.getPassword());
//            user.setEmail(customer.getEmail());
//            user.setContactNum(customer.getContactNum());
//            if (customer.getBirthday() != null) {
//                user.setBirthday(customer.getBirthday().toLocalDate());
//            }
//            user.setCreateTime(LocalDateTime.now()); // 设置创建时间
//            usersRepository.save(user);
//        }
//
//        // 插入 UserPhonePlanList
//        customer.getPhonePlans().add(selectedPlan);
//        selectedPlan.getUserPhonePlanLists().add(customer);
//        customerService.saveCustomer(customer);

        return "redirect:/DashBoard/customers/create";
    }
}
