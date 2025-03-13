package com.lyr.busticketsystemdemo.model.dto;

import lombok.Data;

/**
 * 登录DTO
 *
 * @author yunruili
 * @date 2025/03/13/20:46
 **/
@Data
public class LoginDTO {

    /**
     * 用户名
     */
    private String username;

    /**
     * 密码
     */
    private String password;

}
