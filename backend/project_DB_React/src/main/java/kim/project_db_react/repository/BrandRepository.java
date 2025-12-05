package kim.project_db_react.repository;

import kim.project_db_react.entity.Brand;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

//[Repository란?]
//- 엔티티(Entity)에 의해 생성된 DB 테이블에 접근하는 메서드들을 사용하기 위한 인터페이스입니다
//- 쉽게 말해, DB에 심부름(CRUD: 생성, 조회, 수정, 삭제)을 시키는 '관리자'입니다.
@Repository
public interface BrandRepository  extends JpaRepository<Brand,Long>
{
//     💡 JpaRepository<Brand, Long> 의 의미:
//1. Brand: "나는 'Brand' 엔티티(테이블)를 관리할 거야."
// 2. Long: "Brand 엔티티의 PK(기본키)는 'Long' 타입이야." (Brand.java의 brandId가 Long이라서)

//    [이 안에 아무런 코드를 안 적어도 되는 이유]
//    // JpaRepository를 상속(extends)받는 순간, 스프링 데이터 JPA가 자동으로 아래 기능들을 만들어줍니다.
//    // - save()     : 저장 (INSERT, UPDATE)
//    // - findById() : ID로 한 건 찾기 (SELECT ... WHERE id = ?)
//    // - findAll()  : 전체 목록 찾기 (SELECT * FROM ...)
//    // - delete()   : 삭제 (DELETE)
//
//    // 만약 "브랜드 이름으로 찾기" 같은 특별한 기능이 필요하면 여기에 함수 이름만 적으면 됩니다.
//    // 예: Optional<Brand> findByBrandName(String brandName);
}
