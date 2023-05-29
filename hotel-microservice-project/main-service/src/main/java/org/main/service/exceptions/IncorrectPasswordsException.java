package org.main.service.exceptions;

public class IncorrectPasswordsException extends Exception {

    public IncorrectPasswordsException() {
    }

    public IncorrectPasswordsException(String message) {
        super(message);
    }
}
