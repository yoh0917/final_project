package sellphone.dashboard.user.model;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


public interface UserRepository extends JpaRepository<Users, String> {
	
	Users findByUserAccount(String userAccount);

	Users findByContactNum(String contactNum);
	
	Users findByEmail(String email);
	
}
