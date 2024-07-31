package sellphone.user.model;

import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import sellphone.forum.model.Post;
import sellphone.phonefix.model.PhoneFixBean;

@Entity
@Table(name = "U00001_Users")
public class Users {

	@Id
	private String userId;

	private String userName;

	@Column(name = "useraccount")
	private String userAccount;

	private String password;

	private String email;

	private String contactNum;

	private LocalDate birthday;

	private int status;

	private int reportNum; 

	@Column(nullable = false, columnDefinition = "Datetime2(0) default Getdate()")
	private LocalDateTime createTime;

	@Column(columnDefinition = "Datetime2(0) ")
	private LocalDateTime prevlogTime;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "status", insertable = false, updatable = false)
//		@JsonIgnore
	private UserStatus userStatus;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "user", cascade = CascadeType.ALL)
	private Set<UserPhonePlanList> userPhonePlanListID = new LinkedHashSet<UserPhonePlanList>();

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "user", cascade = CascadeType.ALL)
	private Set<PhoneFixBean> phoneFixBean = new LinkedHashSet<PhoneFixBean>();

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "userId", referencedColumnName = "userId")
	private UserPasswordToken userPasswordResetToken;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "user", cascade = CascadeType.ALL)
    private Set<Post> posts = new LinkedHashSet<>();
	
	@ManyToMany(mappedBy = "likedUsers")
    private Set<Post> likedPosts = new LinkedHashSet<>();
	
	@ManyToMany(mappedBy = "favoritedUsers")
	private Set<Post> favoritePosts = new HashSet<>();
	

	public Set<Post> getFavoritePosts() {
		return favoritePosts;
	}

	public void setFavoritePosts(Set<Post> favoritePosts) {
		this.favoritePosts = favoritePosts;
	}

	public Users() {
	}

	public Set<Post> getLikedPosts() {
		return likedPosts;
	}

	public void setLikedPosts(Set<Post> likedPosts) {
		this.likedPosts = likedPosts;
	}

	public Users(String userName, String userAccount, String password, String email, String contactNum,
			LocalDate birthday) {
		this.userName = userName;
		this.userAccount = userAccount;
		this.password = password;
		this.email = email;
		this.contactNum = contactNum;
		this.birthday = birthday;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String username) {
		this.userName = username;
	}

	public String getUserAccount() {
		return userAccount;
	}

	public void setUserAccount(String useraccount) {
		this.userAccount = useraccount;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getContactNum() {
		return contactNum;
	}

	public void setContactNum(String contactNum) {
		this.contactNum = contactNum;
	}

	public LocalDate getBirthday() {
		return birthday;
	}

	public void setBirthday(LocalDate birthday) {
		this.birthday = birthday;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public UserStatus getUserStatus() {
		return userStatus;
	}

	public void setUserStatus(UserStatus userStatus) {
		this.userStatus = userStatus;
	}

	public LocalDateTime getCreateTime() {
		return createTime;
	}

	public void setCreateTime(LocalDateTime createTime) {
		this.createTime = createTime;
	}

	public LocalDateTime getPrevlogTime() {
		return prevlogTime;
	}

	public void setPrevlogTime(LocalDateTime prevlogTime) {
		this.prevlogTime = prevlogTime;
	}

	public Set<UserPhonePlanList> getUserPhonePlanListID() {
		return userPhonePlanListID;
	}

	public void setUserPhonePlanListID(Set<UserPhonePlanList> userPhonePlanListID) {
		this.userPhonePlanListID = userPhonePlanListID;
	}

	public Set<PhoneFixBean> getPhoneFixBean() {
		return phoneFixBean;
	}

	public void setPhoneFixBean(Set<PhoneFixBean> phoneFixBean) {
		this.phoneFixBean = phoneFixBean;
	}

	public UserPasswordToken getUserPasswordResetToken() {
		return userPasswordResetToken;
	}

	public void setUserPasswordResetToken(UserPasswordToken userPasswordResetToken) {
		this.userPasswordResetToken = userPasswordResetToken;
	}
	
	public Set<Post> getPosts() {
        return posts;
    }

    public void setPosts(Set<Post> posts) {
        this.posts = posts;
    }

	public Integer getReportNum() {
		return reportNum;
	}

	public void setReportNum(int reportNum) {
		this.reportNum = reportNum;
	}
    
    

}
