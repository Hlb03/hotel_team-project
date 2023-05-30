package org.main.service.transformation;

import org.main.service.dto.UserResponseDTO;
import org.main.service.entity.Room;
import org.main.service.entity.User;
import org.main.service.entity.UserResponse;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Component
public class UserResponseTransform {

    public UserResponseDTO dtoTaking(UserResponse userResponse) {
        return UserResponseDTO.builder()
                .id(userResponse.getId())
                .comment(userResponse.getComment())
                .dateTimeResponse(userResponse.getDateTime())
                .rate(userResponse.getRate())
                .nickname(userResponse.getUser().getNickname())
                .build();
    }

    public UserResponse entityTake(UserResponseDTO userResponseDTO) {
        return UserResponse.builder()
                .id(userResponseDTO.getId())
                .comment(userResponseDTO.getComment())
                .rate(userResponseDTO.getRate())
                .dateTime(Timestamp.valueOf(LocalDateTime.now()))
                .user(
                        User.builder()
                                .id(userResponseDTO.getUserId())
                                .nickname(userResponseDTO.getNickname())
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
