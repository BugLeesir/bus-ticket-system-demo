package com.lyr.busticketsystemdemo.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lyr.busticketsystemdemo.domain.UserRole;
import com.lyr.busticketsystemdemo.service.UserRoleService;
import com.lyr.busticketsystemdemo.dao.mapper.UserRoleMapper;
import org.springframework.stereotype.Service;

/**
* @author yunruili
* @description 针对表【user_role(用户角色关联表)】的数据库操作Service实现
* @createDate 2025-03-13 18:34:26
*/
@Service
public class UserRoleServiceImpl extends ServiceImpl<UserRoleMapper, UserRole>
    implements UserRoleService{

}




