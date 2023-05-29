package org.main.service.transformation;

import lombok.AllArgsConstructor;
import org.main.service.dto.HotelDTO;
import org.main.service.entity.Hotel;
import org.main.service.entity.Location;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class HotelTransform {

    private final RoomTransform roomTransform;

    public HotelDTO dtoTaking(Hotel hotel) {
        return HotelDTO.builder()
                .id(hotel.getId())
                .name(hotel.getName())
                .location(hotel.getLocation().getId())
//                .rooms(hotel.getRooms()
//                        .stream()
//                        .map(roomTransform::dtoTaking)
//                        .collect(Collectors.toList()))
                .locationName(hotel.getLocation().getName())
                .build();
    }

    public Hotel entityTake(HotelDTO hotelDTO) {
        return Hotel.builder()
                .id(hotelDTO.getId())
                .name(hotelDTO.getName())
                .location(
                        Location.builder()
                                .id(hotelDTO.getLocation())
                                .build()
                )
//                .rooms(hotelDTO.getRooms()
//                        .stream()
//                        .map(roomTransform::entityTake)
//                        .collect(Collectors.toList()))
                .build();
    }
}
