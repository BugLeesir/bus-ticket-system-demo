package com.lyr.busticketsystemdemo.service;

import com.github.pagehelper.PageInfo;
import com.lyr.busticketsystemdemo.domain.OrderTable;
import com.baomidou.mybatisplus.extension.service.IService;
import com.lyr.busticketsystemdemo.model.dto.OrderDTO;
import com.lyr.busticketsystemdemo.model.dto.OrderSearchDTO;
import com.lyr.busticketsystemdemo.model.dto.OrderUpdateDTO;
import com.lyr.busticketsystemdemo.model.vo.OrderSearchVO;

import java.util.List;

/**
* @author yunruili
* @description 针对表【order_table(订单信息表)】的数据库操作Service
* @createDate 2025-03-13 18:34:26
*/
public interface OrderTableService extends IService<OrderTable> {

    /**
     * 添加订单
     * @param orderDTO
     * @return
     */
    Long addOrder(OrderDTO orderDTO);

    /**
     * 支付订单
     * @param orderId
     * @return
     */
    boolean payOrder(Long orderId);

    /**
     * 取消订单
     * @param orderId
     * @return
     */
    boolean cancelOrder(Long orderId);

    /**
     * 重置订单
     * @param orderId
     * @return
     */
    boolean resetOrder(Long orderId);

    /**
     * 更新订单
     * @param orderUpdateDTO
     * @return
     */
    Integer updateOrder(OrderUpdateDTO orderUpdateDTO);

    /**
     * 删除订单
     * @param orderId
     * @return
     */
    boolean deleteOrder(Long orderId);

    /**
     * 批量删除订单
     * @param orderIds
     * @return
     */
    boolean batchDeleteOrder(List<Long> orderIds);

    /**
     * 查询订单
     * @param orderId
     * @return
     */
    OrderSearchVO searchOrderById(Long orderId);

    /**
     * 查询订单
     * @param orderSearchDTO
     * @return
     */
    PageInfo<OrderSearchVO> searchOrders(OrderSearchDTO orderSearchDTO);
}
