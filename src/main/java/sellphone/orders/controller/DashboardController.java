package sellphone.orders.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sellphone.orders.model.DashboardDay;
import sellphone.orders.model.DashboardYear;
import sellphone.orders.service.DashboardService;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/DashBoard/orders/dashboard")
public class DashboardController {

    @Autowired
    private DashboardService dashboardService;

    @GetMapping("/years")
    public List<String> getAllYears() {
        return dashboardService.getAllYears();
    }

    @GetMapping("/yearly")
    public List<DashboardYear> getYearlyData(@RequestParam String year) {
        return dashboardService.getYearlyData(year);
    }

    @GetMapping("/monthly")
    public List<DashboardDay> getMonthlyData(@RequestParam String year, @RequestParam String month) {
        return dashboardService.getMonthlyData(year, month);
    }
}
//@RestController
//@RequestMapping("/DashBoard/orders")
//public class DashboardController {
//
//    @Autowired
//    private DashboardService dashboardService;
//
//    @GetMapping("/dashboard")
//    public Map<String, Object> showDashboard(@RequestParam(required = false) String year,
//                                             @RequestParam(required = false) String month) {
//        LocalDate now = LocalDate.now();
//        String currentYear = year != null ? year : String.valueOf(now.getYear());
//        String currentMonth = month != null ? month : String.format("%02d", now.getMonthValue());
//
//        List<String> years = dashboardService.getAllYears();
//        List<String> months = List.of("01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12");
//
//        List<DashboardYear> yearlyData = dashboardService.getYearlyData(currentYear);
//        List<DashboardDay> monthlyData = dashboardService.getDailyRevenueByMonthAndYear(currentMonth, currentYear);
//
//        Map<String, Object> response = new HashMap<>();
//        response.put("years", years);
//        response.put("months", months);
//        response.put("selectedYear", currentYear);
//        response.put("selectedMonth", currentMonth);
//        response.put("yearlyData", yearlyData);
//        response.put("monthlyData", monthlyData);
//
//        return response;
//    }
//
//    @GetMapping("/yearly-data")
//    public ResponseEntity<?> getYearlyData(@RequestParam String year) {
//        try {
//            List<DashboardYear> data = dashboardService.getYearlyData(year);
//            return ResponseEntity.ok(data);
//        } catch (Exception e) {
//            Map<String, String> error = new HashMap<>();
//            error.put("error", e.getMessage());
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
//        }
//    }
//
//    @GetMapping("/monthly-data")
//    public List<DashboardDay> getMonthlyData(@RequestParam String year, @RequestParam String month) {
//        return dashboardService.getDailyRevenueByMonthAndYear(month, year);
//    }
//}