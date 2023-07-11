package org.main.service.mapper;

import lombok.AllArgsConstructor;
import org.main.service.dto.RoomDTO;
import org.main.service.entity.Hotel;
import org.main.service.entity.Room;
import org.springframework.stereotype.Component;

import java.io.Serializable;

@Component
@AllArgsConstructor
public class RoomMapper implements Serializable {

    private final UserResponseMapper responseTransform;

    public RoomDTO dtoTaking(Room room) {
        return RoomDTO.builder()
                .id(room.getId())
                .shortDescription(room.getBriefDescription())
                .longDescription(room.getDescription())
                .price(room.getPrice())
                .amountOfPerson(room.getPersonAmount())
                .photoRoom(room.getImageReference())
                .rate(room.getTotalRate())
                .hotel(room.getHotel().getId())
                .comment(
                        room.getResponses()
                        .stream()
                        .map(responseTransform::dtoTaking)
                        .toList()
                ).build();
    }

    public Room entityTake(RoomDTO roomDTO) {
        return Room.builder()
                .id(roomDTO.getId())
                .briefDescription(roomDTO.getShortDescription())
                .description(roomDTO.getLongDescription())
                .imageReference(roomDTO.getPhotoRoom().toString())
                .price(roomDTO.getPrice())
                .personAmount(roomDTO.getAmountOfPerson())
                .hotel(
                        Hotel.builder()
                                .id(roomDTO.getHotel())
                                .build()
                )
                .totalRate(roomDTO.getRate())
                .build();
    }
}
