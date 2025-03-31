package com.lyr.busticketsystemdemo.model.dto;

import lombok.Data;

import java.util.List;

/**
 * 用户返回信息DTO
 *
 * @author yunruili
 * @date 2025/03/14/04:02
 **/
@Data
public class UserResultDTO {
    /**
     * 用户名
     */
    private  String username;

    /**
     * 用户id
     */
    private  String userId;

    /**
     * 用户角色
     */
    private List<String> roles;

    /**
     * 用户状态
     */
    private Integer status;

    /**
     * 用户token
     */
    private String token;

    /**
     * token名称
     */
    private String tokenName;

}
