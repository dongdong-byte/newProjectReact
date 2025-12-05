package kim.project_db_react.entity;


import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "rental_price_applied_log")
public class RentalPriceAppliedLog {
//  여기서 appliedPolicies는 DB에 JSON 타입으로 저장되는데, JPA 초보자에게 JSON 매핑 라이브러리 추가는 복잡할 수 있습니다. 가장 쉬운 방법인 String으로 처리해서 DB에 텍스트로 넣는 방식으로 구현해 드릴게요. (문제없습니다!)

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    // 어떤 예약에 대한 로그인가?
    // 로그는 예약이 지워져도 남아야 할 수도 있지만, table.sql에는 CASCADE가 걸려있으니 연결합니다.
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "reservation_id")
    private Reservation reservation;

    @Column(name = "car_id")
    private Long carId;//단순 기록용이라 객체 대신 ID만 저장하기도 함

    @Column(name = "base_price")
    private BigDecimal basePrice;

    @Column(name = "final_price")
    private BigDecimal finalPrice;

    // [중요] DB의 JSON 타입은 자바에서 String으로 받아서 처리하는 게 제일 속 편합니다.
    @Column(name = "applied_policies" , columnDefinition = "JSON")
    private String appliedPolicies;

    @CreationTimestamp
    @Column(name = "created_at" , updatable = false)
    private LocalDateTime createdAt;
}
