package sellphone.orders.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import sellphone.orders.model.DashboardDay;

import java.util.List;

public interface DashboardDayRepository extends JpaRepository<DashboardDay, String> {
    @Query("SELECT d FROM O1102_DAILYREPORT_V d WHERE d.mm = :month AND d.yyyy = :year")
    List<DashboardDay> findByMonthAndYear(String month, String year);
}