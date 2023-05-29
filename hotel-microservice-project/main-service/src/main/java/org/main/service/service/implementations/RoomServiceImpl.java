package org.main.service.service.implementations;

import lombok.AllArgsConstructor;
import org.main.service.entity.Hotel;
import org.main.service.entity.Room;
import org.main.service.repository.RoomRepository;
import org.main.service.service.RoomService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.List;

@Service
@AllArgsConstructor
public class RoomServiceImpl implements RoomService {

    private final RoomRepository roomRepository;

    @Override
    public void addNewRoom(Room room) {
        room.setHotel(Hotel.builder().id(1).build());
        roomRepository.save(room);
    }

    @Override
    public Room findRoomById(int roomId) {
        return roomRepository.getReferenceById(roomId);
    }

    @Override
    public Page<Room> findAllRooms(int page) {
        Pageable pageable = PageRequest.of(page - 1, 3, Sort.Direction.DESC, "totalRate");
        return roomRepository.findAll(pageable);
    }

    @Override
    public List<Room> findAllByParams(Date startDate, Date endDate, BigDecimal startPrice, BigDecimal endPrice, int personAmount) {
        return roomRepository.findAllByParams(startDate, endDate, startPrice, endPrice, personAmount);
    }

    @Override
    public List<Room> findAllRoomsInHotel(int hotelId) {
        return roomRepository.getRoomsByHotelId(hotelId);
    }

    @Override
    public void updateRoom(Room room) {
        roomRepository.save(room);
    }

    @Override
    public void deleteRoom(int roomId) {
        roomRepository.deleteById(roomId);
    }
}
