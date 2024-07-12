package sellphone.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "F0003_LIKE")
public class Like {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer likeId;
	
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "postId", nullable = false)
	private  Post postId;
	
	@Column(nullable = false)
	private Integer userId;

	public Like() {
		
	}

	public Integer getLikeId() {
		return likeId;
	}

	public void setLikeId(Integer likeId) {
		this.likeId = likeId;
	}

	public Post getPostId() {
		return postId;
	}

	public void setPostId(Post postId) {
		this.postId = postId;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	
	

}

