package sellphone.phoneplan.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import sellphone.phoneplan.model.PhonePlanBean;
import sellphone.phoneplan.service.PhoneplanService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class PhoneplanController {

    @Autowired
    private PhoneplanService phoneplanService;

    
    
    @GetMapping("/DashBoard/phoneplans")
    public String list(@RequestParam(value = "page", defaultValue = "0") int page,
                       @RequestParam(value = "size", defaultValue = "8") int size,
                       @RequestParam(value = "telCompany", required = false, defaultValue = "") String telCompany,
                       @RequestParam(value = "contractType", required = false, defaultValue = "") String contractType,
                       @RequestParam(value = "generation", required = false, defaultValue = "") String generation,
                       @RequestParam(value = "dataUsage", required = false, defaultValue = "") String dataUsage,
                       @RequestParam(value = "planID", required = false, defaultValue = "") String planID,
                       Model model) {
        Page<PhonePlanBean> phonePlansPage = phoneplanService.findByFilters(page, size, telCompany, contractType, generation, dataUsage, planID);
        model.addAttribute("planBeans", phonePlansPage.getContent());
        model.addAttribute("totalPages", phonePlansPage.getTotalPages());
        model.addAttribute("currentPage", phonePlansPage.getNumber());
        model.addAttribute("totalElements", phonePlansPage.getTotalElements());
        model.addAttribute("telCompany", telCompany);
        model.addAttribute("contractType", contractType);
        model.addAttribute("generation", generation);
        model.addAttribute("dataUsage", dataUsage);
        model.addAttribute("planID", planID);
        return "phoneplan/list";
    }

    @GetMapping("/DashBoard/phoneplans/ajax")
    @ResponseBody
    public ResponseEntity<Map<String, Object>> listAjax(@RequestParam(value = "page", defaultValue = "0") int page,
                                                        @RequestParam(value = "size", defaultValue = "8") int size,
                                                        @RequestParam(value = "telCompany", required = false, defaultValue = "") String telCompany,
                                                        @RequestParam(value = "contractType", required = false, defaultValue = "") String contractType,
                                                        @RequestParam(value = "generation", required = false, defaultValue = "") String generation,
                                                        @RequestParam(value = "dataUsage", required = false, defaultValue = "") String dataUsage,
                                                        @RequestParam(value = "planID", required = false, defaultValue = "") String planID) {
        Page<PhonePlanBean> phonePlansPage = phoneplanService.findByFilters(page, size, telCompany, contractType, generation, dataUsage, planID);
        Map<String, Object> response = new HashMap<>();
        response.put("content", phonePlansPage.getContent());
        response.put("totalPages", phonePlansPage.getTotalPages());
        response.put("currentPage", phonePlansPage.getNumber());
        response.put("totalElements", phonePlansPage.getTotalElements());
        return ResponseEntity.ok(response);
    }

    @GetMapping("/DashBoard/phoneplans/create")
    public String createForm(Model model) {
        model.addAttribute("phonePlan", new PhonePlanBean());
        return "phoneplan/form";
    }

    @PostMapping("/DashBoard/phoneplans/create")
    public String create(@ModelAttribute PhonePlanBean phonePlan) {
        phoneplanService.createPhonePlan(phonePlan);  // 使用 createPhonePlan 方法
        return "redirect:/DashBoard/phoneplans";
    }

    @PostMapping("/DashBoard/phoneplans/showFormForUpdate")
    public String showFormForUpdate(@RequestParam("planID") int id, Model model) {
        PhonePlanBean phonePlan = phoneplanService.findPhonePlanById(id);
        if (phonePlan == null) {
            throw new IllegalArgumentException("Invalid phone plan ID:" + id);
        }
        model.addAttribute("planBean", phonePlan);
        return "phoneplan/updateForm";
    }

    @PostMapping("/DashBoard/phoneplans/update")
    public String update(@ModelAttribute("planBean") PhonePlanBean phonePlan) {
        phoneplanService.save(phonePlan);
        return "redirect:/DashBoard/phoneplans";
    }
    @DeleteMapping("/DashBoard/phoneplans/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") int id) {
        phoneplanService.deletePhonePlanById(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/DashBoard/phoneplans/bestPlan")
    public String showBestPlanForm() {
        return "phoneplan/choose";  // 確保這裡的名稱與你的模板文件名一致
    }

    @GetMapping("/api/getBestPlan")
    @ResponseBody
    public List<PhonePlanBean> getBestPlan(@RequestParam String telCompany,
                                           @RequestParam String contractType,
                                           @RequestParam String generation,
                                           @RequestParam String dataUsage) {
        return phoneplanService.findBestPlans(telCompany, contractType, generation, dataUsage);
    }
}