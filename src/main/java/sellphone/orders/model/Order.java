package sellphone.orders.model;

import lombok.Data;
import jakarta.persistence.*;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.List;
import java.util.Date;


@Data
@Entity
@Table(name = "O0001_ORDER")
public class Order {

    @Id
    @Column(name = "ORDERID", length = 15)
    private String orderId;

    @Column(name = "USERID", length = 10)
    private String userId;

    @Column(name = "USERNAME", length = 50)
    private String userName;

    @Column(name = "STATUS", length = 1)
    private String status;

    @Column(name = "TOTALAMOUNT")
    private Integer totalAmount;

//    @Column(name = "CREATEDATE")
//    @Temporal(TemporalType.TIMESTAMP)
//    @JsonFormat(pattern = "yyyyMMdd HH:mm:ss")
//    private Date createDate;
    @Column(name = "CREATEDATE")
    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createDate;

    @Column(name = "PAYSTATUS", length = 1)
    private String payStatus;

//    @Column(name = "PAYDATE")
//    @Temporal(TemporalType.TIMESTAMP)
//    @JsonFormat(pattern = "yyyyMMdd HH:mm:ss")
//    private Date payDate;
    @Column(name = "PAYDATE")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Temporal(TemporalType.TIMESTAMP)
    private Date payDate;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonManagedReference
    private List<OrderDetail> orderDetails;

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Integer getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(Integer totalAmount) {
		this.totalAmount = totalAmount;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public String getPayStatus() {
		return payStatus;
	}

	public void setPayStatus(String payStatus) {
		this.payStatus = payStatus;
	}

	public Date getPayDate() {
		return payDate;
	}

	public void setPayDate(Date payDate) {
		this.payDate = payDate;
	}

	public List<OrderDetail> getOrderDetails() {
		return orderDetails;
	}

	public void setOrderDetails(List<OrderDetail> orderDetails) {
		this.orderDetails = orderDetails;
	}
    
    
    
}
