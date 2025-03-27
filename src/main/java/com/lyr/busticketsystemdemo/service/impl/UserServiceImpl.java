package com.lyr.busticketsystemdemo.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.github.yitter.idgen.YitIdHelper;
import com.lyr.busticketsystemdemo.dao.mapper.UserRoleMapper;
import com.lyr.busticketsystemdemo.domain.User;
import com.lyr.busticketsystemdemo.domain.UserRole;
import com.lyr.busticketsystemdemo.model.dto.*;
import com.lyr.busticketsystemdemo.model.vo.AdminSearchVO;
import com.lyr.busticketsystemdemo.model.vo.MemberSearchVO;
import com.lyr.busticketsystemdemo.service.UserService;
import com.lyr.busticketsystemdemo.dao.mapper.UserMapper;
import com.lyr.busticketsystemdemo.util.PasswordUtil;
import com.lyr.busticketsystemdemo.util.RSAUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
* @author yunruili
* @description 针对表【user(用户信息表)】的数据库操作Service实现
* @createDate 2025-03-13 18:34:26
*/
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
    implements UserService{
    @Autowired
    private UserRoleMapper userRoleMapper;

    @Autowired
    private UserMapper userMapper;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean addMember(MemberDTO memberDTO) {
        // 将MemberDTO转换为User实体
        User user = new User();
        user.setUserId(YitIdHelper.nextId());
        user.setUsername(memberDTO.getUsername());
        // 加密密码
        user.setPassword(PasswordUtil.hashPassword(memberDTO.getPassword()));
        user.setEmail(memberDTO.getEmail());
        user.setPhone(memberDTO.getPhone());
        user.setStatus(memberDTO.getStatus());

        // 保存User实体到数据库
        boolean userSaved = this.save(user);
        if (!userSaved) {
            return false;
        }

        // 创建UserRole实体并设置角色
        UserRole userRole = new UserRole();
        userRole.setUserId(user.getUserId());
        // 1: 会员, 2: 管理员
        userRole.setRoleId(1L);

        // 保存UserRole实体到数据库
        int rows = userRoleMapper.insert(userRole);
        return rows > 0;
    }

    @Override
    public boolean addAdmin(AdminDTO adminDTO) {
        // 将MemberDTO转换为User实体
        User user = new User();
        user.setUserId(YitIdHelper.nextId());
        user.setUsername(adminDTO.getUsername());
        // 加密密码
        user.setPassword(PasswordUtil.hashPassword(adminDTO.getPassword()));
        user.setEmail(adminDTO.getEmail());
        user.setPhone(adminDTO.getPhone());
        user.setStatus(adminDTO.getStatus());

        // 保存User实体到数据库
        boolean userSaved = this.save(user);
        if (!userSaved) {
            return false;
        }

        // 创建UserRole实体并设置角色
        UserRole userRole = new UserRole();
        userRole.setUserId(user.getUserId());
        // 1: 会员, 2: 管理员
        userRole.setRoleId(2L);

        // 保存UserRole实体到数据库
        int rows = userRoleMapper.insert(userRole);
        return rows > 0;
    }

    @Override
    public Integer checkLogin(LoginDTO loginDTO) {
        User user = userMapper.getUserByName(loginDTO.getUsername());
        // 情况1：用户不存在 -1
        if (user == null) {
            return -1;
        }else {
            // 情况2：用户被禁用 0
            if(user.getStatus() == 0) {
                return 0;
            }
        }
        // 解密密码
        String rawPassword = RSAUtil.decryptPassword(loginDTO.getPassword());
        // 情况3：密码错误 -2
        return PasswordUtil.matches(rawPassword, user.getPassword()) ? 1 : -2;
    }

    @Override
    public boolean checkHasUser(String userName) {
        return userMapper.getUserByName(userName) != null;
    }

    @Override
    public boolean setStatus(Long userId, Integer status) {
        // 根据id查询看是否存在该用户
        User user = userMapper.selectById(userId);
        if( user == null) {
            return false;
        }
        // 更新user表中的用户状态
        user.setStatus(status);
        return userMapper.updateById(user) > 0;
    }

    @Override
    public boolean deleteUser(Long userId) {
        // 根据id查询看是否存在该用户
        if(userMapper.selectById(userId) == null) {
            return false;
        }
        // 删除user表中的用户
        return userMapper.deleteById(userId) > 0;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean batchDeleteUser(List<Long> userIds) {
        // 删除user表中的用户
        return userMapper.deleteBatchIds(userIds) > 0;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean batchSetStatus(List<Long> userIds, Integer status) {
        return userMapper.batchSetStatus(userIds, status);
    }

    @Override
    public boolean updateUser(UserUpdateDTO userUpdateDTO) {
        // 查询该用户
        User user = userMapper.selectById(userUpdateDTO.getId());
        // 更新用户信息
        user.setUsername(userUpdateDTO.getUsername());
        user.setEmail(userUpdateDTO.getEmail());
        user.setPhone(userUpdateDTO.getPhone());
        user.setStatus(userUpdateDTO.getStatus());
        // 如果密码不为空，则更新密码
        if(userUpdateDTO.getPassword() != null) {
            user.setPassword(PasswordUtil.hashPassword(RSAUtil.decryptPassword(userUpdateDTO.getPassword())));
        }
        return userMapper.updateById(user) > 0;
    }

    @Override
    public Long getUserIdByUserName(String userName) {
        return userMapper.getUserByName(userName).getUserId();
    }

    @Override
    public PageInfo<MemberSearchVO> searchMembers(MemberSearchDTO memberSearchDTO) {
        // 开启分页
        PageHelper.startPage(memberSearchDTO.getPageNum(), memberSearchDTO.getPageSize());

        // 查询用户列表
        List<User> userList = userMapper.searchMembers(memberSearchDTO);

        // **封装 PageInfo，确保分页数据完整**
        PageInfo<User> pageInfo = new PageInfo<>(userList);

        // 转换 `User` 为 `MemberSearchVO`
        List<MemberSearchVO> memberSearchVOList = userList.stream().map(user -> {
            MemberSearchVO memberSearchVO = new MemberSearchVO();
            memberSearchVO.setId(user.getUserId().toString());
            memberSearchVO.setUsername(user.getUsername());
            memberSearchVO.setEmail(user.getEmail());
            memberSearchVO.setPhone(user.getPhone());
            memberSearchVO.setStatus(user.getStatus());

            // 转换注册时间格式
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate localDate = user.getCreateTime().toInstant()
                    .atZone(ZoneOffset.UTC) // 确保时区一致
                    .toLocalDate();
            memberSearchVO.setRegisterTime(localDate.format(formatter));

            return memberSearchVO;
        }).toList();

        // **用原 `PageInfo` 复制分页信息**
        PageInfo<MemberSearchVO> resultPageInfo = new PageInfo<>(memberSearchVOList);
        resultPageInfo.setPageNum(pageInfo.getPageNum());
        resultPageInfo.setPageSize(pageInfo.getPageSize());
        resultPageInfo.setTotal(pageInfo.getTotal());
        resultPageInfo.setPages(pageInfo.getPages());

        return resultPageInfo;
    }

    @Override
    public PageInfo<AdminSearchVO> searchAdmins(AdminSearchDTO adminSearchDTO) {
        // 开启分页
        PageHelper.startPage(adminSearchDTO.getPageNum(), adminSearchDTO.getPageSize());

        // 查询用户列表
        List<User> userList = userMapper.searchAdmins(adminSearchDTO);

        // **封装 PageInfo，确保分页数据完整**
        PageInfo<User> pageInfo = new PageInfo<>(userList);

        // 转换 `User` 为 `MemberSearchVO`
        List<AdminSearchVO> adminSearchVOList = userList.stream().map(user -> {
            AdminSearchVO adminSearchVO = new AdminSearchVO();
            adminSearchVO.setId(user.getUserId().toString());
            adminSearchVO.setUsername(user.getUsername());
            adminSearchVO.setEmail(user.getEmail());
            adminSearchVO.setPhone(user.getPhone());
            adminSearchVO.setStatus(user.getStatus());

            // 转换注册时间格式
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate localDate = user.getCreateTime().toInstant()
                    .atZone(ZoneOffset.UTC) // 确保时区一致
                    .toLocalDate();
            adminSearchVO.setRegisterTime(localDate.format(formatter));

            return adminSearchVO;
        }).toList();

        // **用原 `PageInfo` 复制分页信息**
        PageInfo<AdminSearchVO> resultPageInfo = new PageInfo<>(adminSearchVOList);
        resultPageInfo.setPageNum(pageInfo.getPageNum());
        resultPageInfo.setPageSize(pageInfo.getPageSize());
        resultPageInfo.setTotal(pageInfo.getTotal());
        resultPageInfo.setPages(pageInfo.getPages());

        return resultPageInfo;
    }

}




