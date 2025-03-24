package com.lyr.busticketsystemdemo.model.vo;

import lombok.Data;

/**
 * 会员查询VO
 *
 * @author yunruili
 * @date 2025/03/23/23:24
 **/
@Data
public class MemberSearchVO {
    /**
     * 会员id
     */
    private String id;
    /**
     * 会员姓名
     */
    private String username;
    /**
     * 会员手机号
     */
    private String phone;
    /**
     * 会员注册时间
     */
    private String registerTime;
    /**
     * 会员邮箱
     */
    private String email;
    /**
     * 会员状态
     */
    private Integer status;

}
