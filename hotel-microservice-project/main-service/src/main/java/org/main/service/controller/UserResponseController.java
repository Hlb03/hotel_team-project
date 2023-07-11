package org.main.service.controller;

import lombok.AllArgsConstructor;
import org.main.service.dto.UserResponseDTO;
import org.main.service.entity.UserResponse;
import org.main.service.service.UserResponseService;
import org.main.service.transformation.UserResponseTransform;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/response")
@AllArgsConstructor
public class UserResponseController {

    private final UserResponseService userResponseService;
    private final UserResponseTransform userResponseTransform;


    // TODO: FRONT SIDE SHOULD ALSO SEND A REAL RATE
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createNewResponse(@RequestBody UserResponseDTO userResponse, Principal principal) {
        userResponseService.addNewResponse(
                userResponseTransform.entityTake(userResponse), principal.getName()
        );
    }

    @GetMapping("/room/{roomId}")
    public List<UserResponseDTO> findAllRoomResponses(@PathVariable int roomId) {
        return userResponseService.findAllResponsesOnRoom(roomId)
                .stream()
                .map(userResponseTransform::dtoTaking)
                .collect(Collectors.toList());
    }

    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasAuthority('WRITE')")
    public void updateResponse(@RequestBody UserResponseDTO response) {
        userResponseService.updateResponse(
                userResponseTransform.entityTake(response)
        );
    }

    @DeleteMapping("/drop/{responseId}")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasAuthority('DELETE')")
    public void deleteResponse(@PathVariable int responseId) {
        userResponseService.deleteResponse(responseId);
    }
}
