package com.messagingsystem.moneycounter.service;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;

@Service
public class MessagePublisherService {

    private final RabbitTemplate rabbitTemplate;

    @Value("${messaging.exchange.name}")
    private String exchangeName;

    @Value("${messaging.routing.key}")
    private String routingKey;

    @Autowired
    public MessagePublisherService(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void publishTransaction(BigDecimal amount, String currency) {
        TransactionMessage transactionMessage = new TransactionMessage(amount, currency);
        rabbitTemplate.convertAndSend(exchangeName, routingKey, transactionMessage);
    }

    public static class TransactionMessage {
        private BigDecimal amount;
        private String currency;

        public TransactionMessage(BigDecimal amount, String currency) {
            this.amount = amount;
            this.currency = currency;
        }

        public BigDecimal getAmount() {
            return amount;
        }

        public String getCurrency() {
            return currency;
        }

        // If setters are not needed, they can be omitted
    }
}
