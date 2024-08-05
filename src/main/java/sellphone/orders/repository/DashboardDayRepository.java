package sellphone.orders.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import sellphone.orders.model.DashboardDay;

import java.util.List;

public interface DashboardDayRepository extends JpaRepository<DashboardDay, String> {
    List<DashboardDay> findByYyyyStartingWith(String year);

    @Query("SELECT d FROM O1102_DAILYREPORT_V d WHERE d.yyyy = :year")
    List<DashboardDay> findByYear(String year);
}