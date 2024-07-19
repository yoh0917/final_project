package sellphone.phonefix.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import sellphone.dashboard.user.model.Users;
@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "F00001_PhoneFixs")
public class PhoneFixBean implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "FixID")
    private int fixID;

    @Column(name = "FixName")
    private String fixName;

    @Column(name = "FixDate")
    private String fixDate;

    @Column(name = "FixCost")
    private String fixCost;

    @Column(name = "FixPort")
    private String fixPort;
    
    @Column(name = "FixState")
    private String fixState;

//    @Column(nullable = false, insertable=false, updatable=false)
//  	private String userId;
    
	@ManyToOne
	@JoinColumn(name = "userId")
    private Users user;
	
	@JsonIgnore
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "fixbean")
	private List<PhoneFixPhotoBean> fixPhotoBean=new ArrayList<>();


    // Parameterized constructor
    public PhoneFixBean(String fixName, String fixDate, String fixCost, String fixPort,String fixState) {
        this.fixName = fixName;
        this.fixDate = fixDate;
        this.fixCost = fixCost;
        this.fixPort = fixPort;
        this.fixState=fixState;
    }

    // Getters and Setters
    public int getFixID() {
        return fixID;
    }

    public PhoneFixBean(int fixID, String fixName, String fixDate, String fixCost, String fixPort,String fixState) {
		super();
		this.fixID = fixID;
        this.fixName = fixName;
        this.fixDate = fixDate;
        this.fixCost = fixCost;
        this.fixPort = fixPort;
        this.fixState=fixState;
	}

    
//空建構子	
//    // Default constructor
//    public PhoneFixBean() {
//    }

//getseter
//    public void setFixID(int fixID) {
//    	this.fixID = fixID;
//    }
//    
//	public String getFixName() {
//		return fixName;
//	}
//
//	public void setFixName(String fixName) {
//		this.fixName = fixName;
//	}
//
//	public String getFixDate() {
//		return fixDate;
//	}
//
//	public void setFixDate(String fixDate) {
//		this.fixDate = fixDate;
//	}
//
//	public String getFixCost() {
//		return fixCost;
//	}
//
//	public void setFixCost(String fixCost) {
//		this.fixCost = fixCost;
//	}
//
//	public String getFixPort() {
//		return fixPort;
//	}
//
//	public void setFixPort(String fixPort) {
//		this.fixPort = fixPort;
//	}
//
//	public String getFixState() {
//		return fixState;
//	}
//
//	public void setFixState(String fixState) {
//		this.fixState = fixState;
//	}

		
	}
