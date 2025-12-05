package kim.project_db_react.controller;

import kim.project_db_react.entity.PickupZone;
import kim.project_db_react.repository.PickupZoneRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@RequestMapping("/api/pickup-zones")
@RequiredArgsConstructor
public class PickupZoneController {
    private final PickupZoneRepository repository;

    @GetMapping
    public List<PickupZone> getAll() { return repository.findAll(); }
}