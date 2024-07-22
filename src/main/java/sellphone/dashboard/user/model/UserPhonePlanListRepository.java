package sellphone.dashboard.user.model;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import sellphone.phoneplan.model.PhonePlanBean;


public interface UserPhonePlanListRepository extends JpaRepository<UserPhonePlanList, Integer> {
	
	UserPhonePlanList findByUserId(String userId);
	
	List<UserPhonePlanList> findAllByuserId(String userId);
    List<UserPhonePlanList> findByPhonePlanAndUser(PhonePlanBean phonePlan, Users user);

	List<UserPhonePlanList> findByUser(Users user);

	
}
