package org.main.service.service;

import org.main.service.entity.UserRooms;

import java.util.List;

public interface UserRoomsService {
    void addNewRoomToUser(UserRooms userRooms);

    List<UserRooms> findAllUserRooms(int userId);

    void updateUserRoom(UserRooms userRooms);

    void deleteUserRoomById(int userRoomsId);
}
