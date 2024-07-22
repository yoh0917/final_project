package sellphone.product.model;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;



public interface ProductScoreRepository extends JpaRepository<ProductScore, Integer> {

	ProductScore findByUserNameAndProductid(String userName, Integer productid);
			 
	 @Query("SELECT ps FROM ProductScore ps WHERE ps.productid = :productId Order By localDateTime DESC")
	    List<ProductScore> findByProductId(Integer productId);
	 
	 
	 @Query(value = "SELECT Top 4 * FROM P0001_ProductScore WHERE productid = :productId ORDER BY localDateTime DESC", nativeQuery = true)
	    List<ProductScore> findTop4ByProductId(@Param("productId") Integer productId);
	 
}
