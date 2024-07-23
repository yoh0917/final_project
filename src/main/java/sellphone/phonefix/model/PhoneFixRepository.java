package sellphone.phonefix.model;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface PhoneFixRepository extends JpaRepository<PhoneFixBean,Integer> {

	@Query("select f from PhoneFixBean f where f.user.userId = :userId")
	List<PhoneFixBean> findListById(String userId);
}
