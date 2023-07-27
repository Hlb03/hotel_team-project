package org.main.service.service;

import org.main.service.exceptions.InvalidActivationCodeException;

public interface AccountStatusService {

    void activateUserAccount(String activationCode) throws InvalidActivationCodeException;

    void blockUserAccountById(int id);

    void unblockAccountById(int id);
}
