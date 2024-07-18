package sellphone.cart.model;

import lombok.Data;
import jakarta.persistence.*;

@Data
@Entity
@Table(name = "S1001_SHOPPINGCART_V")
public class CartView {

    @Id
    @Column(name = "PRODUCTID")
    private int productId;

    @Column(name = "USERID")
    private String userId;

    @Column(name = "COLOR")
    private String color;

    @Column(name = "STORAGE")
    private String storage;

    @Column(name = "QUANTITY")
    private int quantity;

    @Column(name = "PRICE")
    private double price;

    @Column(name = "PRODUCTNAME")
    private String productName;

    @Column(name = "photoFile")
    private String photoFile; // 確認這個欄位名與資料庫中的一致
}