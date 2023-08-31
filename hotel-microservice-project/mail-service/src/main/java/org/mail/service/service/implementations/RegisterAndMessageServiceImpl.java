package org.mail.service.service.implementations;

import jakarta.mail.MessagingException;
import lombok.AllArgsConstructor;
import org.mail.service.dto.MailRequestDTO;
import org.mail.service.entity.User;
import org.mail.service.exception.MailSendingException;
import org.mail.service.exception.UserAlreadyExistsException;
import org.mail.service.mail_types.GeneralMail;
import org.mail.service.service.MailSendService;
import org.mail.service.service.RegisterAndMessageService;
import org.mail.service.service.UserStorageService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@AllArgsConstructor
public class RegisterAndMessageServiceImpl implements RegisterAndMessageService {

    private final MailSendService mailService;
    private final UserStorageService userService;


    //TODO: Check why rollbackFor parameter doesn't work
    @Override
    @Transactional(rollbackFor = {MailSendingException.class})
    public void addNewUserAndSendMessage(MailRequestDTO requestDTO, String activationCode, GeneralMail mail) throws UserAlreadyExistsException, MailSendingException {
        if (userService.searchUserByEmail(requestDTO.getEmail()).isPresent()) {
            System.out.println("SUCH USER IS ALREADY REGISTERED " + requestDTO.getEmail());
            throw new UserAlreadyExistsException(String.format("User with login %s is already registered", requestDTO.getEmail()));
        }

        userService.createNewUser(
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
//            LOGS
            throw new MailSendingException(e.getMessage());
        }
    }
}
