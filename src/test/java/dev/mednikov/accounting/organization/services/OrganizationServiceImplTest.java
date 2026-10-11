package dev.mednikov.accounting.organization.services;

import dev.mednikov.accounting.organization.domain.CreateOrganizationRequest;
import dev.mednikov.accounting.organization.domain.OrganizationResponse;
import dev.mednikov.accounting.organization.domain.UpdateOrganizationRequest;
import dev.mednikov.accounting.organization.exceptions.OrganizationDoesNotExistException;
import dev.mednikov.accounting.organization.mappers.OrganizationResponseMapper;
import dev.mednikov.accounting.organization.mappers.OrganizationResponseMapperImpl;
import dev.mednikov.accounting.organization.models.Organization;
import dev.mednikov.accounting.organization.repositories.OrganizationRepository;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class OrganizationServiceImplTest {

    @Mock private OrganizationRepository organizationRepository;
    private OrganizationServiceImpl organizationService;

    @BeforeEach
    void setup(){
        OrganizationResponseMapper mapper = new OrganizationResponseMapperImpl();
        this.organizationService = new OrganizationServiceImpl(organizationRepository, mapper);
    }

    @Test
    void createOrganizationTest(){
        UUID organizationId = UUID.randomUUID();

        String organizationName = "The Lake Odellton Business Co.";

        CreateOrganizationRequest request = new CreateOrganizationRequest(organizationName);

        Organization organization = new Organization();
        organization.setId(organizationId);
        organization.setName(organizationName);

        when(organizationRepository.save(any(Organization.class))).thenReturn(organization);

        OrganizationResponse result = organizationService.createOrganization(request);
        assertThat(result).isNotNull()
                .hasFieldOrPropertyWithValue("id", organizationId)
                .hasFieldOrPropertyWithValue("name", organizationName);
    }

    @Test
    void updateOrganization_organizationDoesNotExistTest(){
        UUID organizationId = UUID.randomUUID();
        String organizationName = "Roob Industries";

        UpdateOrganizationRequest requestDto = new UpdateOrganizationRequest(organizationId, organizationName);

        when(organizationRepository.findById(organizationId)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> organizationService.updateOrganization(requestDto))
                .isInstanceOf(OrganizationDoesNotExistException.class);
    }

    @Test
    void updateOrganization_updatedTest(){
        UUID organizationId = UUID.randomUUID();
        String organizationName = "Schoen and Sons";

        UpdateOrganizationRequest requestDto = new UpdateOrganizationRequest(organizationId, organizationName);

        Organization organization = new Organization();
        organization.setId(organizationId);
        organization.setName(organizationName);

        when(organizationRepository.findById(organizationId)).thenReturn(Optional.of(organization));
        when(organizationRepository.save(any(Organization.class))).thenReturn(organization);

        OrganizationResponse result = organizationService.updateOrganization(requestDto);
        assertThat(result).isNotNull()
                .hasFieldOrPropertyWithValue("id", organizationId)
                .hasFieldOrPropertyWithValue("name", organizationName);
    }

    @Test
    void getOrganization_existsTest(){
        UUID organizationId = UUID.randomUUID();
        Organization organization = new Organization();
        organization.setId(organizationId);
        organization.setName("Garrisonport Business Company");

        when(organizationRepository.findById(organizationId)).thenReturn(Optional.of(organization));

        Optional<OrganizationResponse> result = organizationService.getOrganization(organizationId);
        assertThat(result).isPresent();
    }

    @Test
    void getOrganization_doesNotExistTest(){
        UUID organizationId = UUID.randomUUID();
        when(organizationRepository.findById(organizationId)).thenReturn(Optional.empty());
        Optional<OrganizationResponse> result = organizationService.getOrganization(organizationId);
        assertThat(result).isEmpty();
    }

}
