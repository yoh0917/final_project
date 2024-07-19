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
	@Column(name = "ORDERID")
	private String orderId;

	@Column(name = "STATUS")
	private String status;

	@Column(name = "CREATEDATE")
	@Temporal(TemporalType.TIMESTAMP)
	private Date createDate;

	@Column(name = "TOTALAMOUNT")
	private Integer totalAmount;

	@Column(name = "PAYDATE")
	@Temporal(TemporalType.TIMESTAMP)
	private Date payDate;

	@Column(name = "PAYSTATUS")
	private String payStatus;

	@Column(name = "USERID")
	private String userId;

	@Column(name = "USERNAME")
	private String userName;

	@Column(name = "NAME")
	private String name;

	@Column(name = "EMAIL")
	private String email;

	@Column(name = "PHONE")
	private String phone;

	@Column(name = "CITY")
	private String city;

	@Column(name = "DISTRICT")
	private String district;

	@Column(name = "ADDRESS")
	private String address;

	@Column(name = "DETAILADDRESS")
	private String detailAddress;
}