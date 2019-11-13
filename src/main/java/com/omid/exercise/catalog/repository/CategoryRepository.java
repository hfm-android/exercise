package com.omid.exercise.catalog.repository;

import com.omid.exercise.catalog.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CategoryRepository extends JpaRepository<Category, Integer> {

    boolean existsById(int id);

    boolean existsByName(String name);

    @Modifying
    @Query("update Category category set category.name = :name WHERE category.id = :id")
    void updateCategoryName(@Param("id") int categoryId, @Param("name") String name);
}
