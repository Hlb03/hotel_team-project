package org.main.service.controller;

import lombok.AllArgsConstructor;
import org.main.service.dto.RoomDTO;
import org.main.service.service.RoomService;
import org.main.service.transformation.RoomTransform;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.List;
import java.util.stream.Collectors;


@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/rooms")
@AllArgsConstructor
public class RoomController {

    private final RoomService roomService;
    private final RoomTransform transform;


    @PostMapping
    public ResponseEntity<Integer> addNewRoom(@RequestBody RoomDTO room, @RequestParam String selectedCity) {
        System.out.println("NEW ROOM INFO " + room + " and is located in " + selectedCity);
        roomService.addNewRoom(transform.entityTake(room));
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/{roomId}")
    public RoomDTO findRoomById(@PathVariable int roomId) {
        return transform.dtoTaking(roomService.findRoomById(roomId));
    }

    @GetMapping
    public List<RoomDTO> getAllRooms(@RequestParam(name = "page", defaultValue = "1") int page) {
        return roomService.findAllRooms(page)
                .stream()
                .map(transform::dtoTaking)
                .toList();
    }

    @GetMapping("/search")
    public List<RoomDTO> findAllSuitableRooms(@RequestParam BigDecimal minPrice, @RequestParam BigDecimal maxPrice,
                                              @RequestParam Date dateStart, @RequestParam Date dateEnd,
                                              @RequestParam int amountOfPerson) {

        System.out.println("SEARCHING PARAMS ARE: " + dateStart + " " + dateEnd);
        return roomService.findAllByParams(dateStart, dateEnd, minPrice, maxPrice, amountOfPerson)
                .stream()
                .map(transform::dtoTaking)
                .toList();
    }

    @GetMapping("/hotel/{hotelId}")
    public List<RoomDTO> findAllHotelRooms(@PathVariable int hotelId) {
        return roomService.findAllRoomsInHotel(hotelId)
                .stream()
                .map(transform::dtoTaking)
                .collect(Collectors.toList());
    }

    @PutMapping
    public ResponseEntity<Integer> updateRoomInfo(@RequestBody RoomDTO room) {
        roomService.updateRoom(transform.entityTake(room));
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/drop/{roomId}")
    public void deleteRoom(@PathVariable int roomId) {
        roomService.deleteRoom(roomId);
    }
}
