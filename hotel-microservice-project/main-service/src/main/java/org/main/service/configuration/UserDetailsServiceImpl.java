package org.main.service.configuration;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.main.service.entity.AccountStatus;
import org.main.service.entity.User;
import org.main.service.service.implementations.UserServiceImpl;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
@AllArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserServiceImpl userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userService.findUserByLogin(username)
                .orElseThrow(() -> new UsernameNotFoundException("USER WITH LOGIN " + username + " WASN'T FOUND"));
        log.info("AUTHORIZING USER WITH PARAMS: " + user.getLogin());

        return org.springframework.security.core.userdetails.User
                .builder()
                .username(username)
                .password(user.getPassword())
                .authorities(user.getRole().getAuthorities())
                .disabled(!(user.getStatus()).equals(AccountStatus.ACTIVE))
                .accountLocked(user.getStatus().equals(AccountStatus.BLOCKED))
                .build();
    }
}