package kim.project_db_react.controller;

import kim.project_db_react.entity.ReservationHistory;
import kim.project_db_react.repository.ReservationHistoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@RequestMapping("/api/reservation-histories")
@RequiredArgsConstructor
public class ReservationHistoryController {
    private final ReservationHistoryRepository repository;

    @GetMapping
    public List<ReservationHistory> getAll() { return repository.findAll(); }
}
