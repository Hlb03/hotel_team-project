package org.main.service.service;

import org.main.service.dto.AuthenticationRequestDTO;
import org.main.service.dto.RegistrationRequestDTO;
import org.main.service.exceptions.AuthenticationException;
import org.main.service.exceptions.IncorrectPasswordsException;
import org.main.service.exceptions.LoginAlreadyRegisteredException;

public interface AuthenticationService {

    void registerUser(RegistrationRequestDTO requestDTO) throws IncorrectPasswordsException, LoginAlreadyRegisteredException;

    String authenticateUser(AuthenticationRequestDTO requestDTO) throws AuthenticationException;
}
