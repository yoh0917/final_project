package sellphone.product.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Setter
@Getter
@Entity
@Table(name="P0001_PRODUCTMODEL")
public class ProductModel {
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer modelId;
	
	private String capacity;
	
	private String color;
	
	private Integer status;
	
	private Integer stockQuantity;
	
	private Integer productPrice;
	
	@ManyToOne
	@JoinColumn(name="productId")
	private ProductNew productNew;
	
	
	
	
}
