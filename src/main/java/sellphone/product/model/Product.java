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
	private String price;
	@Column(name = "DESCRIPTION")
	private String description;
	@Column(name = "STOCKQUANTITY")
	private String stockQuantity;


	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "product")
    private List<Photo> productPhoto = new ArrayList<>();

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



	public String getPrice() {
		return price;
	}



	public void setPrice(String price) {
		this.price = price;
	}



	public String getDescription() {
		return description;
	}



	public void setDescription(String description) {
		this.description = description;
	}



	public String getStockQuantity() {
		return stockQuantity;
	}



	public void setStockQuantity(String stockQuantity) {
		this.stockQuantity = stockQuantity;
	}



	public List<Photo> getProductPhoto() {
		return productPhoto;
	}



	public void setProductPhoto(List<Photo> productPhoto) {
		this.productPhoto = productPhoto;
	}
		
	
	

}
