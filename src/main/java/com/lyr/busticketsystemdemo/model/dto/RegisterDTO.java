package com.lyr.busticketsystemdemo.model.dto;

import lombok.Data;

/**
 * 注册DTO
 *
 * @author yunruili
 * @date 2025/03/15/01:13
 **/
@Data
public class RegisterDTO {
    /**
     * 用户名
     */
    private String userName;

    /**
     * 密码
     */
    private String password;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 手机号
     */
    private String phone;
}
