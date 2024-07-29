package sellphone.admin.service;


import org.springframework.data.jpa.repository.JpaRepository;



public interface AdminRepository extends JpaRepository<Admin, Integer> {

	Admin findByAdminAccount(String account);

	Admin findByAdminPassword(String password);

}
