package org.main.service.service;

import org.main.service.entity.Hotel;

import java.util.List;

public interface HotelService {

    void addNewHotel(Hotel hotel);

    Hotel findHotelById(int id);

    List<Hotel> findHotelsByLocationId(int id);

    Hotel findHotelByName(String hotelName);

    void updateHotel(Hotel hotel);

    void deleteHotelById(int hotelId);
}
