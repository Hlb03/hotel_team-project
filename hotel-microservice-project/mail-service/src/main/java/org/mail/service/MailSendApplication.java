package org.mail.service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

// PORT 8081
@SpringBootApplication
public class MailService
{
    public static void main( String[] args ) {
        SpringApplication.run(MailService.class, args);
    }
}
