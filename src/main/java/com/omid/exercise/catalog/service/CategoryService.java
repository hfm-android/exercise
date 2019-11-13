package com.omid.exercise.catalog.service;

import com.omid.exercise.catalog.entity.Category;
import com.omid.exercise.catalog.exception.DuplicateEntityException;
import com.omid.exercise.catalog.exception.EntityNotFoundException;
import com.omid.exercise.catalog.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class CategoryService {
    private CategoryRepository categoryRepository;

    @Autowired
    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Transactional(readOnly = true)
    public List<Category> findAll() {
        return categoryRepository.findAll();
    }


    public boolean existsById(int id) {
        return categoryRepository.existsById(id);
    }

    public Category save(Category category) {
        String name = category.getName();
        if (categoryRepository.existsByName(name)) {
            throw new DuplicateEntityException("Category with name " + name + " is duplicated");
        }
        return categoryRepository.save(category);
    }

    public void update(Category category) {
        int id = category.getId();
        if (!categoryRepository.existsById(id)) {
            throw new EntityNotFoundException("Category with id " + id + " not found");
        }
        categoryRepository.updateCategoryName(category.getId(), category.getName());
    }

    public void deleteById(int categoryId) {
        if (!categoryRepository.existsById(categoryId)) {
            throw new EntityNotFoundException("Category with id " + categoryId + " not found");
        }
        categoryRepository.deleteById(categoryId);
    }
}
