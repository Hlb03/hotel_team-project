package org.main.service.repository;

import org.main.service.entity.UserRooms;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRoomsRepository extends JpaRepository<UserRooms, Integer> {
    List<UserRooms> getUserRoomsByUserId(int userId);
}
