package com.lyr.busticketsystemdemo.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lyr.busticketsystemdemo.domain.Permission;
import com.lyr.busticketsystemdemo.service.PermissionService;
import com.lyr.busticketsystemdemo.dao.mapper.PermissionMapper;
import org.springframework.stereotype.Service;

/**
* @author yunruili
* @description 针对表【permission(权限信息表)】的数据库操作Service实现
* @createDate 2025-03-13 18:34:26
*/
@Service
public class PermissionServiceImpl extends ServiceImpl<PermissionMapper, Permission>
    implements PermissionService{

}




