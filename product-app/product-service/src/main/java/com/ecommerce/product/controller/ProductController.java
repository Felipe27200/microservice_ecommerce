package com.ecommerce.product.controller;

import com.ecommerce.product.service.CategoryService;
import com.ecommerce.product.service.ProductService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.ecommerce.dto.category.product.CreateProductDTO;
import org.ecommerce.dto.category.product.ProductDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@AllArgsConstructor
@RestController
@RequestMapping(value = "${apiPrefix}/products")
public class ProductController
{
    private final ProductService productService;

    @PostMapping
    public ResponseEntity<ProductDTO> createProduct(@RequestBody CreateProductDTO productDTO)
    {
        var newProduct = this.productService.createProduct(productDTO);

        return ResponseEntity.ok(newProduct);
    }
}
