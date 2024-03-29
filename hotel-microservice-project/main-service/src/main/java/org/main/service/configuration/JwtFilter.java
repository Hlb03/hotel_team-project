package org.main.service.configuration;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.SignatureException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.main.service.utilities.JsonTokenUtil;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.util.stream.Collectors;

@Slf4j
@Configuration
@RequiredArgsConstructor
public class JwtFilter extends OncePerRequestFilter {

    private final JsonTokenUtil jsonToken;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String header = request.getHeader("Authorization");
        String username = null;
        String token = null;

        if (header != null && header.startsWith("Bearer ")) {
            token = header.substring(7);
            try {
                username = jsonToken.getUsernameFromToken(token);
            } catch (ExpiredJwtException e) {
                log.info("Token is already expired, please authorize again");
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Provided authentication token is already expired. Log in again");
            } catch (SignatureException e) {
                log.info("Signature exception doesn't match original ones");
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Provided token is invalid.");
            }
        }

        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                    username,
                    null,

                    jsonToken.getAuthoritiesFromToken(token)
                            .stream()
                            .map(SimpleGrantedAuthority::new)
                            .collect(Collectors.toList())
            );
            SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        }

        log.info("AUTHENTICATION: " + SecurityContextHolder.getContext().getAuthentication());
        doFilter(request, response, filterChain);
    }
}
