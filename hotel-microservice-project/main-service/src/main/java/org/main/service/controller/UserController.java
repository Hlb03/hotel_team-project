package org.main.service.controller;

import lombok.AllArgsConstructor;
import org.main.service.dto.UserDTO;
import org.main.service.exceptions.IncorrectPasswordsException;
import org.main.service.service.UserService;
import org.main.service.transformation.UserTransform;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

//@CrossOrigin(origins = "http://localhost:3000/**")
@CrossOrigin
@RestController
@RequestMapping("/users")
@AllArgsConstructor
public class UserController {

    private final UserService userService;
    private final UserTransform userTransform;

    @PostMapping
    public ResponseEntity<Integer> addNewUser(@RequestBody UserDTO userDTO, @RequestParam String confirmPassword) {
        System.out.println("USER DATA FROM FRONT SERVICE IS: \n" + userDTO);
        try {
            userService.addNewUser(
                    userTransform.entityTake(userDTO), confirmPassword
            );
        } catch (IncorrectPasswordsException e) {
            System.out.println("PASSWORDS ARE NOT THE SAME WHILE REGISTRATING A NEW USER");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    //TODO: THIS REQUEST SHOULD BE WITH USER CREDENTIALS WHICH I SHOULD TAKE FROM MY BACKEND
    @GetMapping
    public UserDTO findUserById() {
        return userTransform.dtoTaking(
                userService.findUserById(23)
        );
    }

    @GetMapping("/login/{userLogin}")
    public UserDTO findUserByLogin(@PathVariable String userLogin) {
        return userTransform.dtoTaking(
                userService.findUserByLogin(userLogin).get() // TODO: should add Optional in data convert
        );
    }

    @PutMapping
    public ResponseEntity<Integer> updateUser(@RequestBody UserDTO userDTO) {
        System.out.println("USER CREDENTIALS TO UPDATE: " + userDTO);
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
