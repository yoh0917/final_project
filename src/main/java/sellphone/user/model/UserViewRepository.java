package sellphone.user.model;


import org.springframework.data.jpa.repository.JpaRepository;



public interface UserViewRepository extends JpaRepository<UserView, String> {

	UserView findByUserId(String userId);
}
