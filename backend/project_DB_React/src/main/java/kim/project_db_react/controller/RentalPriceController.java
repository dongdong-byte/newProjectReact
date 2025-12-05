package kim.project_db_react.controller;

import kim.project_db_react.entity.RentalPrice;
import kim.project_db_react.repository.RentalPriceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@RequestMapping("/api/rental-prices")
@RequiredArgsConstructor
public class RentalPriceController {
    private final RentalPriceRepository repository;

    @GetMapping
    public List<RentalPrice> getAll() { return repository.findAll(); }
}
