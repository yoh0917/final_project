package sellphone.product.model;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import jakarta.transaction.Transactional;

public interface PhotoRepository extends JpaRepository<Photo, Integer> {
	
	@Query("from Photo p where p.product.productid = ?1")
	List<Photo> findByProductProductid(Integer productid);
	
	@Transactional
	void deleteByProductid(Integer productid);

	Optional<Photo> findByProductid(Integer productid);
}
