package org.mail.service.service.implementations;

import lombok.AllArgsConstructor;
import org.mail.service.entity.User;
import org.mail.service.repository.HotelUserRepository;
import org.mail.service.service.UserStorageService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class UserStorageServiceImpl implements UserStorageService {

    private final HotelUserRepository userRepository;

    @Override
    public void createNewUser(User user) {
        userRepository.insert(user);
    }

    @Override
    public Optional<User> searchUserByEmail(String email) {
        return userRepository.findUserByEmail(email);
    }
}
