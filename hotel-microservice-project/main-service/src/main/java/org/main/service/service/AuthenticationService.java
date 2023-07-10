package org.main.service.service;

import org.main.service.entity.User;
import org.main.service.exceptions.IncorrectPasswordsException;

public interface AuthenticationService {

    public void registerUser(User user, String confirmPassword) throws IncorrectPasswordsException;

    public String authenticateUser(User user);
}
