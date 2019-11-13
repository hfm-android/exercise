package com.omid.exercise.catalog;

import com.omid.exercise.catalog.controller.api.user.AuthorityDto;
import com.omid.exercise.catalog.entity.User;
import com.omid.exercise.catalog.entity.enums.AuthorityType;
import com.omid.exercise.catalog.exception.DuplicateEntityException;
import com.omid.exercise.catalog.exception.EntityNotFoundException;
import com.omid.exercise.catalog.repository.UserRepository;
import com.omid.exercise.catalog.service.UserServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
public class TestUserService {
    @Mock
    private UserRepository userRepository;
    @Mock
    private PasswordEncoder passwordEncoder;
    private UserServiceImpl userService;

    @Before
    public void init() {
        userService = new UserServiceImpl(userRepository, passwordEncoder);
    }

    @Test(expected = DuplicateEntityException.class)
    public void saveDuplicateUser_duplicated() {
        when(userRepository.existsByUsername(anyString())).thenReturn(true);
        User user = new User();
        user.setUsername("test");
        userService.save(user);
    }


    @Test(expected = EntityNotFoundException.class)
    public void deleteUser_notFound() {
        when(userRepository.existsByUsername(anyString())).thenReturn(false);
        userService.delete("test");
    }

    @Test(expected = EntityNotFoundException.class)
    public void authorizeUser_notFound() {
        when(userRepository.findByUsername("test")).thenReturn(Optional.empty());
        Set<AuthorityDto> authorityDtoSet = new HashSet<>();
        AuthorityDto authorityDto = new AuthorityDto();
        authorityDto.setId(1);
        authorityDto.setType(AuthorityType.ADMIN);
        authorityDtoSet.add(authorityDto);
        userService.authorize("test", authorityDtoSet);
    }
}
