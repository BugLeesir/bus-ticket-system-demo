package com.lyr.busticketsystemdemo.model.dto;

import lombok.Data;

import java.util.List;

/**
 * 管理员查询DTO
 *
 * @author yunruili
 * @date 2025/03/25/23:38
 **/
@Data
public class AdminSearchDTO {
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
