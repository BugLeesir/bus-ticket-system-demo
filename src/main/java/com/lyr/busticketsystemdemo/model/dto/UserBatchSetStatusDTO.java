package com.lyr.busticketsystemdemo.model.dto;

import lombok.Data;

import java.util.List;

/**
 * 用户批量设置状态DTO
 *
 * @author yunruili
 * @date 2025/03/25/20:41
 **/
@Data
public class UserBatchSetStatusDTO {

    /**
     * 用户id列表
     */
    private List<String> ids;

    /**
     * 用户状态
     */
    private Integer status;
}
