package org.mail.service.controller;

import jakarta.mail.MessagingException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.mail.service.dto.MailRequestDTO;
import org.mail.service.exception.MailSendingException;
import org.mail.service.exception.UserAlreadyExistsException;
import org.mail.service.mail_types.ActivationMail;
import org.mail.service.service.RegisterAndMessageService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@Slf4j
@RestController
@RequestMapping("/mail")
@AllArgsConstructor
public class MailController {

    private final RegisterAndMessageService service;
    private final ActivationMail activationMail;

    @PostMapping("/activate/{activationCode}")
    @ResponseStatus(HttpStatus.OK)
    public String activateAccount(@PathVariable String activationCode,
                                  @RequestBody MailRequestDTO requestDTO
    ) {
        log.info("INPUT PARAMS: " + activationCode + " " + requestDTO);
        try {
            service.addNewUserAndSendMessage(requestDTO, activationCode, activationMail);
        } catch (UserAlreadyExistsException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        } catch (MailSendingException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Failed to register user. Please, try again in a few minutes.");
        }

        return String.format("Message was sent to the %s", requestDTO.getEmail());
    }
}
