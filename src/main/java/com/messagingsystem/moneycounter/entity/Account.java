package com.messagingsystem.moneycounter.entity;

import lombok.Data;
import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Data
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private BigDecimal balance;

    public Account() {
        // Setting initial balance to 0
        this.balance = BigDecimal.ZERO;
    }

    // Lombok will generate the getters and setters
}