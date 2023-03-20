package org.main.service.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.sql.Timestamp;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserResponseDTO implements Serializable {
    private int id;
    private String comment;
    private Timestamp dateTimeResponse;
    private double rate;
    private int userId;
    private String userLogin;
    private int room;
}
