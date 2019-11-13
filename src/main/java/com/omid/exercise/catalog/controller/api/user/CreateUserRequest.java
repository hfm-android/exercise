package com.omid.exercise.catalog.controller.api.user;

import javax.validation.constraints.NotEmpty;
import java.util.Set;

public class CreateUserRequest {
    @NotEmpty(message = "Provide a username")
    private String username;
    @NotEmpty(message = "Provide a password")
    private String password;
    @NotEmpty(message = "Provide a authority")
    private Set<AuthorityDto> authorities;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<AuthorityDto> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(Set<AuthorityDto> authorities) {
        this.authorities = authorities;
    }
}
