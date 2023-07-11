package org.main.service.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.sql.Date;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRoomsDTO implements Serializable {
    private int id;
    private Date startRent;
    private Date endRent;
    private RoomDTO roomDTO;
}
