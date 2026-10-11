package dev.mednikov.accounting.organization.domain;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;

public record CreateOrganizationRequest(
        @NotNull(message = "Name cannot be null")
        @NotEmpty(message = "Name cannot be empty")
        @Length(max = 255, message = "The maximum length is 255 characters")
        String name)
{
}
