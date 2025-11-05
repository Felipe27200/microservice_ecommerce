package org.ecommerce.dto.category.product;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.ecommerce.dto.category.CategoryDTO;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ProductDTO
{
    private Long id;
    private String name;
    private String description;
    private Boolean active = true;
    private BigDecimal price;
    private Long stockQuantity;
    private CategoryDTO category;
}
