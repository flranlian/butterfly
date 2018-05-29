package com.chain.controller;

import com.chain.mode.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * Created by lian.ran on 2018/4/3.
 */
@Api(value="用户列表接口")
@RestController
public class UserController {


    @ApiOperation(value ="查询用户列表", notes="")
    @ApiImplicitParam(name = "user", value = "用户详细实体user", required = true, dataType = "User")
    @PostMapping("/user3")
    public User create3(@RequestBody @Valid User user, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            bindingResult.getAllErrors().forEach(error -> System.out.println(error.getDefaultMessage()));
        }
        user.setId(3);
        return user;
    }

    @PostMapping("/user4")
    public User create4(@RequestBody @Valid User user, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            bindingResult.getAllErrors().forEach(error -> System.out.println(error.getDefaultMessage()));
        }
        user.setId(4);
        return user;
    }

    @GetMapping("/user5")
    @ResponseBody
    public User user5() {
        throw new RuntimeException("User is not exist.");
    }
}
