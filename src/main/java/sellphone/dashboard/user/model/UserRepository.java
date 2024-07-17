package sellphone.dashboard.user.model;

import org.springframework.data.jpa.repository.JpaRepository;



public interface UserRepository extends JpaRepository<Users, String> {
	
	Users findByUserAccount(String userAccount);

	Users findByContactNum(String contactNum);
	
	Users findByEmail(String email);
	
}
