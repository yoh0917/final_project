package sellphone.dashboard.user.model;

import java.sql.Date;
import java.time.LocalDateTime;
import java.util.LinkedHashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
import lombok.Setter;
import sellphone.phoneplan.model.PhonePlanBean;
import lombok.NoArgsConstructor;

@Setter
@Getter
@NoArgsConstructor
@Entity
@Table(name = "U00004_UserPhonePlanList")
public class UserPhonePlanList {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "userPhonePlanID")
    private Long userPhonePlanID;

    @Column(name = "userId")
    private String userId;

    @Column(name = "username")
    private String userName;

    @Column(name = "useraccount")
    private String userAccount;

    @Column(name = "password")
    private String password;

    @Column(name = "Email")
    private String email;

    @Column(name = "contactNum")
    private String contactNum;

    @Column(name = "birthday")
    private Date birthday;

    @Column(name = "status")
    private int status;

    @Column(name = "createTime")
    private LocalDateTime createTime;

    @Column(name = "prevlogTime")
    private LocalDateTime prevlogTime;

    @Column(name = "telCompany")
    private String telCompany;

    @Column(name = "generation")
    private String generation;

    @Column(name = "ContractType")
    private String contractType;

    @Column(name = "ContractDuration")
    private String contractDuration;

    @Column(name = "phoneNumber")
    private String phoneNumber;

    @Column(name = "agreementDate", columnDefinition = "Date")
    private String agreementDate;

    @JsonIgnore
    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(
        name = "UserPhonePlan",
        joinColumns = @JoinColumn(name = "userPhonePlanID"),
        inverseJoinColumns = @JoinColumn(name = "planID")
    )
    private Set<PhonePlanBean> phonePlans = new LinkedHashSet<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userId", insertable = false, updatable = false)
    private Users user;
	private  PhonePlanBean phonePlanBean;

}
