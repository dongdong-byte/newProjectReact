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
@Table(name = "rental_prices")
public class RentalPrice {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID) // ID를 랜덤 문자열(UUID)로 자동 생성
    @Column(length = 50)
    private String id;
    // 어떤 차의 가격인가?
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "car_id")
    private Car car;
    // 금액은 정밀한 계산을 위해 BigDecimal을 쓰거나, 한국 돈이면 Long을 씁니다.
    // 여기선 table.sql의 DECIMAL에 맞춰 BigDecimal을 사용하겠습니다.
    @Column(name = "daily_price")
    private BigDecimal dailyPrice; // 하루 표준 요금
    @Column(name = "hourly_price")
    private BigDecimal hourlyPrice; // 시간당 요금
    @Column(name = "monthly_price")
    private BigDecimal monthlyPrice;  // 월 장기 렌트 요금
    @Column(name = "weekend_price")
    private BigDecimal weekendPrice; // 주말 할증 요금
    @Column(length = 10 , columnDefinition = "VARCHAR(10) DEFAULT 'KRW'")
    private String currency;

    @Column(name = "start_date")
    private LocalDateTime startDate; // 가격 적용 시작일
    @Column(name = "end_date")
    private LocalDateTime endDate; // 가격 적용 종료일
    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    private Long createdAt;

}
