package dev.mednikov.accounting.organization.services;

import dev.mednikov.accounting.organization.domain.CreateOrganizationRequest;
import dev.mednikov.accounting.organization.domain.OrganizationResponse;
import dev.mednikov.accounting.organization.domain.UpdateOrganizationRequest;
import dev.mednikov.accounting.organization.exceptions.OrganizationDoesNotExistException;
import dev.mednikov.accounting.organization.mappers.OrganizationResponseMapper;
import dev.mednikov.accounting.organization.models.Organization;
import dev.mednikov.accounting.organization.repositories.OrganizationRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class OrganizationServiceImpl implements OrganizationService {

    private final OrganizationRepository organizationRepository;
    private final OrganizationResponseMapper mapper;

    public OrganizationServiceImpl(OrganizationRepository organizationRepository, OrganizationResponseMapper mapper) {
        this.organizationRepository = organizationRepository;
        this.mapper = mapper;
    }

    @Override
    public OrganizationResponse createOrganization(CreateOrganizationRequest request) {
        Organization organization = new Organization();
        organization.setName(request.name());

        Organization result = this.organizationRepository.save(organization);
        // TODO emit OrganizationCreatedEvent

        return this.mapper.apply(result);

    }

    @Override
    public OrganizationResponse updateOrganization(UpdateOrganizationRequest request) {
        Organization organization = this.organizationRepository.findById(request.id())
                .orElseThrow(() -> new OrganizationDoesNotExistException(request.id()));

        organization.setName(request.name());
        Organization result = this.organizationRepository.save(organization);

        // TODO emit OrganizationUpdatedEvent

        return this.mapper.apply(result);
    }

    @Override
    public void deleteOrganization(UUID id) {
        this.organizationRepository.deleteById(id);
    }

    @Override
    public Optional<OrganizationResponse> getOrganization(UUID id) {
        return this.organizationRepository.findById(id).map(this.mapper);
    }
}
