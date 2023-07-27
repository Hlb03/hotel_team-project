package org.main.service.exceptions;

public class LoginAlreadyRegisteredException extends Exception {

    public LoginAlreadyRegisteredException(String message) {
        super(message);
    }
}
