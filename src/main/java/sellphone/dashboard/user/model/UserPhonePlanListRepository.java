package sellphone.dashboard.user.model;


import org.springframework.data.jpa.repository.JpaRepository;


public interface UserPhonePlanListRepository extends JpaRepository<UserPhonePlanList, Integer> {
	
	UserPhonePlanList findByUserId(String userId);

	
}
