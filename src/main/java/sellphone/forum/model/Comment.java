package sellphone.forum.model;

import java.time.LocalDateTime;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinColumns;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import sellphone.dashboard.user.model.Users;

@Entity
@Table(name = "F0002_COMMENT")
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int commentId;

    @Column(name = "commentContent", columnDefinition = "nvarchar(max)", nullable = false)
    private String commentContent;

    @Column(name = "commentCreatedTime", nullable = false,updatable = false)
    @org.hibernate.annotations.CreationTimestamp
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime commentCreatedTime;

    @Column(name = "commentLastUpdatedTime", nullable = false)
    @org.hibernate.annotations.UpdateTimestamp
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime commentLastUpdatedTime;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "postId", nullable = false)
    private Post post;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumns({
        @JoinColumn(name = "userId", referencedColumnName = "userId"),
        @JoinColumn(name = "userName", referencedColumnName = "userName")
    })
    private Users users;
    
    @Column(nullable = false, columnDefinition = "int default 0")
    private int likeCount = 0;
//    
//    @Column(name = "userName", nullable = false)
//    private String userName;
    
    
	
	public int getLikeCount() {
		return likeCount;
	}

	public void setLikeCount(int likeCount) {
		this.likeCount = likeCount;
	}

	public Comment() {
		
	}
	
	public Comment(String commentContent, LocalDateTime commentCreatedTime, LocalDateTime commentLastUpdatedTime, Post post, Users users) {
        this.commentContent = commentContent;
        this.commentCreatedTime = commentCreatedTime;
        this.commentLastUpdatedTime = commentLastUpdatedTime;
        this.post = post;
        this.users = users;
    }



	public Integer getCommentId() {
		return commentId;
	}


	public void setCommentId(Integer commentId) {
		this.commentId = commentId;
	}


	

//	public Integer getUserId() {
//		return userId;
//	}
//
//
//	public void setUserId(Integer userId) {
//		this.userId = userId;
//	}
	
	public Users getUsers() {
		return users;
	}

	public void setUser(Users users) {
		this.users = users;
	}

	public void setCommentId(int commentId) {
		this.commentId = commentId;
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

	public void setUsers(Users users) {
		this.users = users;
	}
	
	
}

