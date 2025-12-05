package kim.project_db_react.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration // 스프링 설정 파일이라는 뜻
public class WebConfig  implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry){
        registry.addMapping("/**") // 모든 주소(api/**)에 대해
                .allowedOrigins("http://localhost:5173") // 리액트(3000번 포트)의 접근을 허용한다
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS") // 허용할 기능 목록
                .allowCredentials(true);

    }

}
