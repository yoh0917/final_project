package sellphone.forum.model;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

//@Entity
//@Table(name = "F0002_COMMENT")
public class Commtent {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer commentId;
	
	@Column(nullable = false)
	private Integer postId;
	
	@Column(nullable = false, length = 255)
	private Integer userId;
	
	@Column(nullable = false, columnDefinition = "TEXT")
	private String commentContent;
	
	@Column(nullable = false, updatable = false)
	@org.hibernate.annotations.CreationTimestamp
	private LocalDateTime commentCreatedTime;
	
	
	@Column(nullable = false)
	@org.hibernate.annotations.UpdateTimestamp
	private LocalDateTime commentLastUpdatedTime;
	
	


	public Commtent() {
		
	}


	public Integer getCommentId() {
		return commentId;
	}


	public void setCommentId(Integer commentId) {
		this.commentId = commentId;
	}


	public Integer getPostId() {
		return postId;
	}


	public void setPostId(Integer postId) {
		this.postId = postId;
	}


	public Integer getUserId() {
		return userId;
	}


	public void setUserId(Integer userId) {
		this.userId = userId;
	}


	public String getCommentContent() {
		return commentContent;
	}


	public void setCommentContent(String commentContent) {
		this.commentContent = commentContent;
	}


	public LocalDateTime getCommentCreatedTime() {
		return commentCreatedTime;
	}


	public void setCommentCreatedTime(LocalDateTime commentCreatedTime) {
		this.commentCreatedTime = commentCreatedTime;
	}


	public LocalDateTime getCommentLastUpdatedTime() {
		return commentLastUpdatedTime;
	}


	public void setCommentLastUpdatedTime(LocalDateTime commentLastUpdatedTime) {
		this.commentLastUpdatedTime = commentLastUpdatedTime;
	}
	
	
	

}

