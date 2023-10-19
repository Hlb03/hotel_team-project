package org.mail.service.service.implementations;

import jakarta.mail.MessagingException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.mail.service.dto.MailRequestDTO;
import org.mail.service.entity.User;
import org.mail.service.exception.MailSendingException;
import org.mail.service.exception.UserAlreadyExistsException;
import org.mail.service.mail_types.GeneralMail;
import org.mail.service.repository.HotelUserRepository;
import org.mail.service.service.MailSendService;
import org.mail.service.service.RegisterAndMessageService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@AllArgsConstructor
@Slf4j
public class RegisterAndMessageServiceImpl implements RegisterAndMessageService {

    private final MailSendService mailService;
    private final HotelUserRepository userRepository;


    @Override
    @Transactional(rollbackFor = {UserAlreadyExistsException.class})
    public void addNewUserAndSendMessage(MailRequestDTO requestDTO, String activationCode, GeneralMail mail) throws UserAlreadyExistsException, MailSendingException {
        if (userRepository.findUserByEmail(requestDTO.getEmail()).isPresent()) {
            log.debug("SUCH USER IS ALREADY REGISTERED " + requestDTO.getEmail());
            throw new UserAlreadyExistsException(String.format("User with login %s is already registered", requestDTO.getEmail()));
        }

        userRepository.insert(
                User.builder()
                        .firstName(requestDTO.getFirstName())
                        .lastName(requestDTO.getLastName())
                        .nickname(requestDTO.getNickname())
                        .email(requestDTO.getEmail())
                        .lastUpdate(LocalDateTime.now())
                        .build()
        );

        try {
            mailService.sendMessage(requestDTO, activationCode, mail);
        } catch (MessagingException e) {
            log.debug("Messaging system went wrong");
            throw new MailSendingException(e.getMessage());
        }
    }
}
