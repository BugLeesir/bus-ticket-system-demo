package com.lyr.busticketsystemdemo.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lyr.busticketsystemdemo.domain.RolePermission;
import com.lyr.busticketsystemdemo.service.RolePermissionService;
import com.lyr.busticketsystemdemo.dao.mapper.RolePermissionMapper;
import org.springframework.stereotype.Service;

/**
* @author yunruili
* @description 针对表【role_permission(角色权限关联表)】的数据库操作Service实现
* @createDate 2025-03-13 18:34:26
*/
@Service
public class RolePermissionServiceImpl extends ServiceImpl<RolePermissionMapper, RolePermission>
    implements RolePermissionService{

}




