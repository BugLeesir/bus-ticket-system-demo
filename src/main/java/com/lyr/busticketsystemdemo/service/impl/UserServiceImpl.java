package com.lyr.busticketsystemdemo.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.yitter.idgen.YitIdHelper;
import com.lyr.busticketsystemdemo.dao.mapper.UserRoleMapper;
import com.lyr.busticketsystemdemo.domain.User;
import com.lyr.busticketsystemdemo.domain.UserRole;
import com.lyr.busticketsystemdemo.model.dto.LoginDTO;
import com.lyr.busticketsystemdemo.model.dto.MemberDTO;
import com.lyr.busticketsystemdemo.service.UserService;
import com.lyr.busticketsystemdemo.dao.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
* @author yunruili
* @description 针对表【user(用户信息表)】的数据库操作Service实现
* @createDate 2025-03-13 18:34:26
*/
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
    implements UserService{
    @Autowired
    private UserRoleMapper userRoleMapper;

    @Autowired
    private UserMapper userMapper;

    @Override
    public boolean addMember(MemberDTO memberDTO) {
        // 将MemberDTO转换为User实体
        User user = new User();
        user.setUserId(YitIdHelper.nextId());
        user.setUsername(memberDTO.getUsername());
        user.setPassword(memberDTO.getPassword());
        user.setEmail(memberDTO.getEmail());
        user.setPhone(memberDTO.getPhone());
        user.setStatus(memberDTO.getStatus());

        // 保存User实体到数据库
        boolean userSaved = this.save(user);
        if (!userSaved) {
            return false;
        }

        // 创建UserRole实体并设置角色
        UserRole userRole = new UserRole();
        userRole.setUserId(user.getUserId());
        // 1: 会员, 2: 管理员
        userRole.setRoleId(Long.valueOf(1));

        // 保存UserRole实体到数据库
        int rows = userRoleMapper.insert(userRole);
        return rows > 0;
    }

    @Override
    public boolean checkLogin(LoginDTO loginDTO) {
        User user = userMapper.getUserByName(loginDTO.getUsername());
        if (user == null) {
            return false;
        }
        return user.getPassword().equals(loginDTO.getPassword());
    }
}




