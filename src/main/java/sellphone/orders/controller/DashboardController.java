package sellphone.orders.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import sellphone.orders.model.DashboardDay;
import sellphone.orders.model.DashboardYear;
import sellphone.orders.service.DashboardService;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Controller
@RequestMapping("/DashBoard/orders")
public class DashboardController {

    @Autowired
    private DashboardService dashboardService;

    @GetMapping("/dashboard")
    public String showDashboard(@RequestParam(required = false) String year,
                                @RequestParam(required = false) String month,
                                Model model) {
        LocalDate now = LocalDate.now();
        String currentYear = year != null ? year : String.valueOf(now.getYear());
        String currentMonth = month != null ? month : String.format("%02d", now.getMonthValue());

        List<String> years = dashboardService.getAllYears();
        List<String> months = IntStream.rangeClosed(1, 12)
                .mapToObj(i -> String.format("%02d", i))
                .collect(Collectors.toList());

        List<DashboardYear> yearlyData = dashboardService.getYearlyData(currentYear);
        List<DashboardDay> monthlyData = dashboardService.getMonthlyData(currentYear, currentMonth);

        model.addAttribute("years", years);
        model.addAttribute("months", months);
        model.addAttribute("selectedYear", currentYear);
        model.addAttribute("selectedMonth", currentMonth);
        model.addAttribute("yearlyData", yearlyData);
        model.addAttribute("monthlyData", monthlyData);

        return "OrderBackend/DashboardOrder";
    }

    @GetMapping("/yearly-data")
    @ResponseBody
    public List<DashboardYear> getYearlyData(@RequestParam String year) {
        return dashboardService.getYearlyData(year);
    }

    @GetMapping("/monthly-data")
    @ResponseBody
    public List<DashboardDay> getMonthlyData(@RequestParam String year, @RequestParam String month) {
        return dashboardService.getMonthlyData(year, month);
    }
}