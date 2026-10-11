package dev.mednikov.accounting.organization.mappers;

import dev.mednikov.accounting.organization.domain.OrganizationResponse;
import dev.mednikov.accounting.organization.models.Organization;

import java.util.function.Function;

public interface OrganizationResponseMapper extends Function<Organization, OrganizationResponse> {
}

