package com.lyr.busticketsystemdemo.model.dto;

import lombok.Data;

import java.util.List;

/**
 * 会员查询DTO
 *
 * @author yunruili
 * @date 2025/03/23/23:23
 **/
@Data
public class MemberSearchDTO {
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
    private List<String> registerTime;

    /**
     * 当前页
     */
    private Integer pageNum;

    /**
     * 每页显示数量
     */
    private Integer pageSize;
}
