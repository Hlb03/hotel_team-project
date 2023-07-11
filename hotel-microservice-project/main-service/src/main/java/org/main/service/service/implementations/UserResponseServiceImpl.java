package org.main.service.service.implementations;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.main.service.dto.RoomDTO;
import org.main.service.entity.Room;
import org.main.service.entity.User;
import org.main.service.entity.UserResponse;
import org.main.service.repository.UserResponseRepository;
import org.main.service.service.UserResponseService;
import org.main.service.service.UserService;
import org.main.service.transformation.RoomTransform;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class UserResponseServiceImpl implements UserResponseService {

    private final UserResponseRepository userResponseRepository;
    private final RoomServiceImpl roomService;
    private final RoomTransform roomTransform;
    private final UserService userService;

    @Override
    @Transactional
    public void addNewResponse(UserResponse userResponse, String username) {
        userResponse.setUser(
                userService.findUserByLogin(username).get()
        );
        userResponse.setRate(4.2);
        System.out.println("USER RESPONSE: " + userResponse);

        userResponseRepository.save(userResponse);

        RoomDTO roomToUpdate = roomTransform.dtoTaking(
                roomService.findRoomById(userResponse.getRoom().getId()));

        roomService.updateRoom(roomTransform.entityTake(
                RoomDTO.builder()
                        .id(roomToUpdate.getId())
                        .price(roomToUpdate.getPrice())
                        .shortDescription(roomToUpdate.getShortDescription())
                        .longDescription(roomToUpdate.getLongDescription())
                        .photoRoom(roomToUpdate.getPhotoRoom())
                        .hotel(roomToUpdate.getHotel())
                        .rate(findAverageRoomRating(userResponse.getRoom().getId()))
                        .build()
        ));
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
