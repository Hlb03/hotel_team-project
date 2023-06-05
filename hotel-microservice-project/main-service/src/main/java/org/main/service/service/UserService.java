package org.main.service.service;

import org.main.service.entity.User;
import org.main.service.exceptions.IncorrectPasswordsException;

import java.util.Optional;

public interface UserService {

    void addNewUser(User user, String confirmPassword) throws IncorrectPasswordsException;

    User findUserById(int userId);

    Optional<User> findUserByLogin(String userLogin);

    void updateUser(User user);

    void deleteUserById(int userId);
}
