package com.omid.exercise.catalog.controller.api.category;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Positive;

public class UpdateCategoryRequest {
    @Positive(message = "ID must be a positive number")
    private int id;
    @NotEmpty(message = "You should provide a category name")
    private String name;

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

    @Override
    public String toString() {
        return "UpdateCategoryRequest{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
