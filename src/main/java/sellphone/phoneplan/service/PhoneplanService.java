package sellphone.phoneplan.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import sellphone.phoneplan.model.PhonePlanBean;
import sellphone.phoneplan.model.PhonePlanRepository;

import java.util.List;

@Service
public class PhoneplanService {

    @Autowired
    private PhonePlanRepository phonePlanRepository;

    public void createPhonePlan(PhonePlanBean phonePlan) {
        phonePlanRepository.save(phonePlan);
    }

    public List<PhonePlanBean> findAllPlans() {
        return phonePlanRepository.findAll();
    }

    public PhonePlanBean findPhonePlanById(int id) {
        return phonePlanRepository.findById(id).orElse(null);
    }

    public void save(PhonePlanBean phonePlan) {
        phonePlanRepository.save(phonePlan);
    }

    public void deletePhonePlanById(Integer id) {
        phonePlanRepository.deleteById(id);
    }

    public List<PhonePlanBean> getBestPlans(String telCompany, String planName, String contractType, String generation, String dataUsage) {
        return phonePlanRepository.findByTelCompanyAndPlanNameAndContractTypeAndGenerationAndDataUsage(
                telCompany, planName, contractType, generation, dataUsage);
    }
 
    public List<PhonePlanBean> findPlansByIds(List<Integer> ids) {
        return phonePlanRepository.findAllById(ids);
    }
    
}
