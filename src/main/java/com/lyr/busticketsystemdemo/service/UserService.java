package com.lyr.busticketsystemdemo.service;

import com.github.pagehelper.PageInfo;
import com.lyr.busticketsystemdemo.domain.User;
import com.baomidou.mybatisplus.extension.service.IService;
import com.lyr.busticketsystemdemo.model.dto.*;
import com.lyr.busticketsystemdemo.model.vo.AdminSearchVO;
import com.lyr.busticketsystemdemo.model.vo.MemberSearchVO;

import java.util.List;

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
     * 添加管理员
     * @param adminDTO
     * @return
     */
    boolean addAdmin(AdminDTO adminDTO);

    /**
     * 检查登录
     * @param loginDTO
     * @return
     */
    Integer checkLogin(LoginDTO loginDTO);

    /**
     * 检查是否存在用户
     * @param userName
     * @return
     */
    boolean checkHasUser(String userName);

    /**
     * 设置用户状态
     * @param userId
     * @param status
     * @return
     */
    boolean setStatus(Long userId, Integer status);

    /**
     * 删除用户
     * @param userId
     * @return
     */
    boolean deleteUser(Long userId);

    /**
     * 批量删除用户
     * @param userIds
     * @return
     */
    boolean batchDeleteUser(List<Long> userIds);

    /**
     * 批量设置用户状态
     * @param userIds
     * @param status
     * @return
     */
    boolean batchSetStatus(List<Long> userIds, Integer status);

    /**
     * 更新用户信息
     * @param userUpdateDTO
     * @return
     */
    boolean updateUser(UserUpdateDTO userUpdateDTO);

    /**
     * 根据用户名获取用户id
     * @param userName
     * @return
     */
    Long getUserIdByUserName(String userName);

    /**
     * 会员查询
     * @param memberSearchDTO
     * @return
     */
    PageInfo<MemberSearchVO> searchMembers(MemberSearchDTO memberSearchDTO);

    /**
     * 管理员查询
     * @param adminSearchDTO
     * @return
     */
    PageInfo<AdminSearchVO> searchAdmins(AdminSearchDTO adminSearchDTO);
}
