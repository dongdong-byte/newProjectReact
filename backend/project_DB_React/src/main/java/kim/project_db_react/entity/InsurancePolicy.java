package kim.project_db_react.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Setter
@Table(name = "insurance_policies")
public class InsurancePolicy {
    @Id
    @Column(name = "policy_key", length = 50)
    private String policyKey; // PK가 문자열(basic, full 등)입니다.

    @Column(length = 100)
    private String name; // 일반 자차, 완전 자차 등

    @Column(name = "daily_fee")
    private Long dailyFee; // 1일당 보험료

    @Column(name = "rent_discount_rate")
    private Double rentDiscountRate; // 렌트료 할인율 (예: 0.1)

    @Column(columnDefinition = "TEXT")
    private String description; // 설명

    @Column(name = "is_active")
    private Boolean isActive; // 사용 가능 여부
}
