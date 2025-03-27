package com.lyr.busticketsystemdemo.dao.mapper;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.lyr.busticketsystemdemo.domain.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lyr.busticketsystemdemo.model.dto.AdminSearchDTO;
import com.lyr.busticketsystemdemo.model.dto.MemberSearchDTO;

import java.util.List;

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

    /**
     * 批量设置用户状态
     * @param userIds 用户id列表
     * @param status 用户状态
     * @return 是否设置成功
     */
    default boolean batchSetStatus(List<Long> userIds, Integer status) {
        return this.update(null, Wrappers.<User>lambdaUpdate().in(User::getUserId, userIds).set(User::getStatus, status)) > 0 ;
    }


    List<User> searchMembers(MemberSearchDTO memberSearchDTO);

    List<User> searchAdmins(AdminSearchDTO adminSearchDTO);
}




