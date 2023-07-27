package org.main.service.service.implementations;

import lombok.AllArgsConstructor;
import org.main.service.entity.AccountStatus;
import org.main.service.entity.User;
import org.main.service.exceptions.InvalidActivationCodeException;
import org.main.service.repository.UserRepository;
import org.main.service.service.UserService;
import org.main.service.utilities.RandomStringGenerator;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder encoder;

    @Override
    public Optional<User> findUserByLogin(String userLogin) {
        return userRepository.getUserByLogin(userLogin);
    }

    @Override
    public void updateUser(User user, String username) {
        User u = userRepository.getUserByLogin(username).get();
        user.setPassword(encoder.encode(user.getPassword()));
        user.setStatus(AccountStatus.ACTIVE);
        userRepository.save(u);
    }

    @Override
    public void deleteUserById(int userId) {
        userRepository.deleteById(userId);
    }
}
