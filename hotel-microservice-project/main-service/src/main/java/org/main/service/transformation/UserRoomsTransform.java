package org.main.service.transformation;

import org.main.service.dto.UserRoomsDTO;
import org.main.service.entity.Room;
import org.main.service.entity.User;
import org.main.service.entity.UserRooms;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserRoomsTransform {

    private final RoomTransform roomTransform;

    @Autowired
    public UserRoomsTransform(RoomTransform roomTransform) {
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
                .user(
                        User.builder()
                                .id(userRoomsDTO.getUserId())
                                .build()
                )
                .room(
                        Room.builder()
                                .id(roomId)
                                .build()
                )
                .build();
    }
}
