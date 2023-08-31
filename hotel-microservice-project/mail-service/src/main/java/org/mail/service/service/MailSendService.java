package org.mail.service.service;

import jakarta.mail.MessagingException;
import org.mail.service.dto.MailRequestDTO;
import org.mail.service.mail_types.GeneralMail;

public interface MailSendService {

    void sendMessage(MailRequestDTO requestDTO, String activationCode, GeneralMail mail) throws MessagingException;
}
