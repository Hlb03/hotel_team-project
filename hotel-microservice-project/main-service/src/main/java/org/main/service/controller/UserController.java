package org.main.service.controller;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.main.service.dto.UserDTO;
import org.main.service.mapper.UserMapper;
import org.main.service.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@Slf4j
@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/users")
@AllArgsConstructor
public class UserController {

    private final UserService userService;
    private final UserMapper userMapper;

    @GetMapping
    public UserDTO getUserAuthorizedUserInfo(Principal principal) {
        return userMapper.dtoTaking(
                userService.findUserByLogin(principal.getName()).get()
        );
    }

    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    public void updateUser(@RequestBody UserDTO userDTO, Principal principal) {
        log.info("USER CREDENTIALS TO UPDATE: " + userDTO);
        userService.updateUser(
                userMapper.entityTake(userDTO), principal.getName()
        );
    }

    @DeleteMapping("drop/{userId}")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasAuthority('DELETE')")
    public void deleteUser(@PathVariable int userId) {
        userService.deleteUserById(userId);
    }
}
