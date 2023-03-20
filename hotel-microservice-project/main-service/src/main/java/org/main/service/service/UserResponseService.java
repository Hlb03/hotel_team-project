package org.main.service.service;

import org.main.service.entity.UserResponse;

import java.util.List;

public interface UserResponseService {

    void addNewResponse(UserResponse userResponse);

    List<UserResponse> findAllResponsesOnRoom(int roomId);

    byte findAverageRoomRating(int roomId);

    void updateResponse(UserResponse userResponse);

    void deleteResponse(int responseId);
}
