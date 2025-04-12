package com.lyr.busticketsystemdemo.model.dto;

import lombok.Data;

import java.util.List;

/**
 * 订单DTO
 *
 * @author yunruili
 * @date 2025/04/10/03:50
 **/
@Data
public class OrderDTO {

    /**
     * 下单用户ID
     */
    private String userId;

    /**
     * 关联车次ID
     */
    private String routeId;

    /**
     * 乘客姓名列表
     */
    private List<String> passengerNames;

}
