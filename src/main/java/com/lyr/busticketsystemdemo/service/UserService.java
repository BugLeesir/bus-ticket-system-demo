package com.lyr.busticketsystemdemo.service;

import com.lyr.busticketsystemdemo.domain.User;
import com.baomidou.mybatisplus.extension.service.IService;
import com.lyr.busticketsystemdemo.model.dto.LoginDTO;
import com.lyr.busticketsystemdemo.model.dto.MemberDTO;

/**
* @author yunruili
* @description 针对表【user(用户信息表)】的数据库操作Service
* @createDate 2025-03-13 18:34:26
*/
public interface UserService extends IService<User> {
    /**
     * 添加会员
     * @param memberDTO
     * @return
     */
    boolean addMember(MemberDTO memberDTO);

    /**
     * 检查登录
     * @param loginDTO
     * @return
     */
    boolean checkLogin(LoginDTO loginDTO);
}
