package com.lyr.busticketsystemdemo.model.vo;

import lombok.Data;

/**
 * 订单详情查询VO
 *
 * @author yunruili
 * @date 2025/04/11/21:25
 **/
@Data
public class OrderDetailSearchVO {
    /**
     * 订单详情ID（主键）
     */
    private String detailId;

    /**
     * 关联订单ID
     */
    private String orderId;

    /**
     * 乘客姓名
     */
    private String passengerName;

    /**
     * 座位号
     */
    private String seatNumber;

}
