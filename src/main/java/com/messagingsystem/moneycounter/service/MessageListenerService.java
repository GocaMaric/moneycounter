package com.messagingsystem.moneycounter.service;

import com.messagingsystem.moneycounter.entity.Account;
import com.messagingsystem.moneycounter.repository.AccountRepository;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Service
public class MessageListenerService {

    private final AccountRepository accountRepository;

    @Autowired
    public MessageListenerService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Transactional
    @RabbitListener(queues = "${messaging.queue.name}")
    public void receiveMessage(MessagePublisherService.TransactionMessage message) {
        Account account = accountRepository.findById(1L).orElseGet(Account::new);

        // Update balance
        BigDecimal newBalance = account.getBalance().add(message.getAmount());
        account.setBalance(newBalance);

        // Save the updated account
        accountRepository.save(account);
    }
}
