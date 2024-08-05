package sellphone.orders.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sellphone.orders.model.DashboardDay;
import sellphone.orders.model.DashboardYear;
import sellphone.orders.repository.DashboardDayRepository;
import sellphone.orders.repository.DashboardYearRepository;

import java.util.List;

@Service
public class DashboardService {

    @Autowired
    private DashboardDayRepository dashboardDayRepository;

    @Autowired
    private DashboardYearRepository dashboardYearRepository;

    public List<String> getAllYears() {
        return dashboardYearRepository.findAllYears();
    }

    public List<DashboardYear> getYearlyData(String year) {
        return dashboardYearRepository.findByYyyy(year);
    }

    public List<DashboardDay> getMonthlyData(String year, String month) {
        return dashboardDayRepository.findByMonthAndYear(month, year);
    }
}