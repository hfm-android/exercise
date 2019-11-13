package com.omid.exercise.catalog.controller;

import com.omid.exercise.catalog.controller.api.user.*;
import com.omid.exercise.catalog.entity.User;
import com.omid.exercise.catalog.service.UserServiceImpl;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
public class UserController {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);
    private final UserServiceImpl userService;
    private final ModelMapper modelMapper;

    @Autowired
    public UserController(UserServiceImpl userService, ModelMapper modelMapper) {
        this.userService = userService;
        this.modelMapper = modelMapper;
    }

    @GetMapping(value = "/private/users")
    public List<UserDto> findAll(Principal principal) {
        LOGGER.info("Find all users service was called by {}", principal.getName());
        List<User> users = userService.findAll();
        List<UserDto> userDtoList = users.stream().map(user -> modelMapper.map(user, UserDto.class)).collect(Collectors.toList());
        LOGGER.info("Find all users service response {}", userDtoList);
        return userDtoList;
    }

    @PostMapping(value = "/private/users")
    @ResponseStatus(code = HttpStatus.CREATED)
    public CreateUserResponse create(@RequestBody @Valid CreateUserRequest request, Principal principal) {
        LOGGER.info("Create user service was called by {}", principal.getName());
        User user = userService.save(modelMapper.map(request, User.class));
        CreateUserResponse createUserResponse = modelMapper.map(user, CreateUserResponse.class);
        LOGGER.info("Create user service response {}", createUserResponse);
        return createUserResponse;
    }

    @DeleteMapping(value = "/private/users/{username}")
    public void delete(@PathVariable String username,Principal principal) {
        LOGGER.info("Delete user service was called by {}", principal.getName());
        userService.delete(username);
    }

    @PutMapping(value = "/private/users/authorize")
    public void authorize(@RequestBody @Valid AuthorizeRequest authorizeRequest,Principal principal) {
        LOGGER.info("Authorize service was called by {} with request {}",principal.getName(),authorizeRequest);
        userService.authorize(authorizeRequest.getUsername(), authorizeRequest.getAuthorities());
    }
}
