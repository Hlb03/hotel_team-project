package org.main.service.service;

import org.main.service.entity.User;
import org.main.service.exceptions.IncorrectPasswordsException;

import java.util.Optional;

public interface UserService {

    Optional<User> findUserByLogin(String userLogin);

    void updateUser(User user, String username);

    void deleteUserById(int userId);
}
