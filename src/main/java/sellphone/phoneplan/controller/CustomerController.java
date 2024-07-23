package sellphone.phoneplan.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import sellphone.phoneplan.model.PhonePlanBean;
import sellphone.phoneplan.service.CustomerService;
import sellphone.user.model.UserPhonePlanList;
import sellphone.user.model.UserPhonePlanListRepository;
import sellphone.user.model.Users;
import sellphone.phoneplan.model.UsersRepository;

import java.time.LocalDateTime;
import java.util.List;

@Controller
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private UserPhonePlanListRepository userPPPLR;

    @GetMapping("/DashBoard/customers/create")
    public String createCustomerForm(Model model) {
        List<PhonePlanBean> plans = customerService.findAllPlans();
        model.addAttribute("plans", plans);
        model.addAttribute("customer", new UserPhonePlanList());
        return "phoneplan/customers";
    }

    @PostMapping("/DashBoard/customers/create")
    public String createCustomer(@RequestParam("selectedPlan") int selectedPlanId,
                                 @RequestParam("phoneNum") String phoneNum) {
        PhonePlanBean selectedPlan = customerService.findPhonePlanById(selectedPlanId);
        Users user = usersRepository.findByUserId("2406140003");

        UserPhonePlanList userPPL = new UserPhonePlanList();
        userPPL.setPhonePlan(selectedPlan);
        userPPL.setUser(user);
        userPPL.setPhoneNum(phoneNum);
        userPPL.setAgreementDate(LocalDateTime.now().toString());

        customerService.saveCustomer(userPPL);

        return "redirect:/DashBoard/customers/plans";
    }

    @GetMapping("/DashBoard/customers/plans")
    public String viewCustomerPlans(Model model) {
        Users user = usersRepository.findByUserId("2406140003");
        if (user == null) {
            return "error";
        }

        List<UserPhonePlanList> plans = userPPPLR.findByUser(user);
        model.addAttribute("plans", plans);

        return "phoneplan/viewPlans";
    }

    @PostMapping("/DashBoard/customers/update")
    public String showFormForUpdate(@RequestParam("userPhonePlanID") int userPhonePlanID, Model model) {
        UserPhonePlanList userPPL = userPPPLR.findById(userPhonePlanID).orElse(null);

        if (userPPL != null) {
            model.addAttribute("userPhonePlanID", userPhonePlanID);
            model.addAttribute("phonePlan", userPPL.getPhonePlan());
            model.addAttribute("phoneNum", userPPL.getPhoneNum());
        } else {
            model.addAttribute("error", "找不到");
            return "phoneplan/viewPlans"; 
        }

        List<PhonePlanBean> plans = customerService.findAllPlans();
        model.addAttribute("plans", plans);

        return "phoneplan/customersupdateForm";
    }

    @PostMapping("/DashBoard/customers/saveUpdate")
    public String saveUpdate(@RequestParam("userPhonePlanID") int userPhonePlanID,
                             @RequestParam("planName") String planName, 
                             @RequestParam("phoneNum") String phoneNum,
                             Model model) {
        UserPhonePlanList userPPL = userPPPLR.findById(userPhonePlanID).orElse(null);

        if (userPPL != null) {
            // 找到新的 phone plan
            List<PhonePlanBean> newPhonePlanList = customerService.findPhonePlansByName(planName);
            if (newPhonePlanList.isEmpty()) {
                model.addAttribute("error", "找不到");
                return "phoneplan/customersupdateForm";
            }
            PhonePlanBean newPhonePlan = newPhonePlanList.get(0);
            
            userPPL.setPhonePlan(newPhonePlan);
            userPPL.setPhoneNum(phoneNum);
            userPPPLR.save(userPPL); 
        } else {
            model.addAttribute("error", "找不到");
            return "phoneplan/customersupdateForm";
        }

        return "redirect:/DashBoard/customers/plans";
    }

  
}
