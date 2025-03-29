package com.lyr.busticketsystemdemo.model.dto;

import lombok.Data;

/**
 * 车次信息查询DTO
 *
 * @author yunruili
 * @date 2025/03/28/18:37
 **/
@Data
public class BusRouteSearchDTO {
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
    private String departureTime;

    /**
     * 到达时间
     */
    private String arrivalTime;

    /**
     * 价格下限
     */
    private String priceLower;

    /**
     * 价格上限
     */
    private String priceUpper;

    /**
     * 当前页
     */
    private Integer pageNum;

    /**
     * 每页显示数量
     */
    private Integer pageSize;

}
