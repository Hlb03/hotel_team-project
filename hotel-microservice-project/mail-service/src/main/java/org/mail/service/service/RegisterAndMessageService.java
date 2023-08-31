package org.mail.service.service;

import jakarta.mail.MessagingException;
import org.mail.service.dto.MailRequestDTO;
import org.mail.service.exception.MailSendingException;
import org.mail.service.exception.UserAlreadyExistsException;
import org.mail.service.mail_types.GeneralMail;

public interface RegisterAndMessageService {

    void addNewUserAndSendMessage(MailRequestDTO requestDTO, String activationCode, GeneralMail mail) throws UserAlreadyExistsException, MailSendingException;
}
