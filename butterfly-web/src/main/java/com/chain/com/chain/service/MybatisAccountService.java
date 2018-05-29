package com.chain.com.chain.service;

import com.chain.AccountMapper;
import com.chain.mode.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by lian.ran on 2018/2/13.
 */
@Service
public class MybatisAccountService {

    @Autowired
    private AccountMapper accountMapper;

    public int add(String name, double money) {
        return accountMapper.add(name, money);
    }
    public int update(String name, double money, int id) {
        return accountMapper.update(name, money, id);
    }
    public int delete(int id) {
        return accountMapper.delete(id);
    }
    public Account findAccount(int id) {
        return accountMapper.findAccount(id);
    }
    public List<Account> findAccountList() {
        return accountMapper.findAccountList();
    }

    /**
     * 测试事物处理机制
     */
  //  @Transactional
    public void testTransactional(){
        update("reeee", 100, 6);
       int i=1/0;
        update("eed34", 500, 5);
    }
}
