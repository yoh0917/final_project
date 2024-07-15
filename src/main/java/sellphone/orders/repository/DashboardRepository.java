package sellphone.orders.repository;

import sellphone.orders.model.Dashboard;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DashboardRepository extends JpaRepository<Dashboard, String> {
}