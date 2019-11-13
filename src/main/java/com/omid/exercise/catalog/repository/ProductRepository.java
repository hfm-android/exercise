package com.omid.exercise.catalog.repository;

import com.omid.exercise.catalog.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Integer> {

    @Query("from Product product join product.categories categories where categories.id in(:categoryId)")
    List<Product> findAllByCategory(@Param("categoryId") int categoryId);
}
