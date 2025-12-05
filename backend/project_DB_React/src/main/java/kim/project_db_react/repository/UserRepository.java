package kim.project_db_react.repository;

import kim.project_db_react.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    // findAll()은 이미 상속받아서 자동 포함됨.
    // 내용은 비워둡니다.
}
