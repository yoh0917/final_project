package sellphone.phoneplan.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import sellphone.dashboard.user.model.UserRepository;
import sellphone.dashboard.user.model.Users;
import sellphone.phoneplan.model.PhonePlanBean;
import sellphone.phoneplan.model.PhonePlanRepository;


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
    @Autowired
    private UserRepository usersRepository;

    @Transactional
    public PhonePlanBean addPhonePlanToUser(String userId, PhonePlanBean phonePlanBean) {
        System.out.println("Looking for user with ID: " + userId);
        Users user = usersRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        System.out.println("User found: " + user.getUserName());
        
//        phonePlanBean.setUsers(user);
        return phonePlanRepository.save(phonePlanBean);
    }
}
