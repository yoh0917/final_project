package sellphone.orders.model;

import lombok.Data;
import jakarta.persistence.*;
import com.fasterxml.jackson.annotation.JsonBackReference;

@Data
@Entity
@Table(name = "O0002_ORDERDETAIL")
public class OrderDetail {
    @Id
    @Column(name = "DETAILID")
    private String detailId;

    @Column(name = "ORDERID")
    private String orderId;

    @Column(name = "PRODUCTID")
    private int productId;

    @Column(name = "PRODUCTNAME")
    private String productName;

    @Column(name = "COLOR")
    private String color;

    @Column(name = "STORAGE")
    private String storage;

    @Column(name = "QUANTITY")
    private Integer quantity;

    @Column(name = "PRICE")
    private Integer price;

    @Column(name = "TOTAL")
    private Integer total;

    @ManyToOne
    @JoinColumn(name = "ORDERID", insertable = false, updatable = false)
    @JsonBackReference
    private Order order;
}
