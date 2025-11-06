package org.ecommerce.customer.dto;

public record CustomerDTO(
    Long id,
    String firstName,
    String lastName,
    String email,
    String phone,
    String address_line
) {
}
