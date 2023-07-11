package org.main.service.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class TokenDTO implements Serializable {
    private final String token;
}
