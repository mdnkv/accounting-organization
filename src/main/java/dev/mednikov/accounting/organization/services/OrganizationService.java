package dev.mednikov.accounting.organization.services;

import dev.mednikov.accounting.organization.domain.CreateOrganizationRequest;
import dev.mednikov.accounting.organization.domain.OrganizationResponse;
import dev.mednikov.accounting.organization.domain.UpdateOrganizationRequest;

import java.util.Optional;
import java.util.UUID;

public interface OrganizationService {

    OrganizationResponse createOrganization (CreateOrganizationRequest request);

    OrganizationResponse updateOrganization (UpdateOrganizationRequest request);

    void deleteOrganization (UUID id);

    Optional<OrganizationResponse> getOrganization (UUID id);

}
