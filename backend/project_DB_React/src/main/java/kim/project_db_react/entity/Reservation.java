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
@Table(name = "reservation")
public class Reservation {
    @Id // 여기가 기본키(Primary Key)입니다.
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Auto Increment: 번호가 1, 2, 3... 자동으로 늘어납니다.
    @Column(name = "reservation_id")
    private Long reservationId;

    // ==========================================
    // [연관관계 매핑 구역] - 가장 중요한 부분!
    // ==========================================

    // 1. 누가 예약했나? (User 테이블과 연결)
    // @ManyToOne: 예약(Many)은 한 명의 회원(One)이 합니다.
    // FetchType.LAZY: 성능 최적화 (예약 정보만 가져오고, 회원 정보는 진짜 필요할 때 쿼리를 날림)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id") // DB에는 객체가 아니라 'user_id'라는 숫자값(FK)으로 저장됩니다.
    private User user;

    // 2. 어떤 차를 예약했나? (Car 테이블과 연결)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "car_id") // DB 컬럼명: car_id
    private Car car;

    // 3. 어디서 빌리나? (PickupZone 테이블과 연결)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pickup_zone_id") // DB 컬럼명: pickup_zone_id
    private PickupZone pickupZone;

    // 4. 어디로 반납하나? (PickupZone 테이블 재사용)
    // 같은 PickupZone 테이블을 가리키지만, 용도가 다르므로 변수명과 컬럼명을 다르게 줍니다.
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "return_zone_id") // DB 컬럼명: return_zone_id
    private PickupZone returnZone;

    // 5. [수정됨] 어떤 보험을 들었나? (InsurancePolicy 테이블과 연결)
    // 문자열(String)이 아니라 객체로 연결했으므로, DB에는 정책의 PK(policy_key)가 저장됩니다.
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "insurance_policy_key") // DB 컬럼명: insurance_policy_key
    private InsurancePolicy insurancePolicy;

    // ==========================================
    // [일반 데이터 구역]
    // ==========================================

    @Column(name = "start_date")
    private LocalDateTime startDate; // 대여 시작 시간 (날짜+시간)

    @Column(name = "end_date")
    private LocalDateTime endDate;   // 반납 예정 시간

    // 운전자 정보 (DB의 VARCHAR는 자바의 String)
    @Column(name = "driver_name", length = 50)
    private String driverName;

    @Column(name = "driver_phone", length = 20)
    private String driverPhone;

    @Column(name = "driver_birth")
    private LocalDate driverBirth; // 생년월일은 시간 없이 날짜만(LocalDate)

    @Column(name = "license_kind", length = 50)
    private String licenseKind; // 면허 종류 (1종 보통 등)

    @Column(name = "license_number", length = 50)
    private String licenseNumber; // 면허 번호

    // 옵션 선택 여부 (DB의 TINYINT/Boolean -> 자바 Boolean)
    @Column(name = "has_navi")
    private Boolean hasNavi;      // 네비게이션 포함 여부

    @Column(name = "has_baby_seat")
    private Boolean hasBabySeat;  // 카시트 포함 여부

    @Column(name = "has_dashcam")
    private Boolean hasDashcam;   // 블랙박스 포함 여부

    // 금액 정보 (계산이 중요하므로 Long 사용)
    @Column(name = "estimated_price")
    private Long estimatedPrice; // 예상 결제 금액

    @Column(name = "final_price")
    private Long finalPrice;     // 실제 최종 결제 금액 (연체료 등 포함될 수 있음)

    @Column(length = 20)
    private String status; // 예약 상태 (PENDING:대기, CONFIRMED:확정, CANCELED:취소 등)

    @Column(name = "payment_status", length = 50)
    private String paymentStatus; // 결제 상태 (unpaid:미결제, paid:결제완료)

    @Column(name = "payment_method", length = 50)
    private String paymentMethod; // 결제 수단 (Card, NaverPay 등)

    // @CreationTimestamp: INSERT 쿼리가 발생할 때, 현재 시간을 자동으로 찍어줍니다.
    @CreationTimestamp
    @Column(name = "created_at", updatable = false) // 수정 불가(최초 생성 시간 보존)
    private LocalDateTime createdAt;

}
