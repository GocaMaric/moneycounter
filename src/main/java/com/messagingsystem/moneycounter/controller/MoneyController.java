package com.messagingsystem.moneycounter.controller;

import com.messagingsystem.moneycounter.service.MessagePublisherService;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@RequestMapping("/api/money")
public class MoneyController {

    private final MessagePublisherService messagePublisherService;

    @Autowired
    public MoneyController(MessagePublisherService messagePublisherService) {
        this.messagePublisherService = messagePublisherService;
    }

    @PostMapping("/send")
    public ResponseEntity<String> sendMoney(@RequestBody MoneyTransferRequest request) {
        if (!"EUR".equals(request.getCurrency())) {
            return ResponseEntity.badRequest().body("Unsupported currency. Only EUR is supported.");
        }

        if (request.getAmount().compareTo(BigDecimal.valueOf(-100000000)) < 0 ||
                request.getAmount().compareTo(BigDecimal.valueOf(100000000)) > 0) {
            return ResponseEntity.badRequest().body("The amount must be between -100000000 and +100000000 EUR.");
        }

        messagePublisherService.publishTransaction(request.getAmount(), request.getCurrency());
        return ResponseEntity.ok("Transaction is being processed.");
    }

    @Data // Lombok annotation to generate getters, setters, equals, hashCode and toString methods.
    private static class MoneyTransferRequest {
        private BigDecimal amount;
        private String currency;
    }
}