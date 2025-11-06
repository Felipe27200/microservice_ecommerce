package org.ecommerce.customer.dto;

import jakarta.validation.constraints.NotBlank;

public record CreateCustomerDTO(
        @NotBlank(message = "The first name is required")
        String firstName,
        @NotBlank(message = "The last name is required")
        String lastName,
        @NotBlank(message = "The email is required")
        String email,
        @NotBlank(message = "The phone is required")
        String phone,
        @NotBlank(message = "The address line is required")
        String address_line
) {
}
