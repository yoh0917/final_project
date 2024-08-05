package sellphone.orders.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity(name = "O1102_DAILYREPORT_V")
public class DashboardDay {

    @Id
    @Column(name = "YYYYMMDD")
    private String yyyyMMdd;

    @Column(name = "YYYY")
    private String yyyy;

    @Column(name = "MM")
    private String mm;

    @Column(name = "DD")
    private String dd;

    @Column(name = "MMDD")
    private String mmdd;

    @Column(name = "TOTALAMOUNT")
    private Integer totalAmount;

    @Column(name = "ORDERCOUNT")
    private Integer orderCount;

//    @Column(name = "LM_MONTH_AMOUNT")
//    private Integer lmMonthAmount;

//    @Column(name = "LY_YEAR_AMOUNT")
//    private Integer lyYearAmount;
//
//    @Column(name = "MONTHLY_GRATE")
//    private String monthlyGrate;
//
//    @Column(name = "YEARLY_GRATE")
//    private String yearlyGrate;
//
//    @Column(name = "LM_ORDERCOUNT")
//    private Integer lmOrderCount;
//
//    @Column(name = "LY_ORDERCOUNT")
//    private Integer lyOrderCount;
//
//    @Column(name = "MONTHLY_COUNT_GRATE")
//    private String monthlyCountGrate;
//
//    @Column(name = "YEARLY_COUNT_GRATE")
//    private String yearlyCountGrate;
}
