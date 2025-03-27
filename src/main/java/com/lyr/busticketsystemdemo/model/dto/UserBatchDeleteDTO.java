package com.lyr.busticketsystemdemo.model.dto;

import lombok.Data;

import java.util.List;

/**
 * 用户批量删除DTO
 *
 * @author yunruili
 * @date 2025/03/25/20:39
 **/
@Data
public class UserBatchDeleteDTO {

    /**
     * 用户id列表
     */
    private List<String> ids;

}
