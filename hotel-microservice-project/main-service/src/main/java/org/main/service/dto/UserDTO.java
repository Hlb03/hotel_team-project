package org.main.service.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.main.service.entity.Role;

import java.io.Serializable;
import java.math.BigDecimal;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO implements Serializable {
    private int id;
    private String firstName;
    private String lastName;
    private String login;
    private String password;
    private BigDecimal balance;
    private Integer age;
    private String phoneNumber;
}
