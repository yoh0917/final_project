package sellphone.product.model;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
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
@Table(name = "P0001_PRODUCTNEW")
public class ProductNew {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer productId;

	private String productName;

	private Integer price;			//用於查全部頁面的基本價格

	private String description;

	private String productbrand;

	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL,mappedBy ="productNew")
	private List<ProductModel> productModel = new ArrayList<>();
}
