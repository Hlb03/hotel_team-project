package org.main.service.service.implementations;

import lombok.AllArgsConstructor;
import org.main.service.entity.AccountStatus;
import org.main.service.entity.User;
import org.main.service.exceptions.InvalidActivationCodeException;
import org.main.service.repository.UserRepository;
import org.main.service.service.AccountStatusService;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AccountStatusServiceImpl implements AccountStatusService {

    private final UserRepository userRepository;

    @Override
    public void activateUserAccount(String activationCode) throws InvalidActivationCodeException {
        User user = userRepository.findUserByActivationCode(activationCode)
                .orElseThrow(() -> new InvalidActivationCodeException(
                        String.format("Incorrect activation code (%s). Please, check it and try again.", activationCode)
                ));
        user.setActivationCode(null);
        user.setStatus(AccountStatus.ACTIVE);
        System.out.println("ACTIVATING USER ACCOUNT WITH LOGIN " + user.getLogin());
        userRepository.save(user);
    }

    @Override
    public void blockUserAccountById(int id) {
        manipulateUserAccountStatus(id, AccountStatus.BLOCKED);
    }

    @Override
    public void unblockAccountById(int id) {
        manipulateUserAccountStatus(id, AccountStatus.ACTIVE);
    }

    private void manipulateUserAccountStatus(int userId, AccountStatus status) {
        User user = userRepository.getReferenceById(userId);
        user.setStatus(status);
        userRepository.save(user);
    }
}
