package sellphone.product.model;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@Entity
@Table(name = "p0001_products")
public class Product {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer productid;

	@Column(name = "PRODUCTNAME")
	private String productName;

	@Column(name = "PRICE")
	private Integer price;

	@Column(name = "DESCRIPTION", length = 200)
	private String description;

	@Column(name = "STOCKQUANTITY")
	private Integer stockQuantity;

	@Column(name = "PRODUCTBRAND")
	private String productbrand;

	@Column(name = "PRODUCTSTATUS")
	private Integer productstatus;
	
	private String color;
	
	private String capacity;
	
	private Double totalSocreNum;		//總分
	
	private Integer productviewnumber;  //觀看次數
	
	private Integer totalScore;		//總評論次數
	
	private Double avgScore;  //平均分數

	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "product")
	private List<Photo> productPhoto = new ArrayList<>();

	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "product" )
	private List<ProductScore> productscore = new ArrayList<>();
	
	
	public String getProductbrand() {
		return productbrand;
	}



	public void setProductbrand(String productbrand) {
		this.productbrand = productbrand;
	}



	public Integer getProductstatus() {
		return productstatus;
	}



	public void setProductstatus(Integer productstatus) {
		this.productstatus = productstatus;
	}



	public Integer getProductid() {
		return productid;
	}

	public void setProductid(Integer productid) {
		this.productid = productid;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public Integer getPrice() {
		return price;
	}

	public void setPrice(Integer price) {
		this.price = price;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getStockQuantity() {
		return stockQuantity;
	}

	public void setStockQuantity(Integer stockQuantity) {
		this.stockQuantity = stockQuantity;
	}

	public List<Photo> getProductPhoto() {
		return productPhoto;
	}

	public void setProductPhoto(List<Photo> productPhoto) {
		this.productPhoto = productPhoto;
	}

}
