package kim.project_db_react.controller;

import kim.project_db_react.entity.Brand;
import kim.project_db_react.repository.BrandRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/brands")
@RequiredArgsConstructor
public class BrandController {
    private final BrandRepository repository;

    @GetMapping
    public List<Brand> getAll() { return repository.findAll(); }

}
