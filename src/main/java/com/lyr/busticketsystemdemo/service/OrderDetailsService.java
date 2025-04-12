package com.lyr.busticketsystemdemo.service;

import com.lyr.busticketsystemdemo.domain.OrderDetails;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
* @author yunruili
* @description 针对表【order_details(订单详情表)】的数据库操作Service
* @createDate 2025-03-13 18:34:26
*/
public interface OrderDetailsService extends IService<OrderDetails> {

    /**
     * 添加订单详情
     *
     * @param passengerNames 乘客姓名列表
     */
    void addOrderDetails(List<String> passengerNames , Long orderId , Long busRouteId);

    /**
     * 根据订单ID清除订单详情
     *
     * @param orderId 订单ID
     */
    void deleteOrderDetails(Long orderId);

}
