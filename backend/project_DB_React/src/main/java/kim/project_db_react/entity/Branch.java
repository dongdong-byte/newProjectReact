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
@Table(name = "branch")
public class Branch {
// 지점(나중에 aipick 테이블로 바뀔수 도 있음)

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "branch_id")
    private Long branchId;

    @Column(name = "name" , length = 50)
    private String name;
    @Column(name = "address", length = 150)
    private String address;
    @Column(name = "region_depth1", length = 20)
    private String regionDepth1; // 서울, 경기

    @Column(name = "branch_type", length = 20)
    private String branchType; // 공항, 터미널

    // 위도, 경도
    private Double latitude;
    private Double longitude;
    @Column(name = "phone", length = 20)
    private String phone;
    @Column(name = "open_hours", length = 100)
    private String openHours;
    // DB의 TINYINT(1)은 자바에서 Boolean으로 받으면 됩니다.
    @Column(name = "is_active")
    private Boolean isActive; // 운영 여부 (true/false)

}
