package com.lyr.busticketsystemdemo.model.dto;

import lombok.Data;

/**
 * 会员DTO
 *
 * @author yunruili
 * @date 2025/03/12/20:34
 **/
@Data
public class MemberDTO {

    /**
     * 会员姓名
     */
    private String username;

    /**
     * 会员密码
     */
    private String password;

    /**
     * 会员邮箱
     */
    private String email;

    /**
     * 会员手机号
     */
    private String phone;

    /**
     * 会员状态
     */
    private Integer status;

}
