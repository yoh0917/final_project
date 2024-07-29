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
    private int price;

    @Column(name = "PRODUCTNAME")
    private String productName;

    @Transient
    private String photoBase64; // Base64圖片欄位

    public CartView() {
	}
    
	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
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

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getPhotoBase64() {
		return photoBase64;
	}

	public void setPhotoBase64(String photoBase64) {
		this.photoBase64 = photoBase64;
	}
    
    
}
