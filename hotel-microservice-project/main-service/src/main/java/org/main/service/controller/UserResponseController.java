package org.main.service.controller;

import lombok.AllArgsConstructor;
import org.main.service.dto.UserResponseDTO;
import org.main.service.entity.UserResponse;
import org.main.service.service.UserResponseService;
import org.main.service.transformation.UserResponseTransform;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/response")
@AllArgsConstructor
public class UserResponseController {

    private final UserResponseService userResponseService;
    private final UserResponseTransform userResponseTransform;

    @PostMapping
    public ResponseEntity<Integer> createNewResponse(@RequestBody UserResponseDTO userResponse) {
        userResponseService.addNewResponse(
                userResponseTransform.entityTake(userResponse)
        );
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/room/{roomId}")
    public List<UserResponseDTO> findAllRoomResponses(@PathVariable int roomId) {
        return userResponseService.findAllResponsesOnRoom(roomId)
                .stream()
                .map(userResponseTransform::dtoTaking)
                .collect(Collectors.toList());
    }

    @PutMapping
    public ResponseEntity<Integer> updateResponse(@ModelAttribute UserResponseDTO response) {
        userResponseService.updateResponse(
                userResponseTransform.entityTake(response)
        );
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/drop/{responseId}")
    public void deleteResponse(@PathVariable int responseId) {
        userResponseService.deleteResponse(responseId);
    }
}
