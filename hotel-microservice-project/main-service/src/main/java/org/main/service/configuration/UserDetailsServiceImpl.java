package org.main.service.configuration;

import lombok.AllArgsConstructor;
import org.main.service.entity.AccountStatus;
import org.main.service.entity.User;
import org.main.service.service.implementations.UserServiceImpl;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

@Configuration
@AllArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserServiceImpl userService;
    //
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userService.findUserByLogin(username)
                .orElseThrow(() -> new UsernameNotFoundException("USER WITH LOGIN " + username + " WASN'T FOUND"));

        System.out.println("AUTHRIZING USER WITH PARAMS: " + user);
        boolean accountStatus = user.getStatus().equals(AccountStatus.ACTIVE);

        return org.springframework.security.core.userdetails.User
                .builder()
                .username(username)
                .password(user.getPassword())
                .authorities(user.getRole().getAuthorities())
                .disabled(!accountStatus)
                .build();
    }
}