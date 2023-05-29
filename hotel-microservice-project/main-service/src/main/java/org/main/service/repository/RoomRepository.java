package org.main.service.repository;

import org.main.service.entity.Room;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.List;

@Repository
public interface RoomRepository extends JpaRepository<Room, Integer> {
    List<Room> getRoomsByHotelId(int hotelId);

    Page<Room> findAll(Pageable pageable);

    @Query("SELECT r FROM Room r LEFT JOIN UserRooms ur ON r.id = ur.room.id " +
                        "WHERE ((ur.startRent > :startRent AND ur.endRent < :endRent) OR (ur.startRent < :startRent AND ur.startRent < :endRent)) " +
                                "AND r.price BETWEEN :startPrice AND :endPrice AND r.personAmount >= :personAmount")
    List<Room> findAllByParams(@Param("startRent") Date startDate, @Param("endRent") Date endDate,
                               @Param("startPrice") BigDecimal minPrice, @Param("endPrice") BigDecimal maxPrice,
                               @Param("personAmount") int personAmount);
}