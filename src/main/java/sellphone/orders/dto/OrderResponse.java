package sellphone.orders.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class OrderResponse {
    private Long orderId;
    private String status;
    private Double totalAmount;
}
