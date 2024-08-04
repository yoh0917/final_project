package sellphone.orders.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import sellphone.orders.model.DashboardYear;

import java.util.List;

public interface DashboardYearRepository extends JpaRepository<DashboardYear, String> {
    List<DashboardYear> findByYyyyStartingWith(String year);

    @Query("SELECT d FROM O1102_DAILYREPORT_V d WHERE d.yyyy = :year")
    List<DashboardYear> findByYear(String year);
}