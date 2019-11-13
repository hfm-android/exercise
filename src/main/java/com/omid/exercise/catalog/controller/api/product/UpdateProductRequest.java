package com.omid.exercise.catalog.controller.api.product;

import com.omid.exercise.catalog.controller.api.category.CategoryDto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Positive;
import java.util.Set;

public class UpdateProductRequest {
    @Positive(message = "ID should be a positive number")
    private int id;
    private String name;
    private String brand;
    private String description;
    private String price;
    private Set<CategoryDto> categories;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

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
        return "UpdateProductRequest{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", brand='" + brand + '\'' +
                ", description='" + description + '\'' +
                ", price='" + price + '\'' +
                ", categories=" + categories +
                '}';
    }
}
