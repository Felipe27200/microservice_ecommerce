package com.ecommerce.product.controller;

import com.ecommerce.product.entity.Category;
import com.ecommerce.product.service.CategoryService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.ecommerce.dto.category.CategoryDTO;
import org.ecommerce.dto.category.CreateCategoryDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("${apiPrefix}/categories")
public class CategoryController
{
    private CategoryService categoryService;

    @PostMapping("/")
    public ResponseEntity<CategoryDTO> createCategory(@Valid @RequestBody CreateCategoryDTO categoryDTO)
    {
        final var category = categoryService.createCategory(categoryDTO);

        return ResponseEntity.ok().body(category);
    }

    @GetMapping("/")
    public ResponseEntity<List<Category>> getAllCategories()
    {
        return new ResponseEntity<>(this.categoryService.findAll(), HttpStatus.OK);
    }
}
