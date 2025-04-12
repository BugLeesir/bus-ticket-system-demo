package com.lyr.busticketsystemdemo.model.dto;

import lombok.Data;

/**
 * 订单更新DTO
 *
 * @author yunruili
 * @date 2025/04/10/03:57
 **/
@Data
public class OrderUpdateDTO {

    /**
     * 订单详情ID
     */
    private String detailId;

    /**
     * 订单ID
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
