package org.main.service.service.implementations;

import lombok.AllArgsConstructor;
import org.main.service.entity.UserResponse;
import org.main.service.repository.UserResponseRepository;
import org.main.service.service.UserResponseService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class UserResponseServiceImpl implements UserResponseService {

    private final UserResponseRepository userResponseRepository;

    @Override
    public void addNewResponse(UserResponse userResponse) {
        userResponseRepository.save(userResponse);
    }

    @Override
    public List<UserResponse> findAllResponsesOnRoom(int roomId) {
        return userResponseRepository.getAllByRoomId(roomId);
    }

    @Override
    public double findAverageRoomRating(int roomId) {
        return userResponseRepository.getAverageRatingForRoom(roomId);
    }

    @Override
    public void updateResponse(UserResponse userResponse) {
        userResponseRepository.save(userResponse);
    }

    @Override
    public void deleteResponse(int responseId) {
        userResponseRepository.deleteById(responseId);
    }
}
