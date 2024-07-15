package sellphone.phoneplan.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sellphone.dashboard.user.model.UserPhonePlanList;
import sellphone.dashboard.user.model.Users;
import sellphone.dashboard.user.model.UserPhonePlanListRepository;
import sellphone.phoneplan.model.UsersRepository;

@Service
public class CustomerService {

    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private UserPhonePlanListRepository userPhonePlanListRepository;

    @Transactional
    public void saveCustomer(UserPhonePlanList customer) {
//        Users user = usersRepository.findById(customer.getUserId()).orElseThrow(() -> new IllegalArgumentException("User ID does not exist in the Users table"));
//        customer.setUser(user);
//        userPhonePlanListRepository.save(customer);
    }
}
