package org.main.service.repository;

import org.main.service.entity.Location;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LocationRepository extends JpaRepository<Location, Integer> {
    Location getLocationsByName(String locationName);

    List<Location> findAll();
}
