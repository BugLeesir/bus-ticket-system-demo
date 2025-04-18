package com.lyr.busticketsystemdemo.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lyr.busticketsystemdemo.dao.mapper.UserRoleMapper;
import com.lyr.busticketsystemdemo.domain.Role;
import com.lyr.busticketsystemdemo.service.RoleService;
import com.lyr.busticketsystemdemo.dao.mapper.RoleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* @author yunruili
* @description 针对表【role(角色信息表)】的数据库操作Service实现
* @createDate 2025-03-13 18:34:26
*/
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role>
    implements RoleService{

    @Autowired
    private UserRoleMapper userRoleMapper;

    @Override
    public List<Role> getRoleListByUserId(Long userId) {
        List<Long> roleIds = userRoleMapper.getRoleIdsByUserId(userId);
        return this.listByIds(roleIds);
    }
}




