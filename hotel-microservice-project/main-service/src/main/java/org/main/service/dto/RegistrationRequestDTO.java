package org.main.service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegistrationRequestDTO {
    private String name;
    private String surname;
    private String nickname;
    private String mail;
    private Integer yearsOld;
    private String phone;
    private String password;
    private String confirmPassword;
}
