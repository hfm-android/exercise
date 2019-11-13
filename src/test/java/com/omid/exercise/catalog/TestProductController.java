package com.omid.exercise.catalog;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.omid.exercise.catalog.controller.api.category.CategoryDto;
import com.omid.exercise.catalog.controller.api.product.CreateProductRequest;
import com.omid.exercise.catalog.controller.api.product.ProductDto;
import com.omid.exercise.catalog.entity.Product;
import com.omid.exercise.catalog.service.ProductService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.security.Principal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static junit.framework.TestCase.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = CatalogApplication.class)
@WebAppConfiguration
public class TestProductController {


    @Autowired
    private WebApplicationContext webApplicationContext;
    @MockBean
    private Principal mockPrincipal;
    @MockBean
    private ProductService productService;
    private MockMvc mvc;

    @org.junit.jupiter.api.Test
    void contextLoads() {
    }

    @Before
    public void setUp() {
        mvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
        Mockito.when(mockPrincipal.getName()).thenReturn("test");
    }

    private String mapToJson(Object obj) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(obj);
    }

    private <T> T mapFromJson(String json, Class<T> clazz)
            throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(json, clazz);
    }

    @Test
    public void findAllByCategory() throws Exception {
        List<Product> products = new ArrayList<>();
        Product product = new Product();
        product.setId(1);
        product.setName("jacket");
        products.add(product);
        Mockito.when(productService.findAllByCategory(anyInt())).thenReturn(products);
        MvcResult mvcResult = mvc.perform(get("/public/products/category/1")
                .accept(MediaType.APPLICATION_JSON)).andReturn();
        Assert.assertEquals(200, mvcResult.getResponse().getStatus());
        String content = mvcResult.getResponse().getContentAsString();
        ProductDto[] productDtos = mapFromJson(content, ProductDto[].class);
        assertEquals(1, productDtos[0].getId());
        assertEquals("jacket", productDtos[0].getName());
    }


    @Test
    public void createTest() throws Exception {
        CreateProductRequest createProductRequest = new CreateProductRequest();
        createProductRequest.setName("jacket");
        createProductRequest.setBrand("LC");
        createProductRequest.setPrice("120");
        CategoryDto categoryDto = new CategoryDto();
        categoryDto.setId(1);
        categoryDto.setName("clothes");
        Set<CategoryDto> categoryDtos = new HashSet<>();
        categoryDtos.add(categoryDto);
        createProductRequest.setCategories(categoryDtos);
        Product savedProduct = new Product();
        savedProduct.setId(10);
        Mockito.when(productService.save(any())).thenReturn(savedProduct);
        MvcResult mvcResult = mvc.perform(post("/private/products")
                .accept(MediaType.APPLICATION_JSON)
                .principal(mockPrincipal)
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapToJson(createProductRequest))).andReturn();
        Assert.assertEquals(201, mvcResult.getResponse().getStatus());
        String content = mvcResult.getResponse().getContentAsString();
        ProductDto productDto = mapFromJson(content, ProductDto.class);
        assertEquals(10,productDto.getId());
    }
}
