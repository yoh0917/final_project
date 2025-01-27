package sellphone.forum.model;


import java.time.LocalDateTime;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinColumns;
import jakarta.persistence.JoinTable;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import sellphone.user.model.Users;

@Entity
@Table(name = "F0001_POST")
public class Post {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer postId;
    
    //一個回覆屬於一個用戶
//    @JoinColumn(name = "userId", nullable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumns({
        @JoinColumn(name = "userId", referencedColumnName = "userId"),
        @JoinColumn(name = "userName", referencedColumnName = "userName")
    })
    private Users user; 
    
    @Column(nullable = true, length = 255)
    private String title;

    @Column(nullable = false, columnDefinition = "nvarchar(max)")
    private String postContent;
    
    @Column(nullable = false, updatable = false)
    @org.hibernate.annotations.CreationTimestamp
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date postCreatedTime;
    
    @Column(nullable = false)
    @org.hibernate.annotations.UpdateTimestamp
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date postLastUpdatedTime;
    
    @ManyToMany
    @JoinTable(name = "F0004_POST_TAG",
    joinColumns = @JoinColumn(name = "postId"),
    inverseJoinColumns = @JoinColumn(name = "tagId"))
    private List<Tag> tags = new ArrayList<>();
    

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Comment> comments;
    
    @Column(nullable = false, columnDefinition = "int default 0")
    private int commentCount = 0;
    
    @ManyToMany
    @JoinTable(
        name = "F0005_postFavorites",
        joinColumns = @JoinColumn(name = "postId"),
        inverseJoinColumns = @JoinColumn(name = "userId")
    )
    private Set<Users> favoritedUsers = new HashSet<>();
    

    public Set<Users> getFavoritedUsers() {
		return favoritedUsers;
	}
    
    public boolean isFavoritedByUser(Users user) {
        return favoritedUsers.contains(user);
    }

	public void setFavoritedUsers(Set<Users> favoritedUsers) {
		this.favoritedUsers = favoritedUsers;
	}

	public int getCommentCount() {
		return commentCount;
	}

	public void setCommentCount(int commentCount) {
		this.commentCount = commentCount;
	}

	@Lob
    @Column(nullable = true)
    private byte[] image;
    
    @Column(nullable = false, columnDefinition = "int default 0")
    private int likeCount = 0;
    
    //哪些用戶點讚了這篇文章
    @ManyToMany
    @JoinTable(
        name = "F0003_postLikes",
        joinColumns = @JoinColumn(name = "postId"),
        inverseJoinColumns = @JoinColumn(name = "userId")
    )
    private Set<Users> likedUsers = new HashSet<>();
 
    public Set<Users> getLikedUsers() {
		return likedUsers;
	}

	public void setLikedUsers(Set<Users> likedUsers) {
		this.likedUsers = likedUsers;
	}

	public int getLikeCount() {
		return likeCount;
	}

	public void setLikeCount(int likeCount) {
		this.likeCount = likeCount;
	}

	public Integer getPostId() {
        return postId;
    }

    public void setPostId(Integer postId) {
        this.postId = postId;
    }
    
    public String getTitle() {
        return title;
    }

    public Users getUser() {
		return user;
	}

	public void setUser(Users user) {
		this.user = user;
	}

	public void setTitle(String title) {
        this.title = title;
    }

    public String getPostContent() {
        return postContent;
    }

    public void setPostContent(String postContent) {
        this.postContent = postContent;
    }

    public Date getPostCreatedTime() {
        return postCreatedTime;
    }

    public void setPostCreatedTime(Date postCreatedTime) {
        this.postCreatedTime = postCreatedTime;
    }

    public Date getPostLastUpdatedTime() {
        return postLastUpdatedTime;
    }

    public void setPostLastUpdatedTime(Date postLastUpdatedTime) {
        this.postLastUpdatedTime = postLastUpdatedTime;
    }

    public List<Tag> getTags() {
        return tags;
    }

    public void setTags(List<Tag> tags) {
        this.tags = tags;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }


    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }
}
