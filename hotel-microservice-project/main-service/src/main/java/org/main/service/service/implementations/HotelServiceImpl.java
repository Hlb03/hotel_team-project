package org.main.service.service.implementations;

import org.main.service.entity.Hotel;
import org.main.service.repository.HotelRepository;
import org.main.service.service.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HotelServiceImpl implements HotelService {

    private final HotelRepository hotelRepository;

    @Autowired
    public HotelServiceImpl(HotelRepository hotelRepository) {
        this.hotelRepository = hotelRepository;
    }

    @Override
    public void addNewHotel(Hotel hotel) {
        hotelRepository.save(hotel);
    }

    @Override
    public Hotel findHotelById(int id) {
        return hotelRepository.getReferenceById(id);
    }

    @Override
    public List<Hotel> findHotelsByLocationId(int id) {
        return hotelRepository.getHotelsByLocationId(id);
    }

    @Override
    public Hotel findHotelByName(String hotelName) {
        return hotelRepository.getHotelByName(hotelName);
    }

    @Override
    public void updateHotel(Hotel hotel) {
        hotelRepository.save(hotel);
    }

    @Override
    public void deleteHotelById(int hotelId) {
        hotelRepository.deleteById(hotelId);
    }
}
