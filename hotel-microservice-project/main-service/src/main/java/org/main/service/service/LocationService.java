package org.main.service.service;

import org.main.service.entity.Location;

import java.util.List;

public interface LocationService {

    void addLocation(Location location);

    Location findLocationById(int id);

    Location findLocationByName(String locationName);

    List<Location> getAllLocations();

    void updateLocation(Location location);

    void deleteLocation(int locationId);
}
