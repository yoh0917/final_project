package sellphone.product.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;




@NoArgsConstructor
@Setter
@Getter
@Entity
@Table(name = "P0001_ProductScore")
public class ProductScore {
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer scoreId;
	
	private String userId;

	private Integer productid;
	
	private Integer scorenum;   //評分
	@Lob
	private String review;
	
	@ManyToOne
	@JoinColumn(name = "productid",insertable=false, updatable=false)
	private Product product;
}
