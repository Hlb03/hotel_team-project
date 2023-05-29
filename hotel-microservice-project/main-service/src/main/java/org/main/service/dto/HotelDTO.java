package org.main.service.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.main.service.entity.Room;

import java.io.Serializable;
import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class HotelDTO implements Serializable {
    private int id;
    private String name;
    private int location;
    private String locationName;
//    List<RoomDTO> rooms;
}
