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

    public OrderDetail() {
	}
    
	public String getDetailId() {
		return detailId;
	}

	public void setDetailId(String detailId) {
		this.detailId = detailId;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public String getStorage() {
		return storage;
	}

	public void setStorage(String storage) {
		this.storage = storage;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public Integer getPrice() {
		return price;
	}

	public void setPrice(Integer price) {
		this.price = price;
	}

	public Integer getTotal() {
		return total;
	}

	public void setTotal(Integer total) {
		this.total = total;
	}

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}
    
    
}
