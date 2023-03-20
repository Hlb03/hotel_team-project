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

@RestController
@RequestMapping("/user-rooms")
@AllArgsConstructor
public class UserRoomsController {

    private final UserRoomsService userRoomsService;
    private final UserRoomsTransform userRoomsTransform;

    @PostMapping
    public ResponseEntity<Integer> rentNewRoom(@ModelAttribute UserRoomsDTO userRoomsDTO) {
        userRoomsService.addNewRoomToUser(
                userRoomsTransform.entityTake(userRoomsDTO)
        );
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/{userId}")
    public List<UserRoomsDTO> findAllUserRooms(@PathVariable int userId) {
        return userRoomsService.findAllUserRooms(userId)
                .stream()
                .map(userRoomsTransform::dtoTaking)
                .collect(Collectors.toList());
    }

    @PutMapping
    public ResponseEntity<Integer> updateUserRooms(@ModelAttribute UserRoomsDTO userRoomsDTO) {
        userRoomsService.updateUserRoom(
                userRoomsTransform.entityTake(userRoomsDTO)
        );
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/drop/{userRoomId}")
    public void deleteUsersRoomById(@PathVariable int userRoomId) {
        userRoomsService.deleteUserRoomById(userRoomId);
    }
}
