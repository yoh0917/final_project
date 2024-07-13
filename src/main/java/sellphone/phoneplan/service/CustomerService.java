package sellphone.phoneplan.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import sellphone.dashboard.user.model.UserPhonePlanList;
import sellphone.phoneplan.model.UsersRepository;

import java.util.List;

@Service
public class CustomerService {

    @Autowired
    private UsersRepository usersRepository;

    public List<UserPhonePlanList> findAllCustomers() {
        return usersRepository.findAll();
    }

    public List<UserPhonePlanList> findAllByUserId(String userId) {
        return usersRepository.findAllByUserId(userId);
    }

    @Transactional
    public void saveCustomer(UserPhonePlanList customer) {
        usersRepository.save(customer);
    }
}
