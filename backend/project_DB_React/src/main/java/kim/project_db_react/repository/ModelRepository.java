package kim.project_db_react.repository;


import kim.project_db_react.entity.Model;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ModelRepository extends JpaRepository<Model,Long> {
    // Model 엔티티를 관리하고, PK는 Long 타입
}
