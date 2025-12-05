package kim.project_db_react.repository;


import kim.project_db_react.entity.PickupZone;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PickupZoneRepository extends JpaRepository<PickupZone,Long> {
}
