package com.messagingsystem.moneycounter.service;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;

@Service
public class MessagePublisherService {

    private final RabbitTemplate rabbitTemplate;

    @Autowired
    public MessagePublisherService(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void publish(BigDecimal amount, String currency) {
        // Convert and send the message
    }
}