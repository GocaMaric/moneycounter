package com.messagingsystem.moneycounter.controller;

import com.messagingsystem.moneycounter.entity.Account;
import com.messagingsystem.moneycounter.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/balance")
public class BalanceController {

    private final AccountRepository accountRepository;

    @Autowired
    public BalanceController(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @GetMapping
    public ResponseEntity<?> getCurrentBalance() {
        // Assuming there's only one account for simplicity
        Optional<Account> accountOpt = accountRepository.findById(1L);

        if (!accountOpt.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        Account account = accountOpt.get();
        return ResponseEntity.ok(account.getBalance());
    }
}