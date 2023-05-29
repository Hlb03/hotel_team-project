package org.main.service.service.implementations;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.main.service.entity.User;
import org.main.service.exceptions.IncorrectPasswordsException;
import org.main.service.repository.UserRepository;
import org.main.service.service.UserService;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RestTemplate restTemplate;

    @Override
    @Transactional
    public void addNewUser(User user, String confirmPassword) throws IncorrectPasswordsException {
        if (!user.getPassword().equals(confirmPassword))
            throw new IncorrectPasswordsException("USER PASSWORD IN THE FORM ARE DIFFERENT (" + user.getPassword() + " and " + confirmPassword + ")");

        user.setBalance(new BigDecimal(0));
        userRepository.save(user);
        restTemplate.postForLocation("http://MAILING/hotel-rent/mail/activate/activationCode?receiver=gfietisov@gmail.com&username="
                + user.getLastName()
//                        + user.getLogin()
                , null);

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
