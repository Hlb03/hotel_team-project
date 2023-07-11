package org.main.service.controller;

import lombok.RequiredArgsConstructor;
import org.main.service.dto.TokenDTO;
import org.main.service.dto.UserDTO;
import org.main.service.exceptions.IncorrectPasswordsException;
import org.main.service.service.AuthenticationService;
import org.main.service.mapper.UserMapper;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@CrossOrigin("http://localhost:3000")
@RestController
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService authenticationService;
    private final UserMapper userMapper;

    @PostMapping("/registration")
    @ResponseStatus(HttpStatus.CREATED)
    public void addNewUser(@RequestBody UserDTO userDTO, @RequestParam String confirmPassword) {
        System.out.println("USER DATA FROM FRONT SERVICE IS: \n" + userDTO);

        try {
            authenticationService.registerUser(
                    userMapper.entityTake(userDTO), confirmPassword
            );
        } catch (IncorrectPasswordsException e) {
            System.out.println("Failed to register user with bad credentials");
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @PostMapping("/auth")
    @ResponseStatus(HttpStatus.OK)
    public TokenDTO authenticateUser(@RequestBody UserDTO userDTO) {
        return new TokenDTO(authenticationService.authenticateUser(
                userMapper.entityTake(userDTO))
        );
    }
}
