package org.main.service.exceptions;

public class InvalidActivationCodeException extends Exception {
    public InvalidActivationCodeException(String message) {
        super(message);
    }
}
