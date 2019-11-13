package com.omid.exercise.catalog;

import com.omid.exercise.catalog.entity.Category;
import com.omid.exercise.catalog.entity.Product;
import com.omid.exercise.catalog.exception.EntityNotFoundException;
import com.omid.exercise.catalog.repository.ProductRepository;
import com.omid.exercise.catalog.service.CategoryService;
import com.omid.exercise.catalog.service.ProductService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
public class TestProductService {

    @Mock
    private ProductRepository productRepository;
    @Mock
    private CategoryService categoryService;
    private ProductService productService;

    @Before
    public void init() {
        productService = new ProductService(productRepository, categoryService);
    }

    @Test(expected = EntityNotFoundException.class)
    public void saveProduct_nonExistsCategory() {
        when(categoryService.existsById(anyInt())).thenReturn(false);
        Product product = new Product();
        Set<Category> categories = new HashSet<>();
        Category clothes = new Category();
        clothes.setId(1);
        clothes.setName("clothes");
        categories.add(clothes);
        product.setCategories(categories);
        productService.save(product);
    }

    @Test(expected = EntityNotFoundException.class)
    public void updateProduct_nonExistsCategory() {
        when(categoryService.existsById(anyInt())).thenReturn(false);
        Product product = new Product();
        Set<Category> categories = new HashSet<>();
        Category clothes = new Category();
        clothes.setId(1);
        clothes.setName("clothes");
        categories.add(clothes);
        product.setCategories(categories);
        productService.save(product);
    }

    @Test(expected = EntityNotFoundException.class)
    public void updateProduct_nonExistsProduct() {
        when(categoryService.existsById(anyInt())).thenReturn(true);
        when(productRepository.findById(anyInt())).thenReturn(Optional.empty());
        Product product = new Product();
        product.setId(1);
        Set<Category> categories = new HashSet<>();
        Category clothes = new Category();
        clothes.setId(1);
        clothes.setName("clothes");
        categories.add(clothes);
        product.setCategories(categories);
        productService.update(product);
    }
}