package com.lyr.busticketsystemdemo.model.dto;

import lombok.Data;

import java.util.Date;

/**
 * 车次信息更新DTO
 *
 * @author yunruili
 * @date 2025/03/28/18:45
 **/
@Data
public class BusRouteUpdateDTO {
    /**
     * 车次id
     */
    private String routeId;

    /**
     * 车次名称
     */
    private String routeName;

    /**
     * 出发地
     */
    private String departure;

    /**
     * 目的地
     */
    private String destination;

    /**
     * 出发时间
     */
    private Date departureTime;

    /**
     * 到达时间
     */
    private Date arrivalTime;

    /**
     * 价格
     */
    private String price;

    /**
     * 座位总数
     */
    private Integer seatsTotal;

    /**
     * 剩余座位数
     */
    private Integer seatsAvailable;

}
