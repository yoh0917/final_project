package sellphone.phoneplan.model;

import java.io.Serializable;
import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import sellphone.dashboard.user.model.UserPhonePlanList;

@Entity
@Table(name = "F00001_PhonePlans")
@Getter
@Setter
@NoArgsConstructor
public class PhonePlanBean implements Serializable {

    private static final long serialVersionUID = 1L;

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

    @JsonIgnore
    @ManyToMany(mappedBy = "phonePlans")
    private Set<UserPhonePlanList> userPhonePlanLists = new LinkedHashSet<>();

    @Override
    public String toString() {
        return "PhonePlanBean [planID=" + planID + ", planName=" + planName 
                + ", telCompany=" + telCompany + ", contractType=" + contractType 
                + ", contractDuration=" + contractDuration + ", generation=" + generation 
                + ", dataUsage=" + dataUsage + ", dataTransferRate=" + dataTransferRate 
                + ", intraNetCall=" + intraNetCall + ", interNetCall=" + interNetCall 
                + ", localCall=" + localCall + ", discount=" + discount 
                + ", gift=" + gift + "]";
    }

    @Override
    public int hashCode() {
        return Objects.hash(contractDuration, contractType, dataTransferRate, dataUsage, discount, generation, gift,
                interNetCall, intraNetCall, localCall, planID, planName, telCompany);
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
                && Objects.equals(planName, other.planName) && Objects.equals(telCompany, other.telCompany);
    }

    public PhonePlanBean(int planID, String planName, String telCompany, String contractType,
            String contractDuration, String generation, String dataUsage, String dataTransferRate, String intraNetCall,
            String interNetCall, String localCall, String discount, String gift) {
        this.planID = planID;
        this.planName = planName;
        this.telCompany = telCompany;
        this.contractType = contractType;
        this.contractDuration = contractDuration;
        this.generation = generation;
        this.dataUsage = dataUsage;
        this.dataTransferRate = dataTransferRate;
        this.intraNetCall = intraNetCall;
        this.interNetCall = interNetCall;
        this.localCall = localCall;
        this.discount = discount;
        this.gift = gift;
    }

	public void setUsers(UserPhonePlanList user) {
		// TODO Auto-generated method stub
		
	}
}
