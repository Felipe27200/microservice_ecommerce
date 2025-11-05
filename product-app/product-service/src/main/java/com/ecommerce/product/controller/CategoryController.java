package com.ecommerce.product.controller;

import com.ecommerce.product.service.CategoryService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.ecommerce.dto.category.CategoryDTO;
import org.ecommerce.dto.category.CreateCategoryDTO;
import org.springframework.http.HttpStatus;
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
@RequestMapping("${apiPrefix}/categories")
public class CategoryController
{
    private CategoryService categoryService;

    @PostMapping("/")
    public ResponseEntity<CategoryDTO> createCategory(@Valid @RequestBody CreateCategoryDTO categoryDTO)
    {
        log.debug("[createCategory] {}", categoryDTO);

        final var category = categoryService.createCategory(categoryDTO);

        log.info("[createCategory] Category created: {}", category);

        return ResponseEntity.ok().body(category);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CategoryDTO> updateCategory(@PathVariable Long id, @Valid @RequestBody CreateCategoryDTO categoryDTO)
    {
        log.debug("[updateCategory] {}", categoryDTO);

        final var categoryUpdated = this.categoryService.updateCategory(categoryDTO, id);

        log.info("[updateCategory] Category updated: {}", categoryUpdated);

        return ResponseEntity.ok().body(categoryUpdated);
    }

    @GetMapping("/")
    public ResponseEntity<List<CategoryDTO>> getAllCategories()
    {
        return new ResponseEntity<>(this.categoryService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoryDTO> getCategoryById(@PathVariable Long id)
    {
        return new ResponseEntity<>(this.categoryService.findById(id), HttpStatus.OK);
    }
}
