package com.ecommerce.product.controller;

import com.ecommerce.product.service.ProductService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.ecommerce.dto.category.product.CreateProductDTO;
import org.ecommerce.dto.category.product.ProductDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@AllArgsConstructor
@RestController
@RequestMapping(value = "${apiPrefix}/products")
public class ProductController
{
    private final ProductService productService;

    @PostMapping
    public ResponseEntity<ProductDTO> createProduct(@Valid @RequestBody CreateProductDTO productDTO)
    {
        var newProduct = this.productService.createProduct(productDTO);

        return ResponseEntity.ok(newProduct);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductDTO> updateProduct(@PathVariable Long id, @RequestBody @Valid CreateProductDTO productDTO)
    {
        var updatedProduct = this.productService.updateProduct(id, productDTO);

        return ResponseEntity.ok(updatedProduct);
    }

    @GetMapping("/")
    public ResponseEntity<List<ProductDTO>> getAllProducts()
    {
        return ResponseEntity.ok(this.productService.findAllProducts());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductDTO> findProductById(@PathVariable Long id)
    {
        return ResponseEntity.ok(this.productService.findById(id));
    }
}
