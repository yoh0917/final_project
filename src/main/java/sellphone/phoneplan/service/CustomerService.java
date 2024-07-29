package sellphone.phoneplan.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import sellphone.phoneplan.model.PhonePlanBean;
import sellphone.phoneplan.model.PhonePlanRepository;
import sellphone.user.model.UserPhonePlanList;
import sellphone.user.model.UserPhonePlanListRepository;

@Service
public class CustomerService {

    @Autowired
    private PhonePlanRepository phonePlanRepository;

    @Autowired
    private UserPhonePlanListRepository userPhonePlanListRepository;

    @Transactional
    public void saveCustomer(UserPhonePlanList customer) {
        userPhonePlanListRepository.save(customer);
    }

    @Transactional
    public void savePhonePlan(PhonePlanBean phonePlan) {
        phonePlanRepository.save(phonePlan);
    }

    public PhonePlanBean findPhonePlanById(int id) {
        return phonePlanRepository.findById(id).orElse(null);
    }

    public List<PhonePlanBean> findAllPlans() {
        return phonePlanRepository.findAll();
    }
    public List<PhonePlanBean> findPhonePlansByName(String planName) {
        return phonePlanRepository.findByPlanName(planName);
    }
    
    
}
