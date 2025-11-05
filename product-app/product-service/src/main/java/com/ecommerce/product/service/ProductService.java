package com.ecommerce.product.service;

import com.ecommerce.product.entity.Category;
import com.ecommerce.product.entity.Product;
import com.ecommerce.product.repository.ProductRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.ecommerce.dto.category.product.CreateProductDTO;
import org.ecommerce.dto.category.product.ProductDTO;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@Slf4j
public class ProductService
{
    private final ProductRepository productRepository;
    private final CategoryService categoryService;
    private final ModelMapper modelMapper;

    public ProductDTO createProduct(CreateProductDTO productDTO)
    {
        log.debug("[createProduct] Creating product {}", productDTO);

        var product = this.modelMapper.map(productDTO, Product.class);

        var category = this.categoryService.findById(productDTO.getCategoryFk());
        product.setCategory(this.modelMapper.map(category, Category.class));

        product = productRepository.save(product);

        log.debug("[createProduct] Saved product {}", product);

        return modelMapper.map(product, ProductDTO.class);
    }
}
