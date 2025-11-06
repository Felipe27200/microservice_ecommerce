package org.ecommerce.dto.category.product;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CreateProductDTO
{
    @NotBlank(message = "The name is required")
    private String name;
    @NotBlank(message = "The description is required")
    private String description;
    private Boolean active = true;
    @Min(value = 1, message = "The price must be greater than zero")
    private BigDecimal price;
    @Min(value = 1, message = "The price must be greater than zero")
    private Long stockQuantity;
    @NotNull(message = "The category is required")
    private Long categoryFk;
}
