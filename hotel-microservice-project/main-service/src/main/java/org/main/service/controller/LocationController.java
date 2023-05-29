package org.main.service.controller;

import lombok.AllArgsConstructor;
import org.main.service.dto.LocationDTO;
import org.main.service.entity.Location;
import org.main.service.service.LocationService;
import org.main.service.transformation.LocationTransform;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/locations")
@AllArgsConstructor
public class LocationController {

    private final LocationService locationService;
    private final LocationTransform transform;

    @PostMapping
    public ResponseEntity<Integer> addNewLocation(@RequestBody LocationDTO location) {
        locationService.addLocation(transform.entityTake(location));
        return ResponseEntity.status(HttpStatusCode.valueOf(201)).build();
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
    public ResponseEntity<Integer> updateLocation(@RequestBody LocationDTO location) {
        locationService.updateLocation(transform.entityTake(location));
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/drop/{locationId}")
    public void dropLocation(@PathVariable int locationId) {
        locationService.deleteLocation(locationId);
    }
}
