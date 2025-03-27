package com.lyr.busticketsystemdemo.controller;

import cn.dev33.satoken.stp.StpUtil;
import cn.dev33.satoken.util.SaResult;
import com.lyr.busticketsystemdemo.model.dto.*;
import com.lyr.busticketsystemdemo.service.RoleService;
import com.lyr.busticketsystemdemo.service.UserService;
import com.lyr.busticketsystemdemo.util.RSAUtil;
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
    public SaResult login(@RequestBody LoginDTO loginDTO) {
        Integer checkResult = userService.checkLogin(loginDTO);
        if(checkResult == 1) {
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
            return SaResult.data(userResultDTO);
        } else if(checkResult == 0) {
            return SaResult.error("用户被禁用");
        } else if (checkResult == -1 ) {
            return SaResult.error("用户不存在");
        } else  {
            return SaResult.error("密码错误");
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
    public SaResult register(@RequestBody RegisterDTO registerDTO) {
        // 注册
        if(userService.checkHasUser(registerDTO.getUsername())) {
            return SaResult.error("用户名已存在");
        }
        MemberDTO memberDTO = new MemberDTO();
        memberDTO.setUsername(registerDTO.getUsername());
        // 解密密码
        memberDTO.setPassword(RSAUtil.decryptPassword(registerDTO.getPassword()));
        memberDTO.setEmail(registerDTO.getEmail());
        memberDTO.setPhone(registerDTO.getPhone());
        memberDTO.setStatus(1);
        if(userService.addMember(memberDTO)) {
            return SaResult.ok("注册成功");
        } else {
            return SaResult.error("注册失败");
        }
    }

    @DeleteMapping("/delete")
    public SaResult delete (@RequestParam String id){
        boolean result = userService.deleteUser(Long.valueOf(id));
        if(result) {
            return SaResult.ok("删除成功");
        }else {
            return SaResult.error("删除失败");
        }
    }

    @PutMapping("/status")
    public SaResult setStatus(@RequestParam String id, @RequestParam Integer status){
        boolean result = userService.setStatus(Long.valueOf(id), status);
        if(result) {
            return SaResult.ok("设置成功");
        }else {
            return SaResult.error("设置失败");
        }
    }

    @DeleteMapping("/batchDelete")
    public SaResult batchDelete(@RequestBody UserBatchDeleteDTO userBatchDeleteDTO){
        List<String> ids = userBatchDeleteDTO.getIds();
        // id不能为空
        if(ids == null || ids.isEmpty()) {
            return SaResult.error("id不能为空");
        }
        List<Long> userIds = new ArrayList<>();
        // 将String类型的id转换为Long类型
        ids.forEach(id -> {
            userIds.add(Long.valueOf(id));
        });
        boolean result = userService.batchDeleteUser(userIds);
        if(result) {
            return SaResult.ok("批量删除成功");
        }else {
            return SaResult.error("批量删除失败");
        }
    }

    @PutMapping("/batchSetStatus")
    public SaResult batchSetStatus(@RequestBody UserBatchSetStatusDTO userBatchSetStatusDTO){
        List<String> ids = userBatchSetStatusDTO.getIds();
        // id不能为空
        if(ids == null || ids.isEmpty()) {
            return SaResult.error("id不能为空");
        }
        List<Long> userIds = new ArrayList<>();
        // 将String类型的id转换为Long类型
        ids.forEach(id -> {
            userIds.add(Long.valueOf(id));
        });
        boolean result = userService.batchSetStatus(userIds, userBatchSetStatusDTO.getStatus());
        if(result) {
            return SaResult.ok("批量设置成功");
        }else {
            return SaResult.error("批量设置失败");
        }
    }

    @PutMapping("/update")
    public SaResult update(@RequestBody UserUpdateDTO userUpdateDTO){
        boolean result = userService.updateUser(userUpdateDTO);
        if(result) {
            return SaResult.ok("更新成功");
        }else {
            return SaResult.error("更新失败");
        }
    }
}
