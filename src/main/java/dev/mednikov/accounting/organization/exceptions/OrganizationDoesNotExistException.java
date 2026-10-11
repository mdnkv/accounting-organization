package dev.mednikov.accounting.organization.exceptions;

import java.util.UUID;

public final class OrganizationDoesNotExistException extends RuntimeException {

    public OrganizationDoesNotExistException(UUID organizationId){
        super(String.format("Organization with ID %s does not exist", organizationId.toString()));
    }

}
