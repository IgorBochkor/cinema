package com.dev.cinema.security;

import com.dev.cinema.model.Role;
import com.dev.cinema.model.User;
import com.dev.cinema.service.UserService;
import java.util.stream.Collectors;
import org.springframework.security.core.userdetails.User.UserBuilder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    private final UserService userService;

    public CustomUserDetailsService(UserService userService) {
        this.userService = userService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userService.findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("Login or/and password "
                        + "is incorrect"));
        UserBuilder builder = org.springframework.security.core.userdetails.User.builder();
        builder.username(user.getEmail());
        builder.password(user.getPassword());
        builder.authorities(user.getRoles()
                .stream()
                .map(Role::getRoleName).collect(Collectors.joining(",")));
        return builder.build();
    }
}
