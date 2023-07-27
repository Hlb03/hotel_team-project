package org.main.service.service;

import org.main.service.entity.User;
import org.main.service.exceptions.IncorrectPasswordsException;
import org.main.service.exceptions.InvalidActivationCodeException;

import java.util.Optional;

public interface UserService {

    Optional<User> findUserByLogin(String userLogin);

    void updateUser(User user, String username);

    void deleteUserById(int userId);
}
