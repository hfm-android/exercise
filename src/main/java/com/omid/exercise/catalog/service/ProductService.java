package com.omid.exercise.catalog.service;

import com.omid.exercise.catalog.entity.Product;
import com.omid.exercise.catalog.entity.Category;
import com.omid.exercise.catalog.exception.EntityNotFoundException;
import com.omid.exercise.catalog.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ProductService {

    private final ProductRepository productRepository;
    private final CategoryService categoryService;

    @Autowired
    public ProductService(ProductRepository productRepository, CategoryService categoryService) {
        this.productRepository = productRepository;
        this.categoryService = categoryService;
    }

    @Transactional(readOnly = true)
    public List<Product> findAllByCategory(int categoryId) {
        return productRepository.findAllByCategory(categoryId);
    }

    public Product save(Product product) {
        for (Category category : product.getCategories()) {
            if (!categoryService.existsById(category.getId())) {
                throw new EntityNotFoundException("The category " + category.getId() + " not found");
            }
        }
        return productRepository.save(product);
    }

    public void update(Product newProduct) {
        for (Category category : newProduct.getCategories()) {
            if (!categoryService.existsById(category.getId())) {
                throw new EntityNotFoundException("The category " + category.getId() + " not found");
            }
        }
        Optional<Product> optionalProduct = productRepository.findById(newProduct.getId());
        Product product = optionalProduct.orElseThrow(() -> new EntityNotFoundException("The newProduct " + newProduct.getId() + " not found"));
        setNewValues(product, newProduct);
        productRepository.save(product);
    }

    public void delete(int productId) {
        if (!productRepository.existsById(productId)) {
            throw new EntityNotFoundException("Product with id " + productId + " not found");
        }
        productRepository.deleteById(productId);
    }

    private void setNewValues(Product oldProduct, Product newProduct) {
        if (!StringUtils.isEmpty(newProduct.getPrice())) {
            oldProduct.setPrice(newProduct.getPrice());
        }
        if (!StringUtils.isEmpty(newProduct.getDescription())) {
            oldProduct.setDescription(newProduct.getDescription());
        }
        if (!StringUtils.isEmpty(newProduct.getBrand())) {
            oldProduct.setBrand(newProduct.getBrand());
        }
        if (!StringUtils.isEmpty(newProduct.getName())) {
            oldProduct.setName(newProduct.getName());
        }
        if (!newProduct.getCategories().isEmpty()) {
            oldProduct.getCategories().clear();
            oldProduct.getCategories().addAll(newProduct.getCategories());
        }
    }
}
