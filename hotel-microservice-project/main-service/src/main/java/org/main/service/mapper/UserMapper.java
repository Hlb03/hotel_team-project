package org.main.service.mapper;

import org.main.service.dto.UserDTO;
import org.main.service.entity.Role;
import org.main.service.entity.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public UserDTO dtoTaking(User user) {
        return UserDTO.builder()
                .id(user.getId())
                .name(user.getFirstName())
                .surname(user.getLastName())
                .nickname(user.getNickname())
                .mail(user.getLogin())
                .balance(user.getBalance())
                .yearsOld(user.getAge())
                .phone(user.getPhoneNumber())
                .build();
    }

    public User entityTake(UserDTO userDTO) {
        return User.builder()
                .id(userDTO.getId())
                .role(Role.USER)
                .firstName(userDTO.getName())
                .lastName(userDTO.getSurname())
                .nickname(userDTO.getNickname())
                .login(userDTO.getMail())
                .password(userDTO.getPassword())
                .balance(userDTO.getBalance())
                .age(userDTO.getYearsOld())
                .phoneNumber(userDTO.getPhone())
                .build();
    }
}
