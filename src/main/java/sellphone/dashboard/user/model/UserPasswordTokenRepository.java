package sellphone.dashboard.user.model;

import org.springframework.data.jpa.repository.JpaRepository;


public interface UserPasswordTokenRepository extends JpaRepository<UserPasswordToken, Integer>{

	UserPasswordToken findByToken(String token);

}
