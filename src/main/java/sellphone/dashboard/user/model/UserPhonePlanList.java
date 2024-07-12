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
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import sellphone.phoneplan.model.PhonePlanBean;

@Entity
@Table(name = "U00004_UserPhonePlanList")
@Getter
@Setter
@NoArgsConstructor
public class UserPhonePlanList {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int UserPhonePlanListID;
    
    private String phoneNunber;
    
    @Column(columnDefinition = "Datetime2(0) ")
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
	
	
	
}
