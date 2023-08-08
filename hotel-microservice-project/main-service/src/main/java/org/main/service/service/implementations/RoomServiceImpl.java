package org.main.service.service.implementations;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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

@Slf4j
@Service
@AllArgsConstructor
public class RoomServiceImpl implements RoomService {

    private final RoomRepository roomRepository;

    @Override
    public void addNewRoom(Room room) {
        room.setHotel(Hotel.builder().id(1).build());
        roomRepository.save(room);
        log.info("New room was created with params: " + room);
    }

    @Override
    public Room findRoomById(int roomId) {
        return roomRepository.getReferenceById(roomId);
    }

    @Override
    public Page<Room> findAllRooms(int page, int dataAmount) {
        Pageable pageable = PageRequest.of(page - 1, dataAmount, Sort.Direction.DESC, "totalRate");
        return roomRepository.findAll(pageable);
    }

    // TODO: FIX DAY ISSUE (RECEIVES DESIRED DATE -1 DAY FROM FRONT SERVICE
    // TODO: ADD LOCATION (HOTEL) AS A SEARCH PARAM (NEED TO SEND DATA TO ANTON TO VIEW THE WHOLE AVAILABLE LIST IN DATABASE)
    @Override
    public List<Room> findAllByParams(Date startDate, Date endDate, BigDecimal startPrice, BigDecimal endPrice, int personAmount) {
        Date start  = Date.valueOf(startDate.toLocalDate().plusDays(1));
        Date end = Date.valueOf(endDate.toLocalDate().plusDays(1));
        log.info("SEARCHING PARAMS ARE: " + (startDate.toLocalDate().plusDays(1)) + " " + (endDate.toLocalDate().plusDays(1)));

        return roomRepository.findAllByParams(start, end, startPrice, endPrice, personAmount);
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
