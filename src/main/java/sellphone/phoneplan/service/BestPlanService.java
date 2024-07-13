package sellphone.phoneplan.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sellphone.phoneplan.model.PhonePlanBean;
import sellphone.phoneplan.model.BestPlanRepository;

import java.util.List;

@Service
public class BestPlanService {

    @Autowired
    private BestPlanRepository bestPlanRepository;

    public List<PhonePlanBean> findBestPlans(String telCompany, String planName, String contractType, String generation, String dataUsage) {
        return bestPlanRepository.findBestPlans(telCompany, planName, contractType, generation, dataUsage);
    }

    public List<String> findPlanNamesByTelCompany(String telCompany) {
        return bestPlanRepository.findDistinctPlanNamesByTelCompany(telCompany);
    }

    public List<String> findContractTypesByPlanName(String planName) {
        return bestPlanRepository.findDistinctContractTypesByPlanName(planName);
    }

    public List<String> findGenerationsByContractType(String contractType) {
        return bestPlanRepository.findDistinctGenerationsByContractType(contractType);
    }

    public List<String> findDataUsagesByGeneration(String generation) {
        return bestPlanRepository.findDistinctDataUsagesByGeneration(generation);
    }
}
