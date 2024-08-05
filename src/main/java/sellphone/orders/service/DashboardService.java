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
    private DashboardDayRepository dayRepository;

    @Autowired
    private DashboardYearRepository yearRepository;


//    public List<Dashboard> getAllReports() {
//        return dashboardRepository.findAll();
//    }
//
//    public List<Dashboard> getYearlyRevenue(String year) {
//        return dashboardRepository.findByYyyy(year);
//    }


    public List<DashboardDay> getDailyRevenueByYear(String year) {
        return dayRepository.findByYyyyStartingWith(year);
    }

    public List<DashboardYear> getMonthlyRevenueByYear(String year) {
        return yearRepository.findByYyyyStartingWith(year);
    }
}