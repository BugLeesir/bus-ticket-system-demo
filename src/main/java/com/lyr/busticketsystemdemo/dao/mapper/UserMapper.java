package com.lyr.busticketsystemdemo.dao.mapper;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.lyr.busticketsystemdemo.domain.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
* @author yunruili
* @description 针对表【user(用户信息表)】的数据库操作Mapper
* @createDate 2025-03-13 18:34:26
* @Entity com.lyr.busticketsystemdemo.domain.User
*/
public interface UserMapper extends BaseMapper<User> {

    /**
     * 根据用户名获取用户信息
     * @param userName 用户名
     * @return 用户信息
     */
    default User getUserByName(String userName){
        return this.selectOne(Wrappers.<User>query().lambda()
                .eq(User::getUsername, userName)
        );
    }
}




