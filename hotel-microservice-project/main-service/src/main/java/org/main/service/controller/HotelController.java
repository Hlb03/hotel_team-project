package org.main.service.controller;

import lombok.AllArgsConstructor;
import org.main.service.dto.HotelDTO;
import org.main.service.entity.Hotel;
import org.main.service.service.HotelService;
import org.main.service.mapper.HotelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/hotels")
@AllArgsConstructor
@PreAuthorize("hasAuthority('WRITE')")
public class HotelController {

    private final HotelService hotelService;
    private final HotelMapper hotelMapper;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createNewHotel(@RequestBody HotelDTO hotelDTO) {
        System.out.println("HOTEL IS: " + hotelDTO);
        Hotel hotel = hotelMapper.entityTake(hotelDTO);
        hotelService.addNewHotel(hotel);
    }

    @GetMapping("/{hotelId}")
    public HotelDTO getHotelInfo(@PathVariable int hotelId) {
        Hotel hotel = hotelService.findHotelById(hotelId);
        HotelDTO hotelDTO = hotelMapper.dtoTaking(hotel);
        System.out.println("HOTEL IS: " + hotel);
        return hotelDTO;
    }

    @GetMapping("/name/{hotelName}")
    public HotelDTO getHotelInfo(@PathVariable String hotelName) {
        Hotel hotel = hotelService.findHotelByName(hotelName);
        return hotelMapper.dtoTaking(hotel);
    }

    @GetMapping("/location/{locationId}")
    public List<HotelDTO> findHotelsOnLocation(@PathVariable int locationId) {
        return hotelService.findHotelsByLocationId(locationId)
                .stream()
                .map(hotelMapper::dtoTaking)
                .collect(Collectors.toList());
    }

    @GetMapping
    public List<HotelDTO> getAllHotels() {
        return hotelService.findAllHotels()
                .stream()
                .map(hotelMapper::dtoTaking)
                .toList();
    }

    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    public void updateHotelInfo(@RequestBody HotelDTO hotelDTO) {
        hotelService.updateHotel(hotelMapper.entityTake(hotelDTO));
    }

    @DeleteMapping("/drop/{hotelId}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteHotel(@PathVariable int hotelId) {
        hotelService.deleteHotelById(hotelId);
    }
}
