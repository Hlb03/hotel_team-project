package org.main.service.mapper;

import org.main.service.dto.UserRoomsDTO;
import org.main.service.entity.Room;
import org.main.service.entity.UserRooms;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserRoomsMapper {

    private final RoomMapper roomTransform;

    @Autowired
    public UserRoomsMapper(RoomMapper roomTransform) {
        this.roomTransform = roomTransform;
    }

    public UserRoomsDTO dtoTaking(UserRooms userRooms) {
        return UserRoomsDTO.builder()
                .id(userRooms.getId())
                .startRent(userRooms.getStartRent())
                .endRent(userRooms.getEndRent())
                .roomDTO(
                        roomTransform.dtoTaking(userRooms.getRoom())
                )
                .build();
    }

    public UserRooms entityTake(UserRoomsDTO userRoomsDTO, int roomId) {
        return UserRooms.builder()
                .id(userRoomsDTO.getId())
                .startRent(userRoomsDTO.getStartRent())
                .endRent(userRoomsDTO.getEndRent())
                .room(
                        Room.builder()
                                .id(roomId)
                                .build()
                )
                .build();
    }
}
