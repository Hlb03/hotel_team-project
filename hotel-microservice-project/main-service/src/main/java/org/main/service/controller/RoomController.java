package org.main.service.controller;

import lombok.AllArgsConstructor;
import org.main.service.dto.RoomDTO;
import org.main.service.service.RoomService;
import org.main.service.transformation.RoomTransform;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/rooms")
@AllArgsConstructor
public class RoomController {

    private final RoomService roomService;
    private final RoomTransform transform;

    @PostMapping
    public ResponseEntity<Integer> addNewRoom(@ModelAttribute RoomDTO room) {
        roomService.addNewRoom(transform.entityTake(room));
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/{roomId}")
    public RoomDTO findRoomById(@PathVariable int roomId) {
        return transform.dtoTaking(roomService.findRoomById(roomId));
    }

    @GetMapping
    public List<RoomDTO> getAllRooms(@RequestParam int page) {
        return roomService.findAllRooms(page)
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
    public ResponseEntity<Integer> updateRoomInfo(@ModelAttribute RoomDTO room) {
        roomService.updateRoom(transform.entityTake(room));
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/drop/{roomId}")
    public void deleteRoom(@PathVariable int roomId) {
        roomService.deleteRoom(roomId);
    }
}
