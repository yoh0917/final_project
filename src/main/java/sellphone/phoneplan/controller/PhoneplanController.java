package sellphone.phoneplan.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import sellphone.phoneplan.model.PhonePlanBean;
import sellphone.phoneplan.service.ExportService;
import sellphone.phoneplan.service.PhoneplanService;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class PhoneplanController {

    @Autowired
    private PhoneplanService phoneplanService;

    @Autowired
    private ExportService exportService;

    @GetMapping("/DashBoard/phoneplans/all")
    public String list(Model model) {
        List<PhonePlanBean> phonePlans = phoneplanService.findAllPlans();
        model.addAttribute("phonePlans", phonePlans);
        return "phoneplan/list";
    }

    @GetMapping("/DashBoard/phoneplans/create")
    public String createForm(Model model) {
        model.addAttribute("phonePlan", new PhonePlanBean());
        return "phoneplan/form";
    }

    @PostMapping("/DashBoard/phoneplans/create")
    public String create(@ModelAttribute PhonePlanBean phonePlan) {
        phoneplanService.createPhonePlan(phonePlan);
        return "redirect:/DashBoard/phoneplans/all";
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
        return "redirect:/DashBoard/phoneplans/all";
    }

    @DeleteMapping("/DashBoard/phoneplans/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") int id) {
        phoneplanService.deletePhonePlanById(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/getBestPlan")
    public ResponseEntity<List<PhonePlanBean>> getBestPlan(
            @RequestParam String telCompany,
            @RequestParam String planName,
            @RequestParam String contractType,
            @RequestParam String generation,
            @RequestParam String dataUsage) {
        List<PhonePlanBean> bestPlans = phoneplanService.getBestPlans(telCompany, planName, contractType, generation, dataUsage);
        return ResponseEntity.ok(bestPlans);
    }

    @GetMapping("/phoneplans/export/excel")
    public ResponseEntity<byte[]> exportToExcel(@RequestParam("planIDs") String planIDs) throws IOException {
        List<Integer> planIDList = Arrays.stream(planIDs.split(",")).map(Integer::parseInt).collect(Collectors.toList());
        List<PhonePlanBean> selectedPlans = phoneplanService.findPlansByIds(planIDList);
        byte[] bytes = exportService.exportPhonePlansToExcel(selectedPlans);

        HttpHeaders headers = new HttpHeaders();
        headers.set(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=phone_plans.xlsx");
        return ResponseEntity.ok()
                .headers(headers)
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .body(bytes);
    }
}
