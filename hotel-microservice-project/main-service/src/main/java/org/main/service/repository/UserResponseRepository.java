package org.main.service.repository;

import org.main.service.entity.UserResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserResponseRepository extends JpaRepository<UserResponse, Integer> {

    List<UserResponse> getAllByRoomId(int roomId);

    @Query("SELECT AVG(ur.rate) FROM UserResponse ur WHERE ur.id = :id")
    byte getAverageRatingForRoom(@Param("id") int roomId);
}
