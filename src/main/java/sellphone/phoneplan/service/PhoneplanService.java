package sellphone.phoneplan.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import sellphone.phoneplan.model.PhonePlanBean;
import sellphone.phoneplan.model.PhonePlanRepository;
import sellphone.phoneplan.model.UsersRepository;
import sellphone.dashboard.user.model.*;

import java.util.List;

@Service
public class PhoneplanService {

    @Autowired
    private PhonePlanRepository phonePlanRepository;

    public Page<PhonePlanBean> findByFilters(int page, int size, String telCompany, String contractType, String generation, String dataUsage, String planID) {
        Pageable pageable = PageRequest.of(page, size);
        if (telCompany != null && !telCompany.isEmpty()) {
            return phonePlanRepository.findByTelCompany(telCompany, pageable);
        } else if (contractType != null && !contractType.isEmpty()) {
            return phonePlanRepository.findByContractType(contractType, pageable);
        } else if (planID != null && !planID.isEmpty()) {
            return phonePlanRepository.findByPlanIDContaining(planID, pageable);
        } else {
            return phonePlanRepository.findAll(pageable);
        }
    }

    public List<PhonePlanBean> findByFilters(String type, String company, String network) {
        return phonePlanRepository.findByFilters(type, company, network);
    }

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

    public void deletePhonePlanById(int id) {
        phonePlanRepository.deleteById(id);
    }

    public List<PhonePlanBean> findBestPlans(String telCompany, String contractType, String generation, String dataUsage) {
        return phonePlanRepository.findByFilters(telCompany, contractType, generation, dataUsage);
    }
    
  
    
}
