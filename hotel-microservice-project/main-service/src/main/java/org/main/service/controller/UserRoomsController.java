package org.main.service.controller;

import lombok.AllArgsConstructor;
import org.main.service.dto.UserRoomsDTO;
import org.main.service.service.UserRoomsService;
import org.main.service.mapper.UserRoomsMapper;
import org.springframework.http.HttpStatus;
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
    private final UserRoomsMapper userRoomsMapper;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void rentNewRoom(@RequestBody UserRoomsDTO userRoomsDTO,
                                               @RequestParam int roomId, Principal principal) {
        userRoomsService.addNewRoomToUser(
                userRoomsMapper.entityTake(userRoomsDTO, roomId), principal.getName()
        );
    }

    @GetMapping
    public List<UserRoomsDTO> findAllUserRooms(Principal principal) {
        return userRoomsService.findAllUserRooms(principal.getName())
                .stream()
                .map(userRoomsMapper::dtoTaking)
                .collect(Collectors.toList());
    }

    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasAuthority('WRITE')")
    public void updateUserRooms(@RequestBody UserRoomsDTO userRoomsDTO,
                                                   @RequestParam int roomId) {
        userRoomsService.updateUserRoom(
                userRoomsMapper.entityTake(userRoomsDTO, roomId)
        );
    }

    @DeleteMapping("/drop/{userRoomId}")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasAuthority('DELETE')")
    public void deleteUsersRoomById(@PathVariable int userRoomId) {
        userRoomsService.deleteUserRoomById(userRoomId);
    }
}
