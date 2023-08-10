package org.main.service.repository_tests;

import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.main.service.entity.*;
import org.main.service.repository.RoomRepository;
import org.main.service.repository.UserRepository;
import org.main.service.repository.UserResponseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


@DataJpaTest
public class UserResponseRepositoryTest {

    @Autowired
    UserResponseRepository userResponseRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    RoomRepository roomRepository;

    @BeforeEach
    void initUsersAndRooms() {
        userRepository.save(
                        User
                                .builder()
                                .firstName("test_user")
                                .lastName("lastname")
                                .nickname("nickname")
                                .login("test@gmail.com")
                                .password("test_password")
                                .role(Role.USER)
                                .status(AccountStatus.ACTIVE)
                                .build()
        );

        roomRepository.save(
                        Room
                                .builder()
                                .price(BigDecimal.valueOf(450.00))
                                .briefDescription("brief_description")
                                .description("long_description")
                                .imageReference("...")
                                .personAmount(3)
                                .build()
        );
    }

    @Test
    void checkAverageRatingCalculation() {
        int roomIdAndUserId = 1;
        Double[] rates = {2.2, 3.3, 4.1, 5.0, 3.9};

        insertDataIntoUserResponse(roomIdAndUserId, rates);

        assertNotNull(roomRepository.findById(roomIdAndUserId));
        assertNotNull(userRepository.findById(roomIdAndUserId));
        assertEquals(rates.length, userResponseRepository.count());
        assertNotNull(userResponseRepository.getAverageRatingForRoom(roomIdAndUserId));
        assertEquals(3.7, userResponseRepository.getAverageRatingForRoom(roomIdAndUserId));
    }

    @Test
    void incorrectRatingCalculation() {
        int roomAndUserId = 2;
        Double[] rates = {4.0, 4.5, 5.0};

        insertDataIntoUserResponse(roomAndUserId, rates);

        assertNotNull(roomRepository.findById(roomAndUserId));
        assertNotNull(userRepository.findById(roomAndUserId));
        assertEquals(rates.length, userResponseRepository.count());
        assertNotEquals(4.6, userResponseRepository.getAverageRatingForRoom(roomAndUserId));
    }

    @Test
    void checkRatingWithoutResponses_ShouldBeNull() {
        List<Room> rooms = roomRepository.findAll();
        for (Room r : rooms)
            assertNull(r.getTotalRate());
    }

    private void insertDataIntoUserResponse(int roomIdAndUserId, Double[] rates) {
        List<UserResponse> responses = new LinkedList<>();
        for (double d : rates) {
            responses.add(
                    UserResponse
                            .builder()
                            .comment("Nice room")
                            .rate(d)
                            .user(
                                    User.builder()
                                            .id(roomIdAndUserId)
                                            .build()
                            )
                            .room(
                                    Room.builder()
                                            .id(roomIdAndUserId)
                                            .build()
                            )
                            .build()
            );
        }
        userResponseRepository.saveAll(responses);
    }
}
