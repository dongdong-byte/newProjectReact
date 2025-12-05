package kim.project_db_react.repository;

import kim.project_db_react.entity.PricingPolicy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PricingPolicyRepository  extends JpaRepository<PricingPolicy,String>
{
}
