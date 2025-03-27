package com.lyr.busticketsystemdemo.model.dto;

import lombok.Data;

/**
 * 用户更新DTO
 *
 * @author yunruili
 * @date 2025/03/25/20:26
 **/
@Data
public class UserUpdateDTO {

    /**
     * 用户id
     */
    private String id;

    /**
     * 用户姓名
     */
    private String username;

    /**
     * 用户邮箱
     */
    private String email;

    /**
     * 用户手机号
     */
    private String phone;

    /**
     * 用户状态
     */
    private Integer status;

    /**
     * 用户密码
     */
    private String password;


}
