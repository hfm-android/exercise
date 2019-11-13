package com.omid.exercise.catalog.service;

import com.omid.exercise.catalog.controller.api.user.AuthorityDto;
import com.omid.exercise.catalog.entity.Authority;
import com.omid.exercise.catalog.entity.User;
import com.omid.exercise.catalog.exception.DuplicateEntityException;
import com.omid.exercise.catalog.exception.EntityNotFoundException;
import com.omid.exercise.catalog.repository.UserRepository;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Transactional
public class UserServiceImpl implements UserDetailsService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional(readOnly = true)
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public UserDetails loadUserByUsername(String username) {
        Optional<User> optionalUser = userRepository.findByUsername(username);
        User user = optionalUser.orElseThrow(() -> new UsernameNotFoundException("username not found"));
        Set<SimpleGrantedAuthority> authorities = user.getAuthorities().stream()
                .map(authority -> new SimpleGrantedAuthority(authority.getType().name()))
                .collect(Collectors.toSet());
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), authorities);
    }

    public User save(User user) {
        if (userRepository.existsByUsername(user.getUsername())) {
            throw new DuplicateEntityException(user.getUsername() + " is duplicated");
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    public void delete(String username) {
        if (!userRepository.existsByUsername(username)) {
            throw new EntityNotFoundException(username + " not found");
        }
        userRepository.deleteByUsername(username);
    }

    public void authorize(String username, Set<AuthorityDto> authorities) {
        Optional<User> optionalUser = userRepository.findByUsername(username);
        User user = optionalUser.orElseThrow(() -> new EntityNotFoundException(username + " not found"));
        user.getAuthorities().clear();
        user.setAuthorities(authorities.stream().map(authorityDto -> {
            Authority authority = new Authority();
            authority.setId(authorityDto.getId());
            return authority;
        }).collect(Collectors.toSet()));
        userRepository.save(user);
    }
}
