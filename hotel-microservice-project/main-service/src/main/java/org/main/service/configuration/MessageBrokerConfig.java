package org.main.service.configuration;

import org.main.service.dto.MailRequestDTO;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.DefaultJackson2JavaTypeMapper;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

@Configuration
public class MessageBrokerConfig {

    public static String QUEUE = "mail-sending-queue";
    public static String EXCHANGE = "mail-exchange";
    public static String ROUTING_KEY = "registration-notification";

    @Bean
    public MessageConverter converter() {
        Jackson2JsonMessageConverter converter = new Jackson2JsonMessageConverter();
        converter.setClassMapper(mapper());
        return converter;
    }

    @Bean
    public RabbitTemplate template(ConnectionFactory factory, MessageConverter converter) {
        RabbitTemplate template = new RabbitTemplate(factory);
        template.setMessageConverter(converter);
        return template;
    }

    @Bean
    public DefaultJackson2JavaTypeMapper mapper() {
        DefaultJackson2JavaTypeMapper mapper = new DefaultJackson2JavaTypeMapper();
        mapper.setIdClassMapping(Map.of("mail-request", MailRequestDTO.class));
        return mapper;
    }

    @Bean
    public Queue createQueue() {
        return new Queue(QUEUE);
    }

    @Bean
    public Exchange exchange() {
        return new DirectExchange(EXCHANGE, true, false);
    }

    @Bean
    public Binding binding(Queue queue, Exchange exchange) {
        return BindingBuilder
                .bind(queue)
                .to(exchange)
                .with(ROUTING_KEY)
                .noargs();
    }
}
