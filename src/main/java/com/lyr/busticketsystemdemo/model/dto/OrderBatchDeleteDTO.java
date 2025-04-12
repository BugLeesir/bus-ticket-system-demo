package com.lyr.busticketsystemdemo.model.dto;

import lombok.Data;

import java.util.List;

/**
 * 订单批量删除DTO
 *
 * @author yunruili
 * @date 2025/04/10/03:52
 **/
@Data
public class OrderBatchDeleteDTO {
    /**
     * 订单ID列表
     */
    private List<String> ids;
}
