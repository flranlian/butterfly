package com.chain.controller;

import com.chain.DateUtils;
import com.chain.mode.ConfigBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 * Created by lian.ran on 2018/1/24.
 */
@RestController
public class HelloController {

   @Value("${my.name}")
    private String name;
    @Value("${my.age}")
    private int age;

    @Autowired
    ConfigBean configBean;


    @RequestMapping(value = "/hello",produces = "text/plain;charset=UTF-8")
    public String index() {
       String time =  DateUtils.getDataToStr(new Date());
       System.out.println("名称:"+configBean.getName());
        return "Hello World"+time+"bean:"+configBean.toString();
    }
}
