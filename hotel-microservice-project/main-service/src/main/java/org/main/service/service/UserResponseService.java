package org.main.service.service;

import org.main.service.entity.UserResponse;

import java.util.List;

public interface UserResponseService {

    void addNewResponse(UserResponse userResponse, String username);

    List<UserResponse> findAllResponsesOnRoom(int roomId);

    double findAverageRoomRating(int roomId);

    void updateResponse(UserResponse userResponse);

    void deleteResponse(int responseId);
}
