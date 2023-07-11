package org.main.service.controller;

import jakarta.ws.rs.HttpMethod;
import lombok.AllArgsConstructor;
import org.main.service.dto.LocationDTO;
import org.main.service.entity.Location;
import org.main.service.service.LocationService;
import org.main.service.transformation.LocationTransform;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
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
    private final LocationTransform transform;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void addNewLocation(@RequestBody LocationDTO location) {
        locationService.addLocation(transform.entityTake(location));
    }

    @GetMapping("/{locationName}")
    public LocationDTO findLocationByName(@PathVariable String locationName) {
        Location location = locationService.findLocationByName(locationName);
        return transform.dtoTaking(location);
    }

    @GetMapping
    public List<LocationDTO> findAllLocations() {
        return locationService.getAllLocations()
                .stream()
                .map(transform::dtoTaking)
                .collect(Collectors.toList());
    }

    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    public void updateLocation(@RequestBody LocationDTO location) {
        locationService.updateLocation(transform.entityTake(location));
    }

    @DeleteMapping("/drop/{locationId}")
    @ResponseStatus(HttpStatus.OK)
    public void dropLocation(@PathVariable int locationId) {
        locationService.deleteLocation(locationId);
    }
}
