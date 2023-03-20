package org.main.service.service;

import org.main.service.entity.User;

public interface UserService {

    void addNewUser(User user);

    User findUserById(int userId);

    User findUserByLogin(String userLogin);

    void updateUser(User user);

    void deleteUserById(int userId);
}
