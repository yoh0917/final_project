package sellphone.dashboard.user.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import sellphone.phoneplan.model.PhonePlanBean;



@Entity
@Table(name = "U00004_UserPhonePlanList")
public class UserPhonePlanList {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private int UserPhonePlanListID;
    
    private String phoneNunber;
    
    @Column(columnDefinition = "Date ")
    private String agreementDate;
    
    @Column(nullable = false, insertable=false, updatable=false)
  	private String userId;
    
    @Column(nullable = false, insertable=false, updatable=false)
    private int planID;
    
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "userId")
	private  Users user;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "planID")
	private  PhonePlanBean phonePlanBean;

	public UserPhonePlanList() {
	}
	
	public int getUserPhonePlanListID() {
		return UserPhonePlanListID;
	}

	public void setUserPhonePlanListID(int userPhonePlanListID) {
		UserPhonePlanListID = userPhonePlanListID;
	}

	public String getPhoneNunber() {
		return phoneNunber;
	}

	public void setPhoneNunber(String phoneNunber) {
		this.phoneNunber = phoneNunber;
	}

	public String getAgreementDate() {
		return agreementDate;
	}

	public void setAgreementDate(String agreementDate) {
		this.agreementDate = agreementDate;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public int getPlanID() {
		return planID;
	}

	public void setPlanID(int planID) {
		this.planID = planID;
	}

	public Users getUser() {
		return user;
	}

	public void setUser(Users user) {
		this.user = user;
	}

	public PhonePlanBean getPhonePlanBean() {
		return phonePlanBean;
	}

	public void setPhonePlanBean(PhonePlanBean phonePlanBean) {
		this.phonePlanBean = phonePlanBean;
	}
	
	
}
