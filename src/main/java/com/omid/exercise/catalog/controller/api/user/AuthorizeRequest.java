package com.omid.exercise.catalog.controller.api.user;

import javax.validation.constraints.NotEmpty;
import java.util.Set;

public class AuthorizeRequest {
    @NotEmpty(message = "Provide a username")
    private String username;
    @NotEmpty(message = "Provide authority")
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
        return "AuthorizeRequest{" +
                "username='" + username + '\'' +
                ", authorities=" + authorities +
                '}';
    }
}
