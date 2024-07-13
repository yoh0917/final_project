package sellphone.product.model;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.RequestParam;



public interface ProductRepository extends JpaRepository<Product, Integer> {
	
	@Query("FROM Product p WHERE p.price BETWEEN :minPrice AND :maxPrice")
    List<Product> findByPriceRange(@RequestParam("minPrice") Integer minPrice, @RequestParam("maxPrice") Integer maxPrice);
}
