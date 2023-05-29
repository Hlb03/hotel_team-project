package org.main.service.service;

import org.main.service.entity.Room;
import org.springframework.data.domain.Page;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.List;

public interface RoomService {

    void addNewRoom(Room room);

    Room findRoomById(int roomId);

    Page<Room> findAllRooms(int page);

    List<Room> findAllByParams(Date startDate, Date endDate, BigDecimal startPrice, BigDecimal endPrice, int personAmount);

    List<Room> findAllRoomsInHotel(int hotelId);

    void updateRoom(Room room);

    void deleteRoom(int roomId);
}
