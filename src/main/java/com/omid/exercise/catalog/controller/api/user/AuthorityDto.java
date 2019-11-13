package com.omid.exercise.catalog.controller.api.user;

import com.omid.exercise.catalog.entity.enums.AuthorityType;

import javax.validation.constraints.Positive;

public class AuthorityDto {
    @Positive(message = "Id must be a positive number")
    private int id;
    private AuthorityType type;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public AuthorityType getType() {
        return type;
    }

    public void setType(AuthorityType type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "AuthorityDto{" +
                "id=" + id +
                ", type=" + type +
                '}';
    }
}
