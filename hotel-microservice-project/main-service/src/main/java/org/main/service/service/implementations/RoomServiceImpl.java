package org.main.service.service.implementations;

import lombok.AllArgsConstructor;
import org.main.service.entity.Room;
import org.main.service.repository.RoomRepository;
import org.main.service.service.RoomService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class RoomServiceImpl implements RoomService {

    private final RoomRepository roomRepository;

    @Override
    public void addNewRoom(Room room) {
        roomRepository.save(room);
    }

    @Override
    public Room findRoomById(int roomId) {
        return roomRepository.getReferenceById(roomId);
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
