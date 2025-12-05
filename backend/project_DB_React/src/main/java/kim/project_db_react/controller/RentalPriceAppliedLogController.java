package kim.project_db_react.controller;

import kim.project_db_react.entity.RentalPriceAppliedLog;
import kim.project_db_react.repository.RentalPriceAppliedLogRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@RequestMapping("/api/price-logs")
@RequiredArgsConstructor
public class RentalPriceAppliedLogController {
    private final RentalPriceAppliedLogRepository repository;

    @GetMapping
    public List<RentalPriceAppliedLog> getAll() { return repository.findAll(); }
}