package org.main.service.service.implementations;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.main.service.configuration.UserDetailsServiceImpl;
import org.main.service.entity.AccountStatus;
import org.main.service.entity.User;
import org.main.service.exceptions.IncorrectPasswordsException;
import org.main.service.repository.UserRepository;
import org.main.service.service.AuthenticationService;
import org.main.service.utilities.JsonTokenUtil;
import org.main.service.utilities.RandomStringGenerator;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {

    private final BCryptPasswordEncoder encoder;
    private final RestTemplate restTemplate;
    private final RandomStringGenerator stringGenerator;
    private final AuthenticationManager manager;
    private final JsonTokenUtil jsonToken;
    private final UserRepository userRepository;
    private final UserDetailsServiceImpl userDetailsService;

    @Override
    @Transactional
    public void registerUser(User user, String confirmPassword) throws IncorrectPasswordsException {
        if (!user.getPassword().equals(confirmPassword)) throw new IncorrectPasswordsException(
                "USER PASSWORD IN THE FORM ARE DIFFERENT (" + user.getPassword() + " and " + confirmPassword + ")"
        );

        user.setBalance(new BigDecimal(0));
        user.setStatus(AccountStatus.ACTIVE); // TODO: CHANGE IT TO NOT_ACTIVATED WHEN Anton WILL MAKE MODAL WINDOW FOR ACTIVATION CODE
        user.setPassword(encoder.encode(user.getPassword()));
        userRepository.save(user);
        restTemplate.postForLocation("http://MAILING/hotel-rent/mail/activate?" +
                        "receiver=" + user.getLogin() +
                        "&username=" + user.getLastName() +
                        "&activationCode=" + stringGenerator.generateRandomString(15), // TODO: SAVE ACTIVATION CODE IN DB (& ACTIVATE USER IF REQUESTED)
                null);

    }

    @Override
    public String authenticateUser(User user) {
        System.out.println("USER CREDENTIALS: " + user);
        try {
            manager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            user.getLogin(),
                            user.getPassword()
                    )
            );
        } catch (BadCredentialsException e) {
            System.out.println("Invalid credentials for user authentication");
        }
        UserDetails userDetails = userDetailsService.loadUserByUsername(user.getLogin());
        return jsonToken.generateToken(userDetails);
    }
}
