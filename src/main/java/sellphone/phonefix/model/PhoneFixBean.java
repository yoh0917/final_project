package sellphone.phonefix.model;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import sellphone.dashboard.user.model.Users;

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

//    @Column(nullable = false, insertable=false, updatable=false)
//  	private String userId;
    
	@ManyToOne
	@JoinColumn(name = "userId")
    private Users user;

    // Default constructor
    public PhoneFixBean() {
    }

    // Parameterized constructor
    public PhoneFixBean(String fixName, String fixDate, String fixCost, String fixPort) {
        this.fixName = fixName;
        this.fixDate = fixDate;
        this.fixCost = fixCost;
        this.fixPort = fixPort;
    }

    // Getters and Setters
    public int getFixID() {
        return fixID;
    }

    public PhoneFixBean(int fixID, String fixName, String fixDate, String fixCost, String fixPort) {
		super();
		this.fixID = fixID;
        this.fixName = fixName;
        this.fixDate = fixDate;
        this.fixCost = fixCost;
        this.fixPort = fixPort;
	}



    public void setFixID(int fixID) {
    	this.fixID = fixID;
    }
    
	public String getFixName() {
		return fixName;
	}

	public void setFixName(String fixName) {
		this.fixName = fixName;
	}

	public String getFixDate() {
		return fixDate;
	}

	public void setFixDate(String fixDate) {
		this.fixDate = fixDate;
	}

	public String getFixCost() {
		return fixCost;
	}

	public void setFixCost(String fixCost) {
		this.fixCost = fixCost;
	}

	public String getFixPort() {
		return fixPort;
	}

	public void setFixPort(String fixPort) {
		this.fixPort = fixPort;
	}

	@Override
	public String toString() {
		return "PhoneFixBean [fixID=" + fixID + ", fixName=" + fixName + ", fixDate=" + fixDate
				+ ", fixCost=" + fixCost + ", fixPort=" + fixPort +  "]";
	}
}