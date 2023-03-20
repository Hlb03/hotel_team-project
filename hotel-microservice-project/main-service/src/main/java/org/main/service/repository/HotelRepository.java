package org.main.service.repository;

import org.main.service.entity.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HotelRepository extends JpaRepository<Hotel, Integer> {
    List<Hotel> getHotelsByLocationId(int locationId);
    Hotel getHotelByName(String hotelName);
}
