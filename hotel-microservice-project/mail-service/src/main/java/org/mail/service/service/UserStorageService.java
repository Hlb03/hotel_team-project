package org.mail.service.service;

import org.mail.service.entity.User;

import java.util.Optional;

public interface UserStorageService {

    void createNewUser(User user);

    Optional<User> searchUserByEmail(String email);
}
