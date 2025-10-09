package org.ecommerce.dto.category;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateCategoryDTO {
    @NotBlank(message = "The category name is required")
    private String name;
    private String description;
}
