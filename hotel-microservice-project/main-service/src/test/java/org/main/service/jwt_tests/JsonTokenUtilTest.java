package org.main.service.jwt_tests;

import io.jsonwebtoken.ExpiredJwtException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.main.service.utilities.JsonTokenUtil;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.test.util.ReflectionTestUtils;

import io.jsonwebtoken.SignatureException;
import java.time.Duration;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


public class JsonTokenUtilTest {

    String token;
    JsonTokenUtil tokenUtil = new JsonTokenUtil();

    @BeforeEach
    void createTokenBasedOnUser() {
        List<String> authorities = Arrays.asList("READ", "WRITE", "DELETE");

        ReflectionTestUtils.setField(tokenUtil, "lifetime", Duration.ofMinutes(1L));
        ReflectionTestUtils.setField(tokenUtil, "secret", "new_secret");

        UserDetails userDetails = new User(
                "test_user",
                "test_password",
                    authorities
                            .stream()
                            .map(SimpleGrantedAuthority::new)
                            .collect(Collectors.toList())
                );

        token = tokenUtil.generateToken(userDetails);
    }

    @Test
    void compareUsernameFromToken() {
        assertEquals("test_user", tokenUtil.getUsernameFromToken(token));
    }

    @Test
    void compareAuthoritiesFromToken() {
        List<String> authorities = Arrays.asList("DELETE", "READ", "WRITE");
        assertEquals(authorities, tokenUtil.getAuthoritiesFromToken(token));
    }

    @Test
    void checkIncorrectSignatureExceptionThrowing() {
        String incorrectToken =
                token.substring(0, token.length() - 2) + token.charAt(token.length() - 1) + token.substring(token.length() - 2);

        assertThrows(SignatureException.class, () -> tokenUtil.getUsernameFromToken(incorrectToken));
    }

    @Test
    void checkExpiredTokenExceptionThrowing() throws InterruptedException {
        Thread.sleep(60_000); // waiting for token to expire
        assertThrows(ExpiredJwtException.class, () -> tokenUtil.getUsernameFromToken(token));
    }
}
