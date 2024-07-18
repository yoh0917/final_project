package sellphone.phoneplan.model;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sellphone.dashboard.user.model.Users;

@Repository
public interface UsersRepository extends JpaRepository<Users, String> {
    Users findByUserId(String userId);
    boolean existsByUserId(String userId);
}
