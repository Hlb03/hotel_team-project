package org.main.service.controller;

import lombok.AllArgsConstructor;
import org.main.service.dto.UserResponseDTO;
import org.main.service.service.UserResponseService;
import org.main.service.mapper.UserResponseMapper;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/response")
@AllArgsConstructor
public class UserResponseController {

    private final UserResponseService userResponseService;
    private final UserResponseMapper userResponseMapper;


    // TODO: FRONT SIDE SHOULD ALSO SEND A REAL RATE
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createNewResponse(@RequestBody UserResponseDTO userResponse, Principal principal) {
        userResponseService.addNewResponse(
                userResponseMapper.entityTake(userResponse), principal.getName()
        );
    }

    @GetMapping("/room/{roomId}")
    public List<UserResponseDTO> findAllRoomResponses(@PathVariable int roomId) {
        return userResponseService.findAllResponsesOnRoom(roomId)
                .stream()
                .map(userResponseMapper::dtoTaking)
                .collect(Collectors.toList());
    }

    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasAuthority('WRITE')")
    public void updateResponse(@RequestBody UserResponseDTO response) {
        userResponseService.updateResponse(
                userResponseMapper.entityTake(response)
        );
    }

    @DeleteMapping("/drop/{responseId}")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasAuthority('DELETE')")
    public void deleteResponse(@PathVariable int responseId) {
        userResponseService.deleteResponse(responseId);
    }
}
