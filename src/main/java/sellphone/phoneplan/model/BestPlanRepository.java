package sellphone.phoneplan.model;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BestPlanRepository extends JpaRepository<PhonePlanBean, Integer> {

    @Query("SELECT DISTINCT p.contractType FROM PhonePlanBean p WHERE p.planName = :planName")
    List<String> findDistinctContractTypesByPlanName(@Param("planName") String planName);

    @Query("SELECT DISTINCT p.planName FROM PhonePlanBean p WHERE p.telCompany = :telCompany")
    List<String> findDistinctPlanNamesByTelCompany(@Param("telCompany") String telCompany);

    @Query("SELECT DISTINCT p.generation FROM PhonePlanBean p WHERE p.contractType = :contractType")
    List<String> findDistinctGenerationsByContractType(@Param("contractType") String contractType);

    @Query("SELECT DISTINCT p.dataUsage FROM PhonePlanBean p WHERE p.generation = :generation")
    List<String> findDistinctDataUsagesByGeneration(@Param("generation") String generation);

    @Query("SELECT p FROM PhonePlanBean p WHERE p.telCompany = :telCompany AND p.planName = :planName AND p.contractType = :contractType AND p.generation = :generation AND p.dataUsage = :dataUsage")
    List<PhonePlanBean> findBestPlans(@Param("telCompany") String telCompany, @Param("planName") String planName, @Param("contractType") String contractType, @Param("generation") String generation, @Param("dataUsage") String dataUsage);
}
