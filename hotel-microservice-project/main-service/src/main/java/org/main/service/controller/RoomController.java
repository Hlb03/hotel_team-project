package org.main.service.controller;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.main.service.dto.RoomDTO;
import org.main.service.service.RoomService;
import org.main.service.mapper.RoomMapper;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.List;
import java.util.stream.Collectors;


@Slf4j
@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/rooms")
@AllArgsConstructor
public class RoomController {

    private final RoomService roomService;
    private final RoomMapper roomMapper;


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasAuthority('WRITE')")
    public void addNewRoom(@RequestBody RoomDTO room) {
        log.info("NEW ROOM INFO " + room);
        roomService.addNewRoom(roomMapper.entityTake(room));
    }

    @GetMapping("/info/{roomId}")
    public RoomDTO findRoomById(@PathVariable int roomId) {
        return roomMapper.dtoTaking(roomService.findRoomById(roomId));
    }

    @GetMapping
    public List<RoomDTO> getAllRooms(@RequestParam(name = "page", defaultValue = "1") int page,
                                     @RequestParam int size) {
        return roomService.findAllRooms(page, size)
                .stream()
                .map(roomMapper::dtoTaking)
                .toList();
    }

    // TODO: FIX DAY ISSUE (RECEIVES DESIRED DATE -1 DAY FROM FRONT SERVICE
    // TODO: ADD LOCATION (HOTEL) AS A SEARCH PARAM (NEED TO SEND DATA TO ANTON TO VIEW THE WHOLE AVAILABLE LIST IN DATABASE)
    @GetMapping("/search")
    public List<RoomDTO> findAllSuitableRooms(@RequestParam BigDecimal minPrice, @RequestParam BigDecimal maxPrice,
                                              @RequestParam Date dateStart, @RequestParam Date dateEnd,
                                              @RequestParam int amountOfPerson) {
        return roomService.findAllByParams(dateStart, dateEnd, minPrice, maxPrice, amountOfPerson)
                .stream()
                .map(roomMapper::dtoTaking)
                .toList();
    }

    @GetMapping("/hotel/{hotelId}")
    public List<RoomDTO> findAllHotelRooms(@PathVariable int hotelId) {
        return roomService.findAllRoomsInHotel(hotelId)
                .stream()
                .map(roomMapper::dtoTaking)
                .collect(Collectors.toList());
    }

    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasAuthority('WRITE')")
    public void updateRoomInfo(@RequestBody RoomDTO room) {
        roomService.updateRoom(roomMapper.entityTake(room));
    }

    @DeleteMapping("/drop/{roomId}")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasAuthority('DELETE')")
    public void deleteRoom(@PathVariable int roomId) {
        roomService.deleteRoom(roomId);
    }
}
