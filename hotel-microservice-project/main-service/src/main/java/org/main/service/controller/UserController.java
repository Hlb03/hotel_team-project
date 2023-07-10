package org.main.service.controller;

import lombok.AllArgsConstructor;
import org.main.service.dto.UserDTO;
import org.main.service.service.UserService;
import org.main.service.transformation.UserTransform;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/users")
@AllArgsConstructor
public class UserController {

    private final UserService userService;
    private final UserTransform userTransform;

    @GetMapping
    public UserDTO getUserAuthorizedUserInfo(Principal principal) {
        return userTransform.dtoTaking(
                userService.findUserByLogin(principal.getName()).get()
        );
    }

    @PutMapping
    public ResponseEntity<Integer> updateUser(@RequestBody UserDTO userDTO, Principal principal) {
        System.out.println("USER CREDENTIALS TO UPDATE: " + userDTO);
        userService.updateUser(
                userTransform.entityTake(userDTO), principal.getName()
        );
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("drop/{userId}")
    public void deleteUser(@PathVariable int userId) {
        userService.deleteUserById(userId);
    }
}
