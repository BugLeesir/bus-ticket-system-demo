package com.lyr.busticketsystemdemo.dao.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.lyr.busticketsystemdemo.domain.OrderDetails;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
* @author yunruili
* @description 针对表【order_details(订单详情表)】的数据库操作Mapper
* @createDate 2025-03-13 18:34:26
* @Entity com.lyr.busticketsystemdemo.domain.OrderDetails
*/
public interface OrderDetailsMapper extends BaseMapper<OrderDetails> {

    default List<OrderDetails> selectByOrderId(Long orderId) {
        return this.selectList(new QueryWrapper<OrderDetails>().eq("order_id", orderId));
    }

    default void deleteByOrderId(Long orderId) {
        this.delete(new QueryWrapper<OrderDetails>().eq("order_id", orderId));
    }

    List<String> selectOccupiedSeatsByRouteId(Long busRouteId);
}




