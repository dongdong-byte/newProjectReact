package kim.project_db_react.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long userId;
    @Column(name = "username", nullable = false , length = 50)
    private String username;
    @Column(name = "email", nullable = false, length = 100)
    private String email;

    @CreationTimestamp
    @Column(name = "created_at" ,updatable = false)
    private LocalDateTime createdAt;
//    toString은 꼭 필요하면 만들되, 연관관계 필드(나중에 생길 리스트 등)는 뺍니다.
    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
