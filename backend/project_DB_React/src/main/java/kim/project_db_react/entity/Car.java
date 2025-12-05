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
@Table(name = "car")
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "car_id")
    private Long carId;
    // 1. 어떤 모델인가? (Model과 연결)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "model_id")
    private Model model;

    // 2. 어디에 주차되어 있나? (PickupZone과 연결)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "zone_id")
    private PickupZone pickupZone;

    @Column(name = "car_number", length = 20)
    private String carNumber; // 번호판 (12가 3456)

    @Column(name = "year")
    private Integer year; // 연식 (2025)

    @Column(length = 20)
    private String color; // 색상

    private Integer mileage; // 주행거리

    // 상태 (AVAILABLE, RENTED, MAINTENANCE)
    // DB에는 ENUM으로 되어있지만, 자바에선 편의상 String으로 받습니다.
    @Column(length = 20)
    private String status;

    @Column(columnDefinition = "TEXT")
    private String description; // 차량 설명

}
