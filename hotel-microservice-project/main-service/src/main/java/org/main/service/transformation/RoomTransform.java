package org.main.service.transformation;

import org.main.service.dto.RoomDTO;
import org.main.service.entity.Hotel;
import org.main.service.entity.Room;
import org.springframework.stereotype.Component;

import java.io.Serializable;

@Component
public class RoomTransform implements Serializable {

    public RoomDTO dtoTaking(Room room) {
        return RoomDTO.builder()
                .id(room.getId())
                .description(room.getDescription())
                .price(room.getPrice())
                .imageReference(room.getImageReference())
                .rate(room.getRate())
                .hotel(room.getHotel().getId())
                .build();
    }

    public Room entityTake(RoomDTO roomDTO) {
        return Room.builder()
                .id(roomDTO.getId())
                .description(roomDTO.getDescription())
                .imageReference(roomDTO.getImageReference())
                .price(roomDTO.getPrice())
                .rate(roomDTO.getRate())
                .hotel(
                        Hotel.builder()
                                .id(roomDTO.getHotel())
                                .build()
                )
                .build();
    }
}
