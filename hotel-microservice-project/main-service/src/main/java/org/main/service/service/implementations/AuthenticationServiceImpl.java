package org.main.service.service.implementations;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.main.service.configuration.UserDetailsServiceImpl;
import org.main.service.dto.AuthenticationRequestDTO;
import org.main.service.dto.RegistrationRequestDTO;
import org.main.service.entity.AccountStatus;
import org.main.service.entity.Role;
import org.main.service.entity.User;
import org.main.service.exceptions.AuthenticationException;
import org.main.service.exceptions.IncorrectPasswordsException;
import org.main.service.exceptions.LoginAlreadyRegisteredException;
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
    public void registerUser(RegistrationRequestDTO requestDTO) throws IncorrectPasswordsException, LoginAlreadyRegisteredException {
        if (userRepository.getUserByLogin(requestDTO.getMail()).isPresent())
            throw new LoginAlreadyRegisteredException(
                    String.format("Login %s is already taken. Please, try another one.", requestDTO.getMail())
            );

        if (!requestDTO.getPassword().equals(requestDTO.getConfirmPassword()))
            throw new IncorrectPasswordsException(
                    String.format("User password in the form are different (%s and %s), please check them.", requestDTO.getPassword(), requestDTO.getConfirmPassword())
            );

        String activationCode = stringGenerator.generateRandomString(20);
        userRepository.save(
                User.builder()
                        .firstName(requestDTO.getName())
                        .lastName(requestDTO.getSurname())
                        .nickname(requestDTO.getNickname())
                        .login(requestDTO.getMail())
                        .password(encoder.encode(requestDTO.getPassword()))
                        .balance(new BigDecimal(0))
                        .activationCode(activationCode)
                        .role(Role.USER)
                        .status(AccountStatus.NOT_ACTIVATED)
                        .build()
        );

        restTemplate.postForLocation("http://MAILING/hotel-rent/mail/activate?" +
                        "receiver={login}" +
                        "&username={lastName}" +
                        "&activationCode={activationCode}",
                null,
                requestDTO.getMail(),
                requestDTO.getSurname(),
                activationCode);
    }

    @Override
    public String authenticateUser(AuthenticationRequestDTO requestDTO) throws AuthenticationException {
        System.out.println("USER CREDENTIALS: " + requestDTO);
        try {
            manager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            requestDTO.getMail(),
                            requestDTO.getPassword()
                    )
            );
        } catch (BadCredentialsException e) {
            System.out.printf("Invalid credentials for user %s authentication%n", requestDTO.getMail());
            throw new AuthenticationException("Invalid credentials for user authentication. Please check for mistakes presence.");
        }
        UserDetails userDetails = userDetailsService.loadUserByUsername(requestDTO.getMail());
        return jsonToken.generateToken(userDetails);
    }
}
