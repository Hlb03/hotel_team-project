package org.main.service.utilities;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

import java.util.Random;

@Component
@Scope(value = WebApplicationContext.SCOPE_APPLICATION)
public class RandomStringGenerator {

    private static final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";

    public String generateRandomString(int length) {
        Random random = new Random();
        StringBuilder sb = new StringBuilder(length);

        for (int i = 0; i < length; i++) {
            char randomChar = CHARACTERS.charAt(random.nextInt(CHARACTERS.length()));
            sb.append(randomChar);
        }

        return sb.toString();
    }
}