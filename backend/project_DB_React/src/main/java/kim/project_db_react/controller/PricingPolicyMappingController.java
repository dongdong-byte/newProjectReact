package kim.project_db_react.controller;

import kim.project_db_react.entity.PricingPolicyMapping;
import kim.project_db_react.repository.PricingPolicyMappingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@RequestMapping("/api/pricing-policy-mappings")
@RequiredArgsConstructor
public class PricingPolicyMappingController {
    private final PricingPolicyMappingRepository repository;

    @GetMapping
    public List<PricingPolicyMapping> getAll() { return repository.findAll(); }
}