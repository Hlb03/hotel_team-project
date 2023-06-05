package org.main.service.service.implementations;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.main.service.configuration.PasswordEncoder;
import org.main.service.entity.AccountStatus;
import org.main.service.entity.User;
import org.main.service.exceptions.IncorrectPasswordsException;
import org.main.service.repository.UserRepository;
import org.main.service.service.UserService;
import org.main.service.utilities.RandomStringGenerator;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private final BCryptPasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    private final RestTemplate restTemplate;
    private final RandomStringGenerator stringGenerator;
    private final BCryptPasswordEncoder encoder;

    @Override
    @Transactional
    public void addNewUser(User user, String confirmPassword) throws IncorrectPasswordsException {
        if (!user.getPassword().equals(confirmPassword))
            throw new IncorrectPasswordsException("USER PASSWORD IN THE FORM ARE DIFFERENT (" + user.getPassword() + " and " + confirmPassword + ")");

        user.setBalance(new BigDecimal(0));
        user.setStatus(AccountStatus.ACTIVE); // TODO: CHANGE IT TO NOT_ACTIVATED WHEN Anton WILL MAKE MODAL WINDOW FOR ACTIVATION CODE
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
        System.out.println("PASSWORD LENGTH: " + user.getPassword().length());
        System.out.println("$2a$12$FXYQ1MGWs.mtMT9BS7/POe61PCTw1AU0ZRgtqLz".length() + " LENGTH");
        restTemplate.postForLocation("http://MAILING/hotel-rent/mail/activate?" +
                                                    "receiver=" + user.getLogin() +
                                                    "&username=" + user.getLastName() +
                                                    "&activationCode=" + stringGenerator.generateRandomString(15),
                null);

    }

    @Override
    public User findUserById(int userId) {
        return userRepository.getReferenceById(userId);
    }

    @Override
    public Optional<User> findUserByLogin(String userLogin) {
        return userRepository.getUserByLogin(userLogin);
    }

    @Override
    public void updateUser(User user) {
        user.setId(23);
        user.setPassword(encoder.encode(user.getPassword()));
        user.setStatus(AccountStatus.ACTIVE);
        userRepository.save(user);
    }

    @Override
    public void deleteUserById(int userId) {
        userRepository.deleteById(userId);
    }
}
