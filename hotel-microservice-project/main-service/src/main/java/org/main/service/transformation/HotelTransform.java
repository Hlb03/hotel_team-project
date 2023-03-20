package org.main.service.transformation;

import org.main.service.dto.HotelDTO;
import org.main.service.entity.Hotel;
import org.main.service.entity.Location;
import org.springframework.stereotype.Component;

@Component
public class HotelTransform {

    public HotelDTO dtoTaking(Hotel hotel) {
        return HotelDTO.builder()
                .id(hotel.getId())
                .name(hotel.getName())
                .location(hotel.getLocation().getId())
                .rooms(hotel.getRooms())
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
                .rooms(hotelDTO.getRooms())
                .build();
    }
}
