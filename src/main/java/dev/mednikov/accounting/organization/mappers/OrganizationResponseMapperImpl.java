package dev.mednikov.accounting.organization.mappers;

import dev.mednikov.accounting.organization.domain.OrganizationResponse;
import dev.mednikov.accounting.organization.models.Organization;
import org.springframework.stereotype.Component;

@Component
public final class OrganizationResponseMapperImpl implements OrganizationResponseMapper {

    @Override
    public OrganizationResponse apply(Organization organization) {
        return new OrganizationResponse(organization.getId(), organization.getName());
    }


}
