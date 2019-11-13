package com.omid.exercise.catalog.controller.api.category;

import javax.validation.constraints.Positive;

public class CategoryDto {
    @Positive( message = "ID must be a positive number")
    private int id;
    private String name;

    public CategoryDto() {
    }

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
        return "CategoryDto{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
