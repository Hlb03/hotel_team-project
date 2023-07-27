package org.main.service.configuration;

import jakarta.ws.rs.HttpMethod;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.HttpStatusEntryPoint;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
@AllArgsConstructor
public class WebSecurityConfig {

    private final BCryptPasswordEncoder encoder;
    private final UserDetailsServiceImpl userService;
    private final JwtFilter jwtFilter;

    // TODO: add end points based on roles
    @Bean
    protected SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .csrf().disable()
                .authorizeHttpRequests( requests -> requests
//                        .requestMatchers("/**", HttpMethod.GET, HttpMethod.POST, HttpMethod.PUT, HttpMethod.DELETE).permitAll() // temp decision while Anton is creating UI

                                .requestMatchers(HttpMethod.GET, "/rooms/**").permitAll()
                                .requestMatchers(HttpMethod.GET, "/response/room/*").permitAll()
                                .requestMatchers(HttpMethod.POST, "/registration").permitAll()
                                .requestMatchers(HttpMethod.POST, "/auth").permitAll()
                                .requestMatchers(HttpMethod.POST, "/activate-account/*").permitAll()

                                .anyRequest().authenticated()
                )
                // TODO: Not sure whether token based application should contain this function (in case should -> set expiration time in token to now)
//                .logout(logout -> {
//                    new AntPathRequestMatcher("/logout", HttpMethod.POST);
//                    logout.invalidateHttpSession(true);
//                    logout.clearAuthentication(true);
//                    logout.deleteCookies("JSESSIONID");
//                })
                .exceptionHandling(exception -> exception.authenticationEntryPoint(new HttpStatusEntryPoint(HttpStatus.UNAUTHORIZED))) // create exception controller advice
                .authenticationProvider(daoAuthenticationProvider())
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)) // REST applications doesn't support default Spring sessions (via cookies)
                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }

    @Bean
    protected DaoAuthenticationProvider daoAuthenticationProvider() {
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setPasswordEncoder(encoder);
        daoAuthenticationProvider.setUserDetailsService(userService);
        return daoAuthenticationProvider;
    }

    @Bean
    protected AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }
}