package com.ecommerce.product.service;

import com.ecommerce.product.entity.Category;
import com.ecommerce.product.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.ecommerce.dto.category.CategoryDTO;
import org.ecommerce.dto.category.CreateCategoryDTO;
import org.ecommerce.exception.EntityDuplicateException;
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

        log.info("Category Created {}", category);

        return modelMapper.map(category, CategoryDTO.class);
    }

    public CategoryDTO updateCategory(CreateCategoryDTO categoryDTO, Long id)
    {
        log.info("[updateCategory] Updating category {}", categoryDTO);

        var oldCategory = this.findCategoryById(id);

        var checkName = this.categoryRepository.findByName(categoryDTO.getName()).orElse(null);

        if (checkName != null && !checkName.getId().equals(id))
            throw new EntityDuplicateException("Category Name is already in use");

        oldCategory.setName(categoryDTO.getName());
        oldCategory.setDescription(categoryDTO.getDescription());

        oldCategory = categoryRepository.save(oldCategory);

        log.info("[updateCategory] Category Updated {}", oldCategory);

        return modelMapper.map(oldCategory, CategoryDTO.class);
    }

    public CategoryDTO findById(Long id)
    {
        log.debug("Finding category with id {}", id);

        var category = this.findCategoryById(id);

        return modelMapper.map(category, CategoryDTO.class);
    }

    public List<CategoryDTO> findAll()
    {
        var categories = this.categoryRepository.findAll();

        return categories
            .stream()
            .map(category -> modelMapper.map(category, CategoryDTO.class))
            .toList();
    }

    public Category findCategoryById(Long id)
    {
        return categoryRepository.findById(id)
            .orElseThrow(() -> {
                log.debug("Category with id {} not found", id);

                return new EntityNotFoundException("Category with id " + id + " not found");
            });
    }

    public Category findCategoryByName(String name)
    {
        return categoryRepository.findByName(name)
            .orElseThrow(() -> {
                log.debug("Category with name {} not found", name);

                return new EntityNotFoundException("Category with name " + name + " not found");
            });
    }


}
