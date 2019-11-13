package com.omid.exercise.catalog.controller.api.product;

import com.omid.exercise.catalog.controller.api.category.CategoryDto;

import javax.validation.constraints.NotEmpty;
import java.util.Set;

public class CreateProductRequest {
    @NotEmpty(message = "Provide a product name")
    private String name;
    @NotEmpty(message = "Provide a product brand")
    private String brand;
    private String description;
    @NotEmpty(message = "Provide a price")
    private String price;
    @NotEmpty(message = "provide at least one category for the product")
    private Set<CategoryDto> categories;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public Set<CategoryDto> getCategories() {
        return categories;
    }

    public void setCategories(Set<CategoryDto> categories) {
        this.categories = categories;
    }

    @Override
    public String toString() {
        return "CreateProductRequest{" +
                "name='" + name + '\'' +
                ", brand='" + brand + '\'' +
                ", description='" + description + '\'' +
                ", price='" + price + '\'' +
                ", categories=" + categories +
                '}';
    }
}
