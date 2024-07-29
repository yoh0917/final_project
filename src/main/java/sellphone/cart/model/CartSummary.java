package sellphone.cart.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CartSummary {
    private int totalItems;
    private int totalPrice;
}