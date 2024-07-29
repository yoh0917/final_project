package sellphone.forum.model;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface CommentRepository extends JpaRepository<Comment, Integer> {
	List<Comment> findByPost(Post post);
	
	 @Modifying
	    @Query("update Comment c set c.likeCount = c.likeCount + 1 where c.commentId = ?1")
	    void incrementLikeCount(Integer commentId);

	    @Modifying
	    @Query("update Comment c set c.likeCount = c.likeCount - 1 where c.commentId = ?1")
	    void decrementLikeCount(Integer commentId);
	}	