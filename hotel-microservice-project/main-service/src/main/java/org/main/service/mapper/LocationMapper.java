package org.main.service.mapper;

import org.main.service.dto.LocationDTO;
import org.main.service.entity.Location;
import org.springframework.stereotype.Component;

import java.io.Serializable;

@Component
public class LocationMapper implements Serializable {

    public LocationDTO dtoTaking(Location location) {
        return LocationDTO.builder()
                .id(location.getId())
                .name(location.getName())
                .build();
    }

    public Location entityTake(LocationDTO locationDTO) {
        return Location.builder()
                .id(locationDTO.getId())
                .name(locationDTO.getName())
                .build();
    }
}
