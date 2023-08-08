package org.main.service.controller;

import lombok.AllArgsConstructor;
import org.main.service.dto.LocationDTO;
import org.main.service.entity.Location;
import org.main.service.service.LocationService;
import org.main.service.mapper.LocationMapper;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/locations")
@AllArgsConstructor
@PreAuthorize("hasAuthority('WRITE')")
public class LocationController {

    private final LocationService locationService;
    private final LocationMapper locationMapper;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void addNewLocation(@RequestBody LocationDTO location) {
        locationService.addLocation(locationMapper.entityTake(location));
    }

    @GetMapping("/{locationName}")
    public LocationDTO findLocationByName(@PathVariable String locationName) {
        return locationMapper.dtoTaking(locationService.findLocationByName(locationName));
    }

    @GetMapping
    public List<LocationDTO> findAllLocations() {
        return locationService.getAllLocations()
                .stream()
                .map(locationMapper::dtoTaking)
                .collect(Collectors.toList());
    }

    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    public void updateLocation(@RequestBody LocationDTO location) {
        locationService.updateLocation(locationMapper.entityTake(location));
    }

    @DeleteMapping("/drop/{locationId}")
    @ResponseStatus(HttpStatus.OK)
    public void dropLocation(@PathVariable int locationId) {
        locationService.deleteLocation(locationId);
    }
}
