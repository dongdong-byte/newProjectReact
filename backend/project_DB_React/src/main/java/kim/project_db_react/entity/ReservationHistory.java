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
@Table(name = "reservation_history")
public class ReservationHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "history_id")
    private Long historyId;

    @Column(name = "reservation_id")
    private Long reservationId; // 원본 예약 ID (이미 삭제되었을 수도 있으니 객체 연결 X)
    @Column(name = "car_id")
    private Long carId;  // 차량 ID
    @Column(name = "user_id")
    private Long userId;  // 회원 ID

    @Column(name = "start_date")
    private LocalDateTime startDate;
    @Column(name = "end_date")
    private LocalDateTime endDate;

    @Column(length = 50)
    private String status;
    @Column(name = "final_price")
    private BigDecimal finalPrice;

    @Column(name = "origin_created_at")
    private LocalDateTime originCreatedAt; // 원래 예약이 만들어졌던 시간

    @CreationTimestamp
    @Column(name = "backup_at", updatable = false)
    private LocalDateTime backupAt; // 백업된 시간
}
