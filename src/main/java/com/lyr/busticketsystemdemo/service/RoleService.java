package com.lyr.busticketsystemdemo.service;

import com.lyr.busticketsystemdemo.domain.Role;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
* @author yunruili
* @description 针对表【role(角色信息表)】的数据库操作Service
* @createDate 2025-03-13 18:34:26
*/
public interface RoleService extends IService<Role> {

    /**
     * 根据用户id获取角色列表
     *
     * @param userId 用户id
     * @return
     */
    List<Role> getRoleListByUserId(Long userId);
}
