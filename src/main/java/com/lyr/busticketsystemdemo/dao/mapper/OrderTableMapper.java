package com.lyr.busticketsystemdemo.dao.mapper;

import com.lyr.busticketsystemdemo.domain.OrderTable;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lyr.busticketsystemdemo.model.dto.OrderSearchDTO;
import com.lyr.busticketsystemdemo.model.vo.OrderSearchVO;

import java.util.List;

/**
* @author yunruili
* @description 针对表【order_table(订单信息表)】的数据库操作Mapper
* @createDate 2025-03-13 18:34:26
* @Entity com.lyr.busticketsystemdemo.domain.OrderTable
*/
public interface OrderTableMapper extends BaseMapper<OrderTable> {

    List<OrderSearchVO> searchOrders(OrderSearchDTO orderSearchDTO);

    OrderSearchVO searchOrderById(Long orderId);
}




