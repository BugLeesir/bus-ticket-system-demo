package com.lyr.busticketsystemdemo.model.dto;

import lombok.Data;

import java.util.List;

/**
 * 订单查询DTO
 *
 * @author yunruili
 * @date 2025/04/10/03:51
 **/
@Data
public class OrderSearchDTO {

    /**
     * 订单状态
     */
    private String orderStatus;

    /**
     * 下单用户姓名
     */
    private String username;

    /**
     * 订单创建时间
     */
    private List<String> createTime;

    /**
     * 当前页
     */
    private Integer pageNum;

    /**
     * 每页显示数量
     */
    private Integer pageSize;
}
