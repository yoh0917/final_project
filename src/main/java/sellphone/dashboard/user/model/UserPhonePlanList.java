package sellphone.dashboard.user.model;

import java.sql.Date;
import java.time.LocalDateTime;
import java.util.LinkedHashSet;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import sellphone.phoneplan.model.PhonePlanBean;

@Getter
@Setter
@Entity
@Table(name = "U00004_UserPhonePlanList")
public class UserPhonePlanList {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "userPhonePlanID")
    private int userPhonePlanID;

    @Column(nullable = false, insertable=false, updatable=false)
  	private String userId;
    
    @Column(nullable = false, insertable=false, updatable=false)
    private int planID;
    
    private String phoneNum;
    
    @Column(name = "agreementDate", columnDefinition = "Date")
    private String agreementDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "planID")
    private PhonePlanBean phonePlan;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userId")
    private Users user;

    public UserPhonePlanList() {
	}
    
    
	public void setPhonePlanBean(PhonePlanBean phonePlanBean) {
		
	}

	public Users getUser() {
		return user;
	}

	public void setUser(Users user) {
		this.user = user;
	}


	public String getPhoneNum() {
		return phoneNum;
	}


	public void setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
	}


	
	
	
}
