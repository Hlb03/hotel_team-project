package org.main.service.repository;

import org.main.service.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> getUserByLogin(String login);

    Optional<User> findUserByActivationCode(String activationCode);
}
