package org.main.service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MailRequestDTO {

    private String firstName;
    private String lastName;
    private String nickname;
    private String email;
}
