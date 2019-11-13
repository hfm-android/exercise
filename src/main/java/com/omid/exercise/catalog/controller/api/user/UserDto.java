package com.omid.exercise.catalog.controller.api.user;

import java.util.Set;

public class UserDto {
    private String username;
    private Set<AuthorityDto> authorities;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Set<AuthorityDto> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(Set<AuthorityDto> authorities) {
        this.authorities = authorities;
    }

    @Override
    public String toString() {
        return "UserDto{" +
                "username='" + username + '\'' +
                ", authorities=" + authorities +
                '}';
    }
}
