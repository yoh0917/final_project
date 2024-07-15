package sellphone.orders.model;

import lombok.Data;
import jakarta.persistence.*;
import com.fasterxml.jackson.annotation.JsonBackReference;

@Data
@Entity
@Table(name = "O0002_ORDERDETAIL")
public class OrderDetail {


    @Column(name = "ORDERID", length = 15)
    private String orderId;

    @Id
    @Column(name = "DETAILID", length = 10)
    private String detailId;

    @Column(name = "PRODUCTID", length = 10)
    private String productId;

    @Column(name = "QUANTITY ")
    private Integer count;

    @Column(name = "PRICE")
    private Integer price;

    @Column(name = "TOTAL")
    private Integer total;

    @ManyToOne
    @JoinColumn(name = "ORDERID", insertable = false, updatable = false)
    @JsonBackReference
    private Order order;
}
