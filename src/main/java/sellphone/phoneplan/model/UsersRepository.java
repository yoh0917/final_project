package sellphone.phoneplan.model;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sellphone.dashboard.user.model.UserPhonePlanList;

@Repository
public interface UsersRepository extends JpaRepository<UserPhonePlanList, Long> {
    List<UserPhonePlanList> findAllByUserId(String userId);
}
