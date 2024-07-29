package sellphone.phoneplan.model;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PhonePlanRepository extends JpaRepository<PhonePlanBean, Integer> {

    @Query("SELECT p FROM PhonePlanBean p WHERE p.telCompany = :telCompany AND p.contractType = :contractType AND p.generation = :generation AND p.dataUsage = :dataUsage")
    List<PhonePlanBean> findByFilters(@Param("telCompany") String telCompany,
                                      @Param("contractType") String contractType,
                                      @Param("generation") String generation,
                                      @Param("dataUsage") String dataUsage);
    
    List<PhonePlanBean> findByPlanName(String planName);
    List<PhonePlanBean> findByTelCompanyAndPlanNameAndContractTypeAndGenerationAndDataUsage(
            String telCompany, String planName,  String contractType, String generation, String dataUsage);
    
}
