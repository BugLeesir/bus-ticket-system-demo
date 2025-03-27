package com.lyr.busticketsystemdemo.model.vo;

import lombok.Data;

/**
 * 管理员查询VO
 *
 * @author yunruili
 * @date 2025/03/25/23:35
 **/
@Data
public class AdminSearchVO {
    /**
     * 管理员id
     */
    private String id;
    /**
     * 管理员姓名
     */
    private String username;
    /**
     * 管理员手机号
     */
    private String phone;
    /**
     * 管理员注册时间
     */
    private String registerTime;
    /**
     * 管理员邮箱
     */
    private String email;
    /**
     * 管理员状态
     */
    private Integer status;

}

