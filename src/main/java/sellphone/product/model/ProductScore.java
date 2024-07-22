package sellphone.product.model;

import java.time.LocalDateTime;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
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
	
	private String userName;

	private Integer productid;
	
	private Integer scorenum;   //評分
	@Lob
	private String review;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") // 若要在 thymeleaf 強制使用本格式，需雙層大括號
	@Temporal(TemporalType.TIMESTAMP)
	private LocalDateTime localDateTime;
	
	@ManyToOne
	@JoinColumn(name = "productid",insertable=false, updatable=false)
	private Product product;
}
