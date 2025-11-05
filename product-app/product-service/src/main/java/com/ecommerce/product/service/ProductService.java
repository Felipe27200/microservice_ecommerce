package com.ecommerce.product.service;

import com.ecommerce.product.entity.Category;
import com.ecommerce.product.entity.Product;
import com.ecommerce.product.repository.ProductRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.ecommerce.dto.category.product.CreateProductDTO;
import org.ecommerce.dto.category.product.ProductDTO;
import org.ecommerce.exception.EntityNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

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

    public List<ProductDTO> findAllProducts()
    {
        log.debug("[findAllProducts] Finding all products");

        var products = this.productRepository.findAll();

        return products
                .stream()
                .map(product -> this.modelMapper.map(product, ProductDTO.class))
                .toList();
    }

    public ProductDTO findById(Long id)
    {
        log.debug("[findById] Finding product {}", id);

        var product = this.productRepository.findById(id)
                .orElseThrow(() -> {
                    log.debug("[findById] Product not found with id {}", id);

                    return new EntityNotFoundException("Product not found with id " + id);
                });

        return modelMapper.map(product, ProductDTO.class);
    }

    public ProductDTO updateProduct(Long id, CreateProductDTO productDTO)
    {
        log.debug("[updateProduct] Updating product {}", id);

         var oldProduct = this.productRepository.findById(id)
                 .orElseThrow(() -> {
                     log.debug("[updateProduct] Product not found with id {}", id);

                     return new EntityNotFoundException("Product not found with id " + id);
                 });

         var category = this.categoryService.findById(productDTO.getCategoryFk());
         var product = this.modelMapper.map(productDTO, Product.class);

         oldProduct.setCategory(this.modelMapper.map(category, Category.class));
         oldProduct.setName(product.getName());
         oldProduct.setPrice(product.getPrice());
         oldProduct.setDescription(product.getDescription());

         product = productRepository.save(oldProduct);

         log.debug("[updateProduct] Updated product {}", product);

         return this.modelMapper.map(product, ProductDTO.class);
    }
}
