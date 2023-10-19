package org.mail.service.controller;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.mail.service.dto.MailRequestDTO;
import org.mail.service.exception.MailSendingException;
import org.mail.service.exception.UserAlreadyExistsException;
import org.mail.service.mail_types.ActivationMail;
import org.mail.service.service.RegisterAndMessageService;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.server.ResponseStatusException;

@Slf4j
@EnableRabbit
@Component
@AllArgsConstructor
public class MessageConsumer {

    private final RegisterAndMessageService service;
    private final ActivationMail activationMail;

    @RabbitListener(queues = {"mail-sending-queue"})
    public void activateAccount(@RequestBody MailRequestDTO requestDTO) {
        log.info("INPUT PARAMS: " + requestDTO);
        try {
            service.addNewUserAndSendMessage(requestDTO, requestDTO.getActivationCode(), activationMail);
        } catch (UserAlreadyExistsException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        } catch (MailSendingException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Failed to register user. Please, try again in a few minutes.");
        }

        log.info(String.format("Message was sent to the %s\n", requestDTO.getEmail()));
    }
}
