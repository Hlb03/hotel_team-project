package org.main.service.service.implementations;

import lombok.AllArgsConstructor;
import org.main.service.entity.User;
import org.main.service.repository.UserRepository;
import org.main.service.service.UserService;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public void addNewUser(User user) {
        userRepository.save(user);
    }

    @Override
    public User findUserById(int userId) {
        return userRepository.getReferenceById(userId);
    }

    @Override
    public User findUserByLogin(String userLogin) {
        return userRepository.getUserByLogin(userLogin);
    }

    @Override
    public void updateUser(User user) {
        userRepository.save(user);
    }

    @Override
    public void deleteUserById(int userId) {
        userRepository.deleteById(userId);
    }
}
