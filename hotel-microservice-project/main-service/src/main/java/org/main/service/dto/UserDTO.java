package org.main.service.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO implements Serializable {
    private int id;
    private String name;
    private String surname;
    private String nickname;
    private String mail;
    private String password;
    private BigDecimal balance;
    private Integer yearsOld;
    private String phone;
}
