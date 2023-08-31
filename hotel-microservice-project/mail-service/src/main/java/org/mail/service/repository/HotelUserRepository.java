package org.mail.service.repository;

import org.mail.service.entity.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface HotelUserRepository extends MongoRepository<User, String> {

    Optional<User> findUserByEmail(String email);
}
