package com.omid.exercise.catalog;

import com.omid.exercise.catalog.entity.Category;
import com.omid.exercise.catalog.exception.DuplicateEntityException;
import com.omid.exercise.catalog.exception.EntityNotFoundException;
import com.omid.exercise.catalog.repository.CategoryRepository;
import com.omid.exercise.catalog.service.CategoryService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringRunner;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
public class TestCategoryService {

    @Mock
    private CategoryRepository categoryRepository;
    private CategoryService categoryService;

    @Before
    public void init() {
        categoryService = new CategoryService(categoryRepository);
    }

    @Test(expected = DuplicateEntityException.class)
    public void saveCategory_duplicated() {
        when(categoryRepository.existsByName("clothes")).thenReturn(true);
        Category clothes = new Category();
        clothes.setName("clothes");
        categoryService.save(clothes);
    }

    @Test
    public void saveCategory_nonDuplicated() {
        when(categoryRepository.existsByName("clothes")).thenReturn(false);
        Category clothes = new Category();
        clothes.setName("clothes");
        when(categoryRepository.save(any())).thenReturn(clothes);
        categoryService.save(clothes);
    }

    @Test(expected = EntityNotFoundException.class)
    public void updateCategory_notFound() {
        when(categoryRepository.existsById(any())).thenReturn(true);
        Category clothes = new Category();
        clothes.setName("clothes");
        clothes.setId(1);
        doNothing().when(categoryRepository).updateCategoryName(anyInt(), anyString());
        categoryService.update(clothes);
    }

    @Test(expected = EntityNotFoundException.class)
    public void deleteCategory_notFound() {
        when(categoryRepository.existsById(any())).thenReturn(true);
        doNothing().when(categoryRepository).deleteById(anyInt());
        categoryService.deleteById(1);
    }

}
