package com.ecommerce.product.repository;

import com.ecommerce.product.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long>
{
    @Query("SELECT p FROM Product p WHERE p.category.id = :categoryId AND p.name = :name")
    Optional<Product> findByCategoryIdAndName(@Param("categoryId") Long categoryId, @Param("name") String name);
}
