package kim.project_db_react.repository;


import kim.project_db_react.entity.PricingPolicyMapping;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PricingPolicyMappingRepository  extends JpaRepository<PricingPolicyMapping,String>
{
}
