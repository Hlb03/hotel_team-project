package org.main.service.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.main.service.dto.AuthenticationRequestDTO;
import org.main.service.dto.RegistrationRequestDTO;
import org.main.service.dto.TokenDTO;
import org.main.service.exceptions.AuthenticationException;
import org.main.service.exceptions.IncorrectPasswordsException;
import org.main.service.exceptions.LoginAlreadyRegisteredException;
import org.main.service.service.AuthenticationService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@Slf4j
@CrossOrigin("http://localhost:3000")
@RestController
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    @PostMapping("/registration")
    @ResponseStatus(HttpStatus.CREATED)
    public String addNewUser(@RequestBody RegistrationRequestDTO requestDTO) {
        log.info("\nUSER DATA FROM FRONT SERVICE IS: \n" + requestDTO);

        try {
            authenticationService.registerUser(requestDTO);
        } catch (IncorrectPasswordsException | LoginAlreadyRegisteredException e) {
            log.info(e.getMessage());
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }

        return String.format("Your account was successfully registered. " +
                "In a few minutes you will receive message on %s with activation code.",
                requestDTO.getMail());
    }

    @PostMapping("/auth")
    @ResponseStatus(HttpStatus.OK)
    public TokenDTO authenticateUser(@RequestBody AuthenticationRequestDTO requestDTO) {
        try {
            return new TokenDTO(authenticationService.authenticateUser(requestDTO));
        } catch (AuthenticationException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }
}
