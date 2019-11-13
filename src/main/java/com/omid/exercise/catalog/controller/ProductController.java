package com.omid.exercise.catalog.controller;

import com.omid.exercise.catalog.controller.api.product.CreateProductRequest;
import com.omid.exercise.catalog.controller.api.product.CreateProductResponse;
import com.omid.exercise.catalog.controller.api.product.ProductDto;
import com.omid.exercise.catalog.controller.api.product.UpdateProductRequest;
import com.omid.exercise.catalog.entity.Product;
import com.omid.exercise.catalog.service.ProductService;
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
public class ProductController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ProductController.class);
    private ProductService productService;
    private ModelMapper modelMapper;

    @Autowired
    public ProductController(ProductService productService, ModelMapper modelMapper) {
        this.productService = productService;
        this.modelMapper = modelMapper;
    }

    @GetMapping(value = "/public/products/category/{categoryId}")
    public List<ProductDto> findAllByCategory(@PathVariable int categoryId) {
        LOGGER.info("Find all products service was called");
        List<Product> products = productService.findAllByCategory(categoryId);
        List<ProductDto> productDtoList = products.stream().map(product -> modelMapper.map(product, ProductDto.class)).collect(Collectors.toList());
        LOGGER.info("Find all products service response {}", productDtoList);
        return productDtoList;
    }


    @PostMapping(value = "/private/products")
    @ResponseStatus(code = HttpStatus.CREATED)
    public CreateProductResponse create(@RequestBody @Valid CreateProductRequest request, Principal principal) {
        LOGGER.info("Create product  service was called by {} with request {}", principal.getName(), request);
        int id = productService.save(modelMapper.map(request, Product.class)).getId();
        LOGGER.info("Product created : {}", id);
        return new CreateProductResponse(id);
    }

    @PutMapping(value = "/private/products")
    public void update(@RequestBody @Valid UpdateProductRequest request,Principal principal) {
        LOGGER.info("update product service was called by {} with request {}", principal.getName(), request);
        productService.update(modelMapper.map(request, Product.class));
        LOGGER.info("User {} updated the product {}", principal.getName(), request.getId());
    }

    @DeleteMapping(value = "/private/products/{id}")
    public void delete(@PathVariable int id,Principal principal) {
        LOGGER.info("Delete product service was called by {} with request id:{}", principal.getName(), id);
        productService.delete(id);
        LOGGER.info("User {} deleted the product {}", principal.getName(), id);
    }
}
