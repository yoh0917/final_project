package sellphone.dashboard.user.model;

import java.time.LocalDateTime;

import org.hibernate.annotations.Immutable;
import org.hibernate.annotations.View;
import org.springframework.stereotype.Component;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity @Table(name = "U00001_Users_V")
//@View(query = "Create View ")
//@Immutable
public class UserView {
	
	@Id
  	private String userId;
    
	private String userName;

	private String userAccount;

    private String email;
	
	private String contactNum;
	
	private LocalDateTime createTime;

	private LocalDateTime prevlogTime;

	private String statusDescrib;

	public String getUserId() {
		return userId;
	}
	
	public UserView() {
	}
	
	
	public UserView(String userId, String userName, String userAccount, String email, String contactNum,
			LocalDateTime createTime, LocalDateTime prevlogTime, String statusDescrib) {
		this.userId = userId;
		this.userName = userName;
		this.userAccount = userAccount;
		this.email = email;
		this.contactNum = contactNum;
		this.createTime = createTime;
		this.prevlogTime = prevlogTime;
		this.statusDescrib = statusDescrib;
	}



	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserAccount() {
		return userAccount;
	}

	public void setUserAccount(String userAccount) {
		this.userAccount = userAccount;
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

	public String getStatusDescrib() {
		return statusDescrib;
	}

	public void setStatusDescrib(String statusDescrib) {
		this.statusDescrib = statusDescrib;
	}
	
			
			
}
