package org.main.service.controller;

import lombok.AllArgsConstructor;
import org.main.service.dto.UserDTO;
import org.main.service.service.UserService;
import org.main.service.transformation.UserTransform;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
@AllArgsConstructor
public class UserController {

    private final UserService userService;
    private final UserTransform userTransform;

    @PostMapping
    public ResponseEntity<Integer> addNewUser(@ModelAttribute UserDTO userDTO) {
        userService.addNewUser(
                userTransform.entityTake(userDTO)
        );
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/{userId}")
    public UserDTO findUserById(@PathVariable int userId) {
        return userTransform.dtoTaking(
                userService.findUserById(userId)
        );
    }

    @GetMapping("/login/{userLogin}")
    public UserDTO findUserByLogin(@PathVariable String userLogin) {
        return userTransform.dtoTaking(
                userService.findUserByLogin(userLogin)
        );
    }

    @PutMapping
    public ResponseEntity<Integer> updateUser(@ModelAttribute UserDTO userDTO) {
        userService.updateUser(
                userTransform.entityTake(userDTO)
        );
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("drop/{userId}")
    public void deleteUser(@PathVariable int userId) {
        userService.deleteUserById(userId);
    }
}
