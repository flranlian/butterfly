package com.chain.controller;

import com.chain.com.chain.service.IAccountService;
import com.chain.com.chain.service.MybatisAccountService;
import com.chain.mode.Account;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by fangzhipeng on 2017/4/20.
 */
@Api(value="mybatis列表接口")
@RestController
@RequestMapping("/myaccount")
public class MybatisAccountController {


    @Autowired
    MybatisAccountService mybatisAccountService;

    @RequestMapping(value = "/list",method = RequestMethod.GET)
    @ApiOperation(value ="查询所有账户列表", notes="")
    public  List<Account> getAccounts(){
       return   mybatisAccountService.findAccountList();
    }


    @RequestMapping(value = "/{id}",method = RequestMethod.GET)
    public Account getAccountById(@PathVariable("id") int id){
        return mybatisAccountService.findAccount(id);
    }

    @RequestMapping(value = "/update",method = RequestMethod.GET)
    public void transfer(){
        mybatisAccountService.testTransactional();
    }
}
