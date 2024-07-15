package sellphone.phoneplan.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import sellphone.phoneplan.model.PhonePlanBean;
import sellphone.phoneplan.service.BestPlanService;

import java.util.List;

@Controller
public class BestChooseController {

    @Autowired
    private BestPlanService bestPlanService;

    @GetMapping("/DashBoard/phoneplans")
    public String showBestPlanForm() {
        return "phoneplan/choose"; // 確保這裡的名稱與你的模板文件名一致
    }

    @GetMapping("/DashBoard/phoneplans/getBestPlan")
    @ResponseBody
    public List<PhonePlanBean> getBestPlan(@RequestParam String telCompany, @RequestParam String planName,
                                           @RequestParam String contractType, @RequestParam String generation, @RequestParam String dataUsage) {
        return bestPlanService.findBestPlans(telCompany, planName, contractType, generation, dataUsage);
    }

    @GetMapping("/DashBoard/phoneplans/getOptions")
    @ResponseBody
    public List<String> getOptions(@RequestParam String currentId, @RequestParam String currentValue) {
        switch (currentId) {
            case "telCompany":
                return bestPlanService.findPlanNamesByTelCompany(currentValue);
            case "planName":
                return bestPlanService.findContractTypesByPlanName(currentValue);
            case "contractType":
                return bestPlanService.findGenerationsByContractType(currentValue);
            case "generation":
                return bestPlanService.findDataUsagesByGeneration(currentValue);
            default:
                throw new IllegalArgumentException("Invalid parameter: " + currentId);
        }
    }
}
