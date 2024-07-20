package sellphone.product.model;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;



public interface ProductRepository extends JpaRepository<Product, Integer> {
	//找有上架的 price range
	@Query("FROM Product p WHERE p.price BETWEEN :minPrice AND :maxPrice AND p.productstatus = 1")
    List<Product> findByPriceRange(Integer minPrice,Integer maxPrice);
	//找有上架的
	@Query("FROM Product p WHERE p.productstatus = 1")
	List<Product> getAllStatus();
	//找特定品牌的
	@Query("FROM Product p WHERE p.productbrand = :productbrand AND p.productstatus = 1 ")
	List<Product> getAllbrand(String productbrand);
	//
	@Query("FROM Product p WHERE p.price BETWEEN :minPrice AND :maxPrice AND p.productstatus = 1 AND p.productbrand =:productbrand ")
	List<Product> getAllbrandbyprice(Integer minPrice,Integer maxPrice,String productbrand);

}
