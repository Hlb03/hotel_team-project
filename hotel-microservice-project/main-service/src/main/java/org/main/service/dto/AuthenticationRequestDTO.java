package org.main.service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class AuthenticationRequestDTO implements Serializable {
    private String mail;
    private String password;
}
