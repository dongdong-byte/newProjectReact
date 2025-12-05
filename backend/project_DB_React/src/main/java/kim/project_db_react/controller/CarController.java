package kim.project_db_react.controller;

import kim.project_db_react.entity.Car;
import kim.project_db_react.repository.CarRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController // "이 클래스는 화면(HTML)이 아니라 데이터(JSON)를 돌려줍니다"라는 뜻
@RequestMapping("/api/cars") // 접속 주소: localhost:8080/api/cars
@RequiredArgsConstructor // Repository를 자동으로 연결(DI)해 줍니다.
public class CarController {
    private final CarRepository carRepository;

    // [GET] 차량 전체 목록 조회
    @GetMapping
    public List<Car> getAllCars() {
        // DB에서 모든 차를 가져와서 바로 반환!
        return carRepository.findAll();
    }

}
