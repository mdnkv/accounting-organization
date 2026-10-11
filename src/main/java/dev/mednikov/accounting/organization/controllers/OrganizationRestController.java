package dev.mednikov.accounting.organization.controllers;

import dev.mednikov.accounting.organization.domain.CreateOrganizationRequest;
import dev.mednikov.accounting.organization.domain.OrganizationResponse;
import dev.mednikov.accounting.organization.domain.UpdateOrganizationRequest;
import dev.mednikov.accounting.organization.services.OrganizationService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/organizations")
public class OrganizationRestController {

    private final OrganizationService organizationService;

    public OrganizationRestController(OrganizationService organizationService) {
        this.organizationService = organizationService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public @ResponseBody OrganizationResponse createOrganization (@RequestBody @Valid CreateOrganizationRequest request) {
        return this.organizationService.createOrganization(request);
    }

    @PutMapping
    public @ResponseBody OrganizationResponse updateOrganization (@RequestBody @Valid UpdateOrganizationRequest request) {
        return this.organizationService.updateOrganization(request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteOrganization (@PathVariable UUID id){
        this.organizationService.deleteOrganization(id);
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrganizationResponse> getOrganization (@PathVariable UUID id){
        Optional<OrganizationResponse> result = this.organizationService.getOrganization(id);
        return ResponseEntity.of(result);
    }

}
