package com.messagingsystem.moneycounter.repository;

import com.messagingsystem.moneycounter.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, Long> {

}