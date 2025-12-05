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
@Table(name = "model")
public class Model {
// 아반떼, 쏘렌토 같은 모델 정보입니다. **"어떤 브랜드의 차인가?"**를 알기 위해 Brand와 연결됩니다.
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "model_id")
    private Long modelId;
// ==========================================
    // [중요] 연관관계 매핑 (Foreign Key)
    // ==========================================
    // @ManyToOne: "여러 개의 모델(Many)이 하나의 브랜드(One)에 속한다"
    // FetchType.LAZY: "브랜드 정보는 당장 필요 없으면 나중에 천천히 가져와" (성능 최적화 필수 옵션)

// [중요] Brand와 연결 (ManyToOne: 여러 모델이 하나의 브랜드에 속함)
    // DB에는 'brand_id'라는 이름으로 컬럼이 생깁니다.
    @ManyToOne(fetch =  FetchType.LAZY)
    @JoinColumn(name = "brand_id") // DB에는 'brand_id'라는 이름의 컬럼으로 연결고리가 생깁니다.
    private Brand brand;
    // 주의: private Long brandId; 라고 쓰지 않고 객체(Brand)를 바로 넣습니다!

    // ==========================================
    // 일반 컬럼들
    // ==========================================

    @Column(name = "model_name", nullable = false, length = 100)
    private String modelName;  // 예: 아반떼, 쏘렌토
    @Column(name = "fuel_type", length = 50)
    private String fuelType; // 예: Gasoline, Diesel, Electric
    @Column(length = 50)
    private String segment; // 예: SUV, Sedan
    @Column(name = "passenger_limit")
    private Integer passengerLimit; // 승차 인원 (숫자니까 Integer)

    // [추가됨] 변속기 (오토, 수동)
    @Column(length = 20)
    private String transmission; // 예: Auto, Manual

}
