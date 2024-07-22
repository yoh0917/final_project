package sellphone.product.model;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;



public interface ProductScoreRepository extends JpaRepository<ProductScore, Integer> {

	ProductScore findByUserNameAndProductid(String userName, Integer productid);
			 
	 @Query("SELECT ps FROM ProductScore ps WHERE ps.productid = :productId")
	    List<ProductScore> findByProductId(Integer productId);
}
