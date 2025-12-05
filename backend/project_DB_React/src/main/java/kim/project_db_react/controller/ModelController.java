package kim.project_db_react.controller;

import kim.project_db_react.entity.Model;
import kim.project_db_react.repository.ModelRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@RequestMapping("/api/models")
@RequiredArgsConstructor
public class ModelController {
    private final ModelRepository repository;

    @GetMapping
    public List<Model> getAll() { return repository.findAll(); }
}