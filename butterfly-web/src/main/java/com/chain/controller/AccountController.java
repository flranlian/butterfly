package com.chain.controller;

import com.chain.com.chain.dao.AccountDao;
import com.chain.com.chain.service.IAccountService;
import com.chain.com.chain.service.MybatisAccountService;
import com.chain.mode.Account;
import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by fangzhipeng on 2017/4/20.
 */

@RestController
@RequestMapping("/account")
public class AccountController {

    @Autowired
   IAccountService accountService;
    //@Autowired
    //MybatisAccountService mybatisAccountService;

    @RequestMapping(value = "/list",method = RequestMethod.GET)
    public  List<Account> getAccounts(){
       // return   mybatisAccountService.findAccountList();
       return accountService.findAccountList();
    }

    @RequestMapping(value = "/listPage/{page}",method = RequestMethod.GET)
    public  List<Account> getAccountsPage(@PathVariable("page") int page){
        return accountService.findAccountByPage(page,2);
    }

    @RequestMapping(value = "/{id}",method = RequestMethod.GET)
    public Account getAccountById(@PathVariable("id") int id){
        return accountService.findAccountById(id);
    }

    @RequestMapping(value = "/{id}",method = RequestMethod.PUT)
    public  String updateAccount(@PathVariable("id")int id , @RequestParam(value = "name",required = true)String name,
    @RequestParam(value = "money" ,required = true)double money){
        Account account=new Account();
        account.setMoney(money);
        account.setName(name);
        account.setId(id);
        int t=accountService.update(account);


        if(t==1){   //toString 方法
            return  ReflectionToStringBuilder.reflectionToString(account, ToStringStyle.MULTI_LINE_STYLE);
        }else {
            return "fail";
        }
    }

    @RequestMapping(value = "",method = RequestMethod.POST)
    public  String postAccount( @RequestParam(value = "name")String name,
                                 @RequestParam(value = "money" )double money){
        Account account=new Account();
        account.setMoney(money);
        account.setName(name);
        int t= accountService.add(account);
        if(t==1){
            return account.toString();
        }else {
            return "fail";
        }

    }

    @PostMapping("/create")
    public Account create1(@RequestBody Account account) {
        System.out.println(ReflectionToStringBuilder.reflectionToString(account, ToStringStyle.MULTI_LINE_STYLE));
        account.setId(1);
        return account;
    }

}
