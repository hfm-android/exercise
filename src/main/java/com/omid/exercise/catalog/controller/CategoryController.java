package com.omid.exercise.catalog.controller;

import com.omid.exercise.catalog.controller.api.category.CategoryDto;
import com.omid.exercise.catalog.controller.api.category.CreateCategoryRequest;
import com.omid.exercise.catalog.controller.api.category.UpdateCategoryRequest;
import com.omid.exercise.catalog.entity.Category;
import com.omid.exercise.catalog.service.CategoryService;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
public class CategoryController {

    private static final Logger LOGGER = LoggerFactory.getLogger(CategoryController.class);
    private final CategoryService categoryService;
    private final ModelMapper modelMapper;

    @Autowired
    public CategoryController(CategoryService categoryService, ModelMapper modelMapper) {
        this.categoryService = categoryService;
        this.modelMapper = modelMapper;
    }

    @GetMapping(value = "/public/categories")
    public List<CategoryDto> findAll() {
        LOGGER.info("Find all categories service was called");
        List<Category> categories = categoryService.findAll();
        List<CategoryDto> categoryDtoList = categories.stream()
                .map(category -> modelMapper.map(category, CategoryDto.class)).collect(Collectors.toList());
        LOGGER.info("Find all categories service response {}", categoryDtoList);
        return categoryDtoList;
    }

    @PostMapping(value = "/private/categories")
    @ResponseStatus(code = HttpStatus.CREATED)
    public CategoryDto create(@RequestBody @Valid CreateCategoryRequest request, Principal principal) {
        LOGGER.info("Create category service was called by {} with request {}", principal.getName(), request);
        Category category = categoryService.save(modelMapper.map(request, Category.class));
        CategoryDto categoryDto = modelMapper.map(category, CategoryDto.class);
        LOGGER.info("Category created : {}", categoryDto);
        return categoryDto;
    }

    @PutMapping(value = "/private/categories")
    public void update(@RequestBody @Valid UpdateCategoryRequest request, Principal principal) {
        LOGGER.info("update category service was called by {} with request {}", principal.getName(), request);
        categoryService.update(modelMapper.map(request, Category.class));
        LOGGER.info("User {} updated the category {}", principal.getName(), request.getId());
    }

    @DeleteMapping(value = "/private/categories/{id}")
    public void delete(@PathVariable int id, Principal principal) {
        LOGGER.info("Delete category service was called by {} with request id:{}", principal.getName(), id);
        categoryService.deleteById(id);
        LOGGER.info("User {} deleted the category {}", principal.getName(), id);
    }
}
