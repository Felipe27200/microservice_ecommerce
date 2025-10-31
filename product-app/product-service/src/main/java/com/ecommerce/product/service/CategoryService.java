package com.ecommerce.product.service;

import com.ecommerce.product.entity.Category;
import com.ecommerce.product.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.ecommerce.dto.category.CategoryDTO;
import org.ecommerce.dto.category.CreateCategoryDTO;
import org.ecommerce.exception.EntityNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class CategoryService
{
    private final CategoryRepository categoryRepository;
    private final ModelMapper modelMapper;

    public CategoryDTO createCategory(CreateCategoryDTO categoryDTO)
    {
        log.info("Creating new category {}", categoryDTO);

        var category = modelMapper.map(categoryDTO, Category.class);
        category = categoryRepository.save(category);

        log.info("Created category {}", category);

        return modelMapper.map(category, CategoryDTO.class);
    }

    public CategoryDTO findById(Long id)
    {
        log.debug("Finding category with id {}", id);

        var category = categoryRepository.findById(id)
                .orElseThrow(() -> {
                    log.debug("Category with id {} not found", id);

                    return new EntityNotFoundException("Category with id " + id + " not found");
                });

        return modelMapper.map(category, CategoryDTO.class);
    }

    public List<Category> findAll()
    {
        return this.categoryRepository.findAll();
    }
}
