package sellphone.orders.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import sellphone.orders.service.DashboardService;

@Controller
public class DashboardController {

    @Autowired
    private DashboardService dashboardService;

    @GetMapping("/DashBoard/orders/dashboard")
    public String getDashboard(Model model) {
        model.addAttribute("revenue", 99118.5);
        model.addAttribute("transactions", 376);
        model.addAttribute("avgTransaction", 450.53);
        model.addAttribute("couponSales", 23364.55);
        model.addAttribute("overviewBalance", 87982.80);
        return "DashboardOrder1";
    }

    @GetMapping("/DashBoard/orders/getYearlyDailyRevenue/{year}")
    public String getYearlyDailyRevenue(@PathVariable String year, Model model) {
        model.addAttribute("data", dashboardService.getDailyRevenueByYear(year));
        return "jsonTemplate";
    }
}


//    @GetMapping("/dashboard")
//    public String getDashboard(Model model) {
//        List<Dashboard> reports = dashboardService.getAllReports();
//        model.addAttribute("reports", reports);
//        return "OrderBackend/DashboardOrder";
//    }
//
//    @GetMapping("/getYearlyRevenue/{year}")
//    @ResponseBody
//    public List<Dashboard> getYearlyRevenue(@PathVariable String year) {
//        return dashboardService.getYearlyRevenue(year);
//    }
//
//    @GetMapping("/DashBoard/orders/getYearlyDailyRevenue")
//    public List<DashboardDay> getYearlyDailyRevenue(@RequestParam("year") String year) {
//        return dashboardService.getDailyReportsByYear(year);
//    }
//
//    @GetMapping("/getDailyRevenueByYear")
//    public List<DashboardDay> getDailyRevenueByYear(@RequestParam("year") String year) {
//        return dashboardService.getDailyRevenueByYear(year);
//    }
