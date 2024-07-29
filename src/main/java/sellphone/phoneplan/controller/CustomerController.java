package sellphone.phoneplan.controller;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import sellphone.phoneplan.model.PhonePlanBean;
import sellphone.phoneplan.model.SmsService;
import sellphone.phoneplan.service.CustomerService;
import sellphone.user.model.UserPhonePlanList;
import sellphone.user.model.UserPhonePlanListRepository;
import sellphone.user.model.Users;
import sellphone.phoneplan.model.UsersRepository;

import java.io.File;
import java.nio.file.FileSystems;
import java.nio.file.Path;
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

    @Autowired
    private SmsService smsService;

    @GetMapping("/DashBoard/customers/create")
    public String createCustomerForm(Model model) {
        List<PhonePlanBean> plans = customerService.findAllPlans();
        model.addAttribute("plans", plans);
        model.addAttribute("customer", new UserPhonePlanList());
        return "phoneplan/customers";
    }

    @PostMapping("/DashBoard/customers/create")
    public String createCustomer(@RequestParam("selectedPlan") int selectedPlanId,
                                 @RequestParam("phoneNum") String phoneNum) throws Exception {
        PhonePlanBean selectedPlan = customerService.findPhonePlanById(selectedPlanId);
        Users user = usersRepository.findByUserId("2406140003");

        UserPhonePlanList userPPL = new UserPhonePlanList();
        userPPL.setPhonePlan(selectedPlan);
        userPPL.setUser(user);
        userPPL.setPhoneNum(phoneNum);
        userPPL.setAgreementDate(LocalDateTime.now().toString());

        String qrCodeText = "http://localhost:8081/sellphone/planDetail?plan=" + selectedPlan.getPlanName() + "&phoneNum=" + phoneNum;
        String filePath = "./src/main/resources/static/qr-codes/" + userPPL.getPlanID() + ".png";
        generateQRCode(qrCodeText, filePath);
        userPPL.setQrCodePath("/qr-codes/" + userPPL.getPlanID() + ".png");

        customerService.saveCustomer(userPPL);

        // 發送簡訊
        String message = String.format("您已成功申辦方案:\n方案名稱: %s\n電信公司: %s\n合約類型: %s\n詳情請查看: %s", 
                                       selectedPlan.getPlanName(), selectedPlan.getTelCompany(), selectedPlan.getContractType(), qrCodeText);
        smsService.sendSMS(phoneNum, message);

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

    private void generateQRCode(String text, String filePath) throws Exception {
        File directory = new File("./src/main/resources/static/qr-codes");
        if (!directory.exists()) {
            directory.mkdirs();
        }

        BitMatrix matrix = new MultiFormatWriter().encode(text, BarcodeFormat.QR_CODE, 250, 250);
        Path path = FileSystems.getDefault().getPath(filePath);
        MatrixToImageWriter.writeToPath(matrix, "PNG", path);
    }

    @GetMapping("/planDetail")
    public String showPlanDetail(@RequestParam("plan") String planName, @RequestParam("phoneNum") String phoneNum, Model model) {
        PhonePlanBean plan = customerService.findPhonePlansByName(planName).stream().findFirst().orElse(null);
        if (plan == null) {
            return "error"; 
        }
        model.addAttribute("plan", plan);
        model.addAttribute("phoneNum", phoneNum);
        return "phoneplan/planDetail";
    }
}
