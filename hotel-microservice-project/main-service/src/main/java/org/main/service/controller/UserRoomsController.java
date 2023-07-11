package org.main.service.controller;

import lombok.AllArgsConstructor;
import org.main.service.dto.UserRoomsDTO;
import org.main.service.service.UserRoomsService;
import org.main.service.transformation.UserRoomsTransform;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
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
    @ResponseStatus(HttpStatus.CREATED)
    public void rentNewRoom(@RequestBody UserRoomsDTO userRoomsDTO,
                                               @RequestParam int roomId, Principal principal) {
        userRoomsService.addNewRoomToUser(
                userRoomsTransform.entityTake(userRoomsDTO, roomId), principal.getName()
        );
    }

    // TODO: INFO ABOUT USER ROOMS SHOULD BE BASED ON SESSION BEAN THAT STORES AUTHORIZED USER INFO
    @GetMapping
    public List<UserRoomsDTO> findAllUserRooms(Principal principal) {
        return userRoomsService.findAllUserRooms(principal.getName())
                .stream()
                .map(userRoomsTransform::dtoTaking)
                .collect(Collectors.toList());
    }

    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasAuthority('WRITE')")
    public void updateUserRooms(@RequestBody UserRoomsDTO userRoomsDTO,
                                                   @RequestParam int roomId) {
        userRoomsService.updateUserRoom(
                userRoomsTransform.entityTake(userRoomsDTO, roomId)
        );
    }

    @DeleteMapping("/drop/{userRoomId}")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasAuthority('DELETE')")
    public void deleteUsersRoomById(@PathVariable int userRoomId) {
        userRoomsService.deleteUserRoomById(userRoomId);
    }
}
