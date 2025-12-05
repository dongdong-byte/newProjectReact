package kim.project_db_react.controller;

import kim.project_db_react.entity.Reservation;
import kim.project_db_react.repository.ReservationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@RequestMapping("/api/reservations")
@RequiredArgsConstructor
public class ReservationController {
    private final ReservationRepository repository;

    @GetMapping
    public List<Reservation> getAll() { return repository.findAll(); }
}