package sellphone.model;



import org.springframework.data.domain.Pageable;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface PostRepository extends JpaRepository<Post, Integer> {

	@Query("from Post")
	Page<Post> findLatest(org.springframework.data.domain.Pageable pgb);
	
	List<Post> findByTitleContainingOrPostContentContaining(String title, String postContent);
	
	Page<Post> findByTitleContainingIgnoreCase(String title, org.springframework.data.domain.Pageable pageable);
	
	Page<Post> findByTagsName(String tagName, Pageable pageable);
}
