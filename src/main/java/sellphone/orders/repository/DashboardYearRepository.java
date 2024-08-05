package sellphone.orders.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import sellphone.orders.model.DashboardYear;

import java.util.List;

public interface DashboardYearRepository extends JpaRepository<DashboardYear, String> {
    List<DashboardYear> findByYyyy(String year);

    @Query("SELECT DISTINCT d.yyyy FROM O1101_MONTHREPORT_V d ORDER BY d.yyyy")
    List<String> findAllYears();
}