package org.main.service.controller;

import lombok.AllArgsConstructor;
import org.main.service.dto.UserRoomsDTO;
import org.main.service.service.UserRoomsService;
import org.main.service.transformation.UserRoomsTransform;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/user-rooms")
@AllArgsConstructor
public class UserRoomsController {

    private final UserRoomsService userRoomsService;
    private final UserRoomsTransform userRoomsTransform;

    @PostMapping
    public ResponseEntity<Integer> rentNewRoom(@RequestBody UserRoomsDTO userRoomsDTO,
                                               @RequestParam int roomId) {
        userRoomsService.addNewRoomToUser(
                userRoomsTransform.entityTake(userRoomsDTO, roomId)
        );
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    // TODO: INFO ABOUT USER ROOMS SHOULD BE BASED ON SESSION BEAN THAT STORES AUTHORIZED USER INFO
    @GetMapping
    public List<UserRoomsDTO> findAllUserRooms() {
        return userRoomsService.findAllUserRooms(1)
                .stream()
                .map(userRoomsTransform::dtoTaking)
                .collect(Collectors.toList());
    }

    @PutMapping
    public ResponseEntity<Integer> updateUserRooms(@RequestBody UserRoomsDTO userRoomsDTO,
                                                   @RequestParam int roomId) {
        userRoomsService.updateUserRoom(
                userRoomsTransform.entityTake(userRoomsDTO, roomId)
        );
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/drop/{userRoomId}")
    public void deleteUsersRoomById(@PathVariable int userRoomId) {
        userRoomsService.deleteUserRoomById(userRoomId);
    }
}
