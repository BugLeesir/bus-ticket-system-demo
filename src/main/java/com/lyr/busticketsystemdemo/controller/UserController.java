package com.lyr.busticketsystemdemo.controller;

import com.lyr.busticketsystemdemo.model.dto.LoginDTO;
import com.lyr.busticketsystemdemo.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 用户控制器
 *
 * @author yunruili
 * @date 2025/03/13/19:32
 **/
@RestController
@RequestMapping("/user")
public class UserController {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    @PostMapping(path = "/login", produces = "application/json")
    public String login(@RequestBody LoginDTO loginDTO) {
        if(userService.checkLogin(loginDTO)) {
            return "登录成功";
        } else {
            return "登录失败";
        }
    }
}
