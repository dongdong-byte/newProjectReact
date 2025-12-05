package kim.project_db_react.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "brand")
public class Brand {
    //    현대, 기아 같은 브랜드 정보를 담는 테이블입니다.

    @Id // "여기가 PK(주민등록번호 같은 고유 키)야"
    @GeneratedValue(strategy = GenerationType.IDENTITY) // "번호는 DB가 알아서 1, 2, 3... 늘려줘" (Auto Increment)
    @Column(name = "brand_id") // 자바에선 brandId지만, DB에는 brand_id로 저장해줘
    private Long brandId;

    @Column(name = "brand_name", nullable = false, length = 100) // 필수 입력(Not Null), 100자 제한
    private String brandName; // 예: Hyundai, Kia, BMW

    @Column(name = "origin_country", length = 100)
    private String originCountry; // 예: Korea, Germany
}
