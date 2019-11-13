package com.omid.exercise.catalog.controller.api.product;

public class CreateProductResponse {
    private int id;

    public CreateProductResponse(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "CreateProductResponse{" +
                "id=" + id +
                '}';
    }
}
