package com.chain.com.chain.dao;

import com.chain.mode.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountDao  extends JpaRepository<Account,Integer> {
}