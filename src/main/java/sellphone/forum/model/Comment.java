package sellphone.forum.model;

import java.time.LocalDateTime;

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
@Table(name = "F0002_COMMENT")
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int commentId;

    @Column(name = "commentContent", columnDefinition = "nvarchar(max)", nullable = false)
    private String commentContent;

    @Column(name = "commentCreatedTime", nullable = false)
    private LocalDateTime commentCreatedTime;

    @Column(name = "commentLastUpdatedTime", nullable = false)
    private LocalDateTime commentLastUpdatedTime;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "postId", nullable = false)
    private Post post;

    @Column(name = "userId", nullable = false)
    private int userId;
	
	public Comment() {
		
	}


	public Integer getCommentId() {
		return commentId;
	}


	public void setCommentId(Integer commentId) {
		this.commentId = commentId;
	}


	

	public Integer getUserId() {
		return userId;
	}


	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	
	public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
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

