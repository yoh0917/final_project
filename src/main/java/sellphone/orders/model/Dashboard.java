package sellphone.orders.model;

import lombok.Data;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import java.math.BigDecimal;

@Data
@Entity
public class Dashboard {
    @Id
    private String yyyymm;
    private BigDecimal totalAmount;
    private BigDecimal lmMonthAmount;
    private BigDecimal lyYearAmount;
    private BigDecimal monthlyGrate;
    private BigDecimal yearlyGrate;
    private int orderCount;
    private int lmOrderCount;
    private int lyOrderCount;
    private BigDecimal monthlyCountGrate;
    private BigDecimal yearlyCountGrate;
}