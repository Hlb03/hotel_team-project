package org.main.service.service;

import org.main.service.entity.User;
import org.main.service.exceptions.IncorrectPasswordsException;

public interface UserService {

    void addNewUser(User user, String confirmPassword) throws IncorrectPasswordsException;

    User findUserById(int userId);

    User findUserByLogin(String userLogin);

    void updateUser(User user);

    void deleteUserById(int userId);
}
