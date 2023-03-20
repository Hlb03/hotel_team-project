package org.main.service.service.implementations;

import lombok.AllArgsConstructor;
import org.main.service.entity.UserRooms;
import org.main.service.repository.UserRoomsRepository;
import org.main.service.service.UserRoomsService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class UserRoomsServiceImpl implements UserRoomsService {

    private final UserRoomsRepository userRoomsRepository;

    @Override
    public void addNewRoomToUser(UserRooms userRooms) {
        userRoomsRepository.save(userRooms);
    }

    @Override
    public List<UserRooms> findAllUserRooms(int userId) {
        return userRoomsRepository.getUserRoomsByUserId(userId);
    }

    @Override
    public void updateUserRoom(UserRooms userRooms) {
        userRoomsRepository.save(userRooms);
    }

    @Override
    public void deleteUserRoomById(int userRoomsId) {
        userRoomsRepository.deleteById(userRoomsId);
    }
}
