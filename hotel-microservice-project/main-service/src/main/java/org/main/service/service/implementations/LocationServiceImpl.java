package org.main.service.service.implementations;

import lombok.AllArgsConstructor;
import org.main.service.entity.Location;
import org.main.service.repository.LocationRepository;
import org.main.service.service.LocationService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class LocationServiceImpl implements LocationService {

    private final LocationRepository locationRepository;

    @Override
    public void addLocation(Location location) {
        locationRepository.save(location);
    }

    @Override
    public Location findLocationById(int id) {
        return locationRepository.getReferenceById(id);
    }

    @Override
    public Location findLocationByName(String locationName) {
        return locationRepository.getLocationsByName(locationName);
    }

    @Override
    public List<Location> getAllLocations() {
        return locationRepository.findAll();
    }

    @Override
    public void updateLocation(Location location) {
        locationRepository.save(location);
    }

    @Override
    public void deleteLocation(int locationId) {
        locationRepository.deleteById(locationId);
    }
}
