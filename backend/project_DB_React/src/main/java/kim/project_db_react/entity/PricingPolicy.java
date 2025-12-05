package kim.project_db_react.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "pricing_policies")
public class PricingPolicy {
//    PricingPolicy.java (할인/할증 정책)
//성수기, 이벤트 등 정책을 정의하는 곳입니다.
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(length = 50)
    private String id;

    @Column(length = 100)
    private String name; // 정책명 (여름 성수기 등)

    @Column(length = 50)
    private String type; // 주말, 시즌, 이벤트

    @Column(name = "start_date")
    private LocalDate startDate;

    @Column(name = "end_date")
    private LocalDate endDate;

    // 1.2(20% 인상) 같은 소수점을 저장하기 위해 Double이나 BigDecimal 사용
    private Double multiplier;

    @Column(columnDefinition = "TEXT")
    private String description;

    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

}
