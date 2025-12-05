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
@Table(name = "pickup_zone")
public class PickupZone {
//    주차구역
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "zone_id")
    private Long zoneId;
    // Branch와 연결
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "branch_id")
    private Branch branch;

    @Column(name = "name", length = 50)
    private String name; // 구역 이름


    private Double latitude;
    private Double longitude;

    @Column(name = "guide_info", columnDefinition = "TEXT")
    private String guideInfo; // 찾아오는 길

    // [추가된 항목들]
    @Column(name = "landmark", length = 100)
    private String landmark; // 주변 랜드마크

    @Column(name = "is_active")
    private Boolean isActive; // 사용 가능 여부


}
