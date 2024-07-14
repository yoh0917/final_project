package sellphone.phoneplan.model;

import java.io.Serializable;
import java.util.LinkedHashSet;
import java.util.Set;

import jakarta.persistence.*;
import sellphone.dashboard.user.model.UserPhonePlanList;

@Entity
@Table(name = "F00001_PhonePlans")
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

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "phonePlanBean", cascade = CascadeType.ALL)
    private Set<UserPhonePlanList> userPhonePlanLists = new LinkedHashSet<>();

    // Constructors, getters, and setters...
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

    public Set<UserPhonePlanList> getUserPhonePlanLists() {
        return userPhonePlanLists;
    }

    public void setUserPhonePlanLists(Set<UserPhonePlanList> userPhonePlanLists) {
        this.userPhonePlanLists = userPhonePlanLists;
    }

    public void addUserPhonePlanList(UserPhonePlanList userPhonePlanList) {
        this.userPhonePlanLists.add(userPhonePlanList);
        userPhonePlanList.setPhonePlanBean(this);
    }

	public void setUsers(UserPhonePlanList user) {
		// TODO Auto-generated method stub
		
	}
}
