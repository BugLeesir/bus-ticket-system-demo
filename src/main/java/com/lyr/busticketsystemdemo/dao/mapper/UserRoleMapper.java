package com.lyr.busticketsystemdemo.dao.mapper;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.lyr.busticketsystemdemo.domain.UserRole;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.sql.Wrapper;
import java.util.List;
import java.util.stream.Collectors;

/**
* @author yunruili
* @description 针对表【user_role(用户角色关联表)】的数据库操作Mapper
* @createDate 2025-03-13 18:34:26
* @Entity com.lyr.busticketsystemdemo.domain.UserRole
*/
public interface UserRoleMapper extends BaseMapper<UserRole> {

    default List<Long> getRoleIdsByUserId(Long userId){
        List<UserRole> userRoles = this.selectList(Wrappers.<UserRole>query().lambda().eq(UserRole::getUserId,userId));
        return userRoles.stream().map(UserRole::getRoleId).collect(Collectors.toList());
    }

    default boolean deleteByUserId(Long userId){
        return this.delete(Wrappers.<UserRole>query().lambda().eq(UserRole::getUserId,userId))>0;
    }
}




