package com.lyr.busticketsystemdemo.controller;

import cn.dev33.satoken.stp.StpUtil;
import com.lyr.busticketsystemdemo.model.dto.LoginDTO;
import com.lyr.busticketsystemdemo.model.dto.RegisterDTO;
import com.lyr.busticketsystemdemo.model.dto.UserResultDTO;
import com.lyr.busticketsystemdemo.service.RoleService;
import com.lyr.busticketsystemdemo.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

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

    @Autowired
    private RoleService roleService;

    @PostMapping("/login")
    public UserResultDTO login(@RequestBody LoginDTO loginDTO) {
        if(userService.checkLogin(loginDTO)) {
            // 进行登录
            Long userId = userService.getUserIdByUserName(loginDTO.getUsername());
            StpUtil.login(userId);
            UserResultDTO userResultDTO = new UserResultDTO();
            userResultDTO.setUsername(loginDTO.getUsername());
            List<String>roleList = new ArrayList<>();
            roleService.getRoleListByUserId(userId).forEach(role -> {
                roleList.add(role.getRoleName());
            });
            userResultDTO.setRoles(roleList);
            userResultDTO.setToken(StpUtil.getTokenValue());
            userResultDTO.setTokenName(StpUtil.getTokenName());
            return userResultDTO;
        } else {
            return null;
        }
    }

    @GetMapping("/logout")
    public void logout() {
        StpUtil.logout();
    }

    @GetMapping("/info")
    public UserResultDTO getUserByToken(String token) {
        Long userId = Long.valueOf(StpUtil.getLoginIdByToken(token).toString());
        UserResultDTO userResultDTO = new UserResultDTO();
        userResultDTO.setUsername(userService.getById(userId).getUsername());
        List<String> roleList = new ArrayList<>();
        roleService.getRoleListByUserId( userId).forEach(role -> {
            roleList.add(role.getRoleName());
        });
        userResultDTO.setRoles(roleList);
        return userResultDTO;
    }

    @PostMapping("/register")
    public void register(@RequestBody RegisterDTO registerDTO) {
        // 注册
    }
}
