package org.main.service.transformation;

import org.main.service.dto.UserResponseDTO;
import org.main.service.entity.Room;
import org.main.service.entity.User;
import org.main.service.entity.UserResponse;
import org.springframework.stereotype.Component;

@Component
public class UserResponseTransform {

    public UserResponseDTO dtoTaking(UserResponse userResponse) {
        return UserResponseDTO.builder()
                .id(userResponse.getId())
                .comment(userResponse.getComment())
                .dateTimeResponse(userResponse.getDateTime())
                .rate(userResponse.getRate())
                .userLogin(userResponse.getUser().getLogin())
                .build();
    }

    public UserResponse entityTake(UserResponseDTO userResponseDTO) {
        return UserResponse.builder()
                .id(userResponseDTO.getId())
                .comment(userResponseDTO.getComment())
                .dateTime(userResponseDTO.getDateTimeResponse())
                .rate(userResponseDTO.getRate())
                .user(
                        User.builder()
                                .id(userResponseDTO.getId())
                                .login(userResponseDTO.getUserLogin())
                                .build()
                )
                .room(
                        Room.builder()
                                .id(userResponseDTO.getRoom())
                                .build()
                )
                .build();
    }
}
