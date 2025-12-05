package kim.project_db_react.controller;

import kim.project_db_react.entity.PricingPolicy;
import kim.project_db_react.repository.PricingPolicyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@RequestMapping("/api/pricing-policies")
@RequiredArgsConstructor
public class PricingPolicyController {
    private final PricingPolicyRepository repository;

    @GetMapping
    public List<PricingPolicy> getAll() { return repository.findAll(); }
}
