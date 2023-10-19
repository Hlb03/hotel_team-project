package org.main.service.service;

import org.main.service.dto.MailRequestDTO;

public interface RabbitMQMessageService {

    void sendMessage(MailRequestDTO requestDTO);
}
