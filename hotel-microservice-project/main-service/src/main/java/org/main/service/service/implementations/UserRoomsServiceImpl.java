package org.main.service.service.implementations;

import lombok.AllArgsConstructor;
import org.main.service.entity.UserRooms;
import org.main.service.repository.UserRoomsRepository;
import org.main.service.service.UserRoomsService;
import org.main.service.service.UserService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class UserRoomsServiceImpl implements UserRoomsService {

    private final UserRoomsRepository userRoomsRepository;
    private final UserService userService;

    @Override
    public void addNewRoomToUser(UserRooms userRooms, String username) {
        userRooms.setUser(
                userService.findUserByLogin(username).get()
        );
        userRoomsRepository.save(userRooms);
    }

    @Override
    public List<UserRooms> findAllUserRooms(String username) {
        return userRoomsRepository.getUserRoomsByUserLogin(username);
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
