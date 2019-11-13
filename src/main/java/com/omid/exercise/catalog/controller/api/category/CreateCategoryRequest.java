package com.omid.exercise.catalog.controller.api.category;

import javax.validation.constraints.NotEmpty;

public class CreateCategoryRequest {
    @NotEmpty(message = "You should provide a category name")
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "CreateCategoryRequest{" +
                "name='" + name + '\'' +
                '}';
    }
}
