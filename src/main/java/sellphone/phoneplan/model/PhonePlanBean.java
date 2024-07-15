package sellphone.phoneplan.model;

import java.io.Serializable;
import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import sellphone.dashboard.user.model.UserPhonePlanList;

@Entity
//@Getter
//@Setter
//@NoArgsConstructor
@Table(name = "F00001_PhonePlans")
public class PhonePlanBean  {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PlanID")
    private int planID;

    @Column(name = "PlanName")
    private String planName;

    @Column(name = "TelCompany")
    private String telCompany;

    @Column(name = "ContractType")
    private String contractType;

    @Column(name = "ContractDuration")
    private String contractDuration;

    @Column(name = "Generation")
    private String generation;

    @Column(name = "DataUsage")
    private String dataUsage;

    @Column(name = "DataTransferRate")
    private String dataTransferRate;

    @Column(name = "IntraNetCall")
    private String intraNetCall;

    @Column(name = "InterNetCall")
    private String interNetCall;

    @Column(name = "LocalCall")
    private String localCall;

    @Column(name = "Discount")
    private String discount;

    @Column(name = "Gift")
    private String gift;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "phonePlan", cascade = CascadeType.ALL )
	private Set<UserPhonePlanList> userPhonePlanLists = new LinkedHashSet<UserPhonePlanList>();
    

    @Override
    public int hashCode() {
        return Objects.hash(contractDuration, contractType, dataTransferRate, dataUsage, discount, generation, gift,
                interNetCall, intraNetCall, localCall, planID, planName, telCompany, userPhonePlanLists);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        PhonePlanBean other = (PhonePlanBean) obj;
        return Objects.equals(contractDuration, other.contractDuration)
                && Objects.equals(contractType, other.contractType)
                && Objects.equals(dataTransferRate, other.dataTransferRate)
                && Objects.equals(dataUsage, other.dataUsage) && Objects.equals(discount, other.discount)
                && Objects.equals(generation, other.generation) && Objects.equals(gift, other.gift)
                && Objects.equals(interNetCall, other.interNetCall) && Objects.equals(intraNetCall, other.intraNetCall)
                && Objects.equals(localCall, other.localCall) && planID == other.planID
                && Objects.equals(planName, other.planName) && Objects.equals(telCompany, other.telCompany)
                && Objects.equals(userPhonePlanLists, other.userPhonePlanLists);
    }

    public Set<UserPhonePlanList> getUserPhonePlanLists() {
        return userPhonePlanLists;
    }

    public void setUserPhonePlanLists(Set<UserPhonePlanList> userPhonePlanLists) {
        this.userPhonePlanLists = userPhonePlanLists;
    }

    
    
//    public void addUserPhonePlanList(UserPhonePlanList userPhonePlanList) {
//        this.userPhonePlanLists.add(userPhonePlanList);
//        userPhonePlanList.setPhonePlanBean(this);
//    }
//
//    public void setUsers(UserPhonePlanList user) {
//        // TODO Auto-generated method stub
//    }

    @Override
    public String toString() {
        return "PhonePlanBean [planID=" + planID + ", planName=" + planName + ", telCompany=" + telCompany
                + ", contractType=" + contractType + ", contractDuration=" + contractDuration + ", generation="
                + generation + ", dataUsage=" + dataUsage + ", dataTransferRate=" + dataTransferRate + ", intraNetCall="
                + intraNetCall + ", interNetCall=" + interNetCall + ", localCall=" + localCall + ", discount="
                + discount + ", gift=" + gift + ", userPhonePlanLists=" + userPhonePlanLists + "]";
    }
    public PhonePlanBean() {
	}

	public int getPlanID() {
		return planID;
	}

	public void setPlanID(int planID) {
		this.planID = planID;
	}

	public String getPlanName() {
		return planName;
	}

	public void setPlanName(String planName) {
		this.planName = planName;
	}

	public String getTelCompany() {
		return telCompany;
	}

	public void setTelCompany(String telCompany) {
		this.telCompany = telCompany;
	}

	public String getContractType() {
		return contractType;
	}

	public void setContractType(String contractType) {
		this.contractType = contractType;
	}

	public String getContractDuration() {
		return contractDuration;
	}

	public void setContractDuration(String contractDuration) {
		this.contractDuration = contractDuration;
	}

	public String getGeneration() {
		return generation;
	}

	public void setGeneration(String generation) {
		this.generation = generation;
	}

	public String getDataUsage() {
		return dataUsage;
	}

	public void setDataUsage(String dataUsage) {
		this.dataUsage = dataUsage;
	}

	public String getDataTransferRate() {
		return dataTransferRate;
	}

	public void setDataTransferRate(String dataTransferRate) {
		this.dataTransferRate = dataTransferRate;
	}

	public String getIntraNetCall() {
		return intraNetCall;
	}

	public void setIntraNetCall(String intraNetCall) {
		this.intraNetCall = intraNetCall;
	}

	public String getInterNetCall() {
		return interNetCall;
	}

	public void setInterNetCall(String interNetCall) {
		this.interNetCall = interNetCall;
	}

	public String getLocalCall() {
		return localCall;
	}

	public void setLocalCall(String localCall) {
		this.localCall = localCall;
	}

	public String getDiscount() {
		return discount;
	}

	public void setDiscount(String discount) {
		this.discount = discount;
	}

	public String getGift() {
		return gift;
	}

	public void setGift(String gift) {
		this.gift = gift;
	}
    
    
}
