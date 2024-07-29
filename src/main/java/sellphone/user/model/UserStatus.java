package sellphone.user.model;

import java.util.LinkedHashSet;
import java.util.Set;

import org.springframework.stereotype.Component;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity @Table(name = "U00002_UserStatus")
@Component
public class UserStatus {
	
	@Id 
	private int statusId;
	
	private String statusDescrib;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "userStatus", cascade = CascadeType.ALL )
	private Set<Users> userBean = new LinkedHashSet<Users>();

	public int getStatusId() {
		return statusId;
	}

	public void setStatusId(int statusId) {
		this.statusId = statusId;
	}

	public String getStatusDescrib() {
		return statusDescrib;
	}

	public void setStatusDescrib(String statusDescrib) {
		this.statusDescrib = statusDescrib;
	}

	public Set<Users> getUsesBean() {
		return userBean;
	}

	public void setUsersBean(Set<Users> userBean) {
		this.userBean = userBean;
	}
	
	
}
