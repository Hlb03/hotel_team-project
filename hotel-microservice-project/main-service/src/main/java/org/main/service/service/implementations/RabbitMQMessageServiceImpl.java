package org.main.service.service.implementations;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.main.service.dto.MailRequestDTO;
import org.main.service.service.RabbitMQMessageService;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class RabbitMQMessageServiceImpl implements RabbitMQMessageService {

    private final RabbitTemplate template;
    @Value("${rabbitmq.exchange.name}")
    private String EXCHANGE;
    @Value("${rabbitmq.routing-key}")
    private String ROUTING_KEY;

    @Override
    public void sendMessage(MailRequestDTO requestDTO) {
        template.convertAndSend(EXCHANGE, ROUTING_KEY, requestDTO);
        log.info("Message was successfully send to broker!");
    }
}
