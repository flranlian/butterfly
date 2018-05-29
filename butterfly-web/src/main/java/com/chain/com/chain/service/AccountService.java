package com.chain.com.chain.service;

import com.chain.AccountMapper;
import com.chain.com.chain.dao.AccountDao;
import com.chain.com.chain.dao.IAccountDAO;
import com.chain.com.chain.service.IAccountService;
import com.chain.mode.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountService implements IAccountService {
    @Autowired
    IAccountDAO accountDAO;

    @Autowired
    AccountDao accountDao;

    @Override
    public int add(Account account) {
        //return accountDAO.add(account);
        Account result =  accountDao.save(account);
        System.out.println(result);
        if(result!=null){
           return 1;
        }
        return 0;
    }

    @Override
    public int update(Account account) {
        return accountDAO.update(account);
    }

    @Override
    public int delete(int id) {
        return accountDAO.delete(id);
    }

    @Override
    public Account findAccountById(int id) {
        return accountDAO.findAccountById(id);
    }

    @Override
    public List<Account> findAccountList() {
      //  return accountDAO.findAccountList();
        return  accountDao.findAll();
    }

    @Override
    public List<Account> findAccountByPage(int page, int size) {
        Pageable pageable2 = new PageRequest(page, size, Sort.Direction.ASC, "id");
        Page<Account> r = accountDao.findAll(pageable2);
        if(r!=null){
           int totalPages=  r.getTotalPages();
           System.out.println("结果:"+r.getContent());
           return r.getContent();
        }
        return null;
    }
}
