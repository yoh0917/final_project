package sellphone.product.model;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductModelRepository extends JpaRepository<ProductModel, Integer> {

	
	List<ProductModel> findByProductNewProductId(Integer productId);
	
}
