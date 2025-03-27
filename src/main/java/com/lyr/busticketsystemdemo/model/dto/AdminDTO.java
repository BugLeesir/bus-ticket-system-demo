package com.lyr.busticketsystemdemo.model.dto;

import lombok.Data;

/**
 * 管理员DTO
 *
 * @author yunruili
 * @date 2025/03/25/23:30
 **/
@Data
public class AdminDTO {

    /**
     * 管理员姓名
     */
    private String username;

    /**
     * 管理员邮箱
     */
    private String email;

    /**
     * 管理员手机号
     */
    private String phone;

    /**
     * 管理员状态
     */
    private Integer status;

    /**
     * 管理员密码
     */
    private String password;

}
