package sellphone.dashboard.user.model;

import java.time.LocalDateTime;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;


@Entity
@Table(name = "U0005_UserPasswordResetToken")
public class UserPasswordToken {
    
	@Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int Id;    
	
	private String token;
	
	@Column(nullable = false, columnDefinition = "Datetime2(0) default Getdate()")
	private LocalDateTime expiryDateTime;
    
	@Column(insertable=false, updatable=false)
	private String userId;
	
	@OneToOne(cascade=CascadeType.ALL)
    @JoinColumn(name="userId", referencedColumnName="userId")
    private Users user;


	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public LocalDateTime getExpiryDateTime() {
		return expiryDateTime;
	}

	public void setExpiryDateTime(LocalDateTime expiryDateTime) {
		this.expiryDateTime = expiryDateTime;
	}


	public Users getUser() {
		return user;
	}

	public void setUser(Users user) {
		this.user = user;
	}
	
	
    
}