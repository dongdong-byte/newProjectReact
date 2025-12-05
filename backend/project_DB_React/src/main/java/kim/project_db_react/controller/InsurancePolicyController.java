package kim.project_db_react.controller;

import kim.project_db_react.entity.InsurancePolicy;
import kim.project_db_react.repository.InsurancePolicyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@RequestMapping("/api/insurance-policies")
@RequiredArgsConstructor
public class InsurancePolicyController {
    private final InsurancePolicyRepository repository;

    @GetMapping
    public List<InsurancePolicy> getAll() { return repository.findAll(); }
}