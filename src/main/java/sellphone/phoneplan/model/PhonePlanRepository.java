package sellphone.phoneplan.model;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PhonePlanRepository extends JpaRepository<PhonePlanBean, Integer> {
    Page<PhonePlanBean> findByTelCompany(String telCompany, Pageable pageable);
    Page<PhonePlanBean> findByContractType(String contractType, Pageable pageable);
    Page<PhonePlanBean> findByTelCompanyAndContractType(String telCompany, String contractType, Pageable pageable);
    
    @Query("SELECT p FROM PhonePlanBean p WHERE str(p.planID) LIKE %:planID%")
    Page<PhonePlanBean> findByPlanIDContaining(@Param("planID") String planID, Pageable pageable);

    @Query("SELECT p FROM PhonePlanBean p WHERE (:type IS NULL OR p.contractType = :type) AND (:company IS NULL OR p.telCompany = :company) AND (:network IS NULL OR p.generation = :network)")
    List<PhonePlanBean> findByFilters(@Param("type") String type, @Param("company") String company, @Param("network") String network);
    @Query("SELECT p FROM PhonePlanBean p WHERE p.telCompany = :telCompany AND p.contractType = :contractType AND p.generation = :generation AND p.dataUsage = :dataUsage")
    List<PhonePlanBean> findByFilters(@Param("telCompany") String telCompany,
                                      @Param("contractType") String contractType,
                                      @Param("generation") String generation,
                                      @Param("dataUsage") String dataUsage);

}
