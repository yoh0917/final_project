package sellphone.cart.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.*;

@Entity
@Table(name = "S0001_SHOPPINGCART")
@Data
@NoArgsConstructor
@AllArgsConstructor
@IdClass(CartPK.class)
public class Cart {
    @Id
    @Column(name = "USERID")
    private String userId;

    @Id
    @Column(name = "PRODUCTID")
    private int productId;

    @Column(name = "COLOR")
    private String color;

    @Column(name = "STORAGE")
    private String storage;

    @Column(name = "QUANTITY")
    private int quantity;

    @Column(name = "PRICE")
    private int price;
}
