package sellphone.forum.model;



import org.springframework.data.domain.Pageable;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface PostRepository extends JpaRepository<Post, Integer> {
	
	List<Post> findByUserUserId(String userId);
	
	List<Post> findByTagsName(String tagName);
	
    
    @Query("SELECT p FROM Post p JOIN p.favoritedUsers u WHERE u.userId = :userId")
    List<Post> findBookmarkedByUserId(@Param("userId") String userId);

	@Query("from Post")
	Page<Post> findLatest(org.springframework.data.domain.Pageable pgb);
	
	List<Post> findByTitleContainingOrPostContentContaining(String title, String postContent);
	
	Page<Post> findByTitleContainingIgnoreCase(String title, org.springframework.data.domain.Pageable pageable);
	
	Page<Post> findByTagsName(String tagName, Pageable pageable);
	
	 @Transactional
	    @Modifying
	    @Query("UPDATE Post p SET p.likeCount = p.likeCount + 1 WHERE p.postId = :postId")
	    void incrementLikeCount(Integer postId);

	    @Transactional
	    @Modifying
	    @Query("UPDATE Post p SET p.likeCount = p.likeCount - 1 WHERE p.postId = :postId")
	    void decrementLikeCount(Integer postId);
	}


