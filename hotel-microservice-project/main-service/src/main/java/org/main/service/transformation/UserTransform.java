package org.main.service.transformation;

import org.main.service.dto.UserDTO;
import org.main.service.entity.Role;
import org.main.service.entity.User;
import org.springframework.stereotype.Component;

@Component
public class UserTransform {

    public UserDTO dtoTaking(User user) {
        return UserDTO.builder()
                .id(user.getId())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .login(user.getLogin())
                .balance(user.getBalance())
                .age(user.getAge())
                .phoneNumber(user.getPhoneNumber())
                .build();
    }

    // TODO: think whether userDTO should have balance, because db has default value for insertions (but what about balance replenishment??)
    // TODO: samely to user password
    public User entityTake(UserDTO userDTO) {
        return User.builder()
                .id(userDTO.getId())
                .role(Role.USER)
                .firstName(userDTO.getFirstName())
                .lastName(userDTO.getLastName())
                .login(userDTO.getLogin())
                .password(userDTO.getPassword())
                .balance(userDTO.getBalance())
                .age(userDTO.getAge())
                .phoneNumber(userDTO.getPhoneNumber())
                .build();
    }
}
