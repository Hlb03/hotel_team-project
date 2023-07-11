package org.main.service.service;

import org.main.service.entity.UserRooms;

import java.util.List;

public interface UserRoomsService {
    void addNewRoomToUser(UserRooms userRooms, String username);

    List<UserRooms> findAllUserRooms(String username);

    void updateUserRoom(UserRooms userRooms);

    void deleteUserRoomById(int userRoomsId);
}
