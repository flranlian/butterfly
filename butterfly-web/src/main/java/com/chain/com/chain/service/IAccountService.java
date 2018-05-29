package com.chain.com.chain.service;

import com.chain.mode.Account;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IAccountService {


    int add(Account account);

    int update(Account account);

    int delete(int id);

    Account findAccountById(int id);

    List<Account> findAccountList();
    List<Account> findAccountByPage(int page, int size);
}