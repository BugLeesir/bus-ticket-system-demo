package com.lyr.busticketsystemdemo.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.github.yitter.idgen.YitIdHelper;
import com.lyr.busticketsystemdemo.dao.mapper.BusRouteMapper;
import com.lyr.busticketsystemdemo.dao.mapper.OrderDetailsMapper;
import com.lyr.busticketsystemdemo.domain.OrderDetails;
import com.lyr.busticketsystemdemo.domain.OrderTable;
import com.lyr.busticketsystemdemo.model.dto.OrderDTO;
import com.lyr.busticketsystemdemo.model.dto.OrderSearchDTO;
import com.lyr.busticketsystemdemo.model.dto.OrderUpdateDTO;
import com.lyr.busticketsystemdemo.model.vo.OrderDetailSearchVO;
import com.lyr.busticketsystemdemo.model.vo.OrderSearchVO;
import com.lyr.busticketsystemdemo.service.OrderDetailsService;
import com.lyr.busticketsystemdemo.service.OrderTableService;
import com.lyr.busticketsystemdemo.dao.mapper.OrderTableMapper;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
* @author yunruili
* @description 针对表【order_table(订单信息表)】的数据库操作Service实现
* @createDate 2025-03-13 18:34:26
*/
@Service
public class OrderTableServiceImpl extends ServiceImpl<OrderTableMapper, OrderTable>
    implements OrderTableService{

    @Autowired
    private OrderTableMapper orderTableMapper;

    @Autowired
    private OrderDetailsMapper orderDetailsMapper;

    @Autowired
    private BusRouteMapper busRouteMapper;

    @Autowired
    private OrderDetailsService orderDetailsService;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Long addOrder(OrderDTO orderDTO) {

        // 计算需要的票数
        int ticketCount = orderDTO.getPassengerNames().size();

        // 计算剩余票数
        int availableTickets = busRouteMapper.selectById(orderDTO.getRouteId()).getSeatsAvailable();

        // 判断是否有足够的票 -1: 余票不足
        if (availableTickets < ticketCount) {
            return -1L;
        }

        // 生成订单

        OrderTable orderTable = new OrderTable();
        Long orderId = YitIdHelper.nextId();
        orderTable.setOrderId(orderId);
        orderTable.setUserId(Long.valueOf(orderDTO.getUserId()));
        Long busRouteId = Long.valueOf(orderDTO.getRouteId());
        orderTable.setRouteId(busRouteId);
        // 设置订单状态为未支付
        orderTable.setOrderStatus(0);
        this.save(orderTable);

        // 生成订单详情
        orderDetailsService.addOrderDetails(orderDTO.getPassengerNames(),orderId,busRouteId);

        return orderId;
    }

    @Override
    public boolean payOrder(Long orderId) {
        OrderTable orderTable = orderTableMapper.selectById(orderId);
        // 变更订单状态为已支付
        orderTable.setOrderStatus(1);
        orderTable.setPaymentTime(java.sql.Timestamp.valueOf(LocalDateTime.now()));
        // 更新订单
        return orderTableMapper.updateById(orderTable) > 0;
    }

    @Override
    public boolean cancelOrder(Long orderId) {
        OrderTable orderTable = orderTableMapper.selectById(orderId);
        // 变更订单状态为已取消
        orderTable.setOrderStatus(2);
        // 清除订单详情
        orderDetailsService.deleteOrderDetails(orderId);
        // 更新订单
        return orderTableMapper.updateById(orderTable) > 0;
    }

    @Override
    public boolean resetOrder(Long orderId) {
        OrderTable orderTable = orderTableMapper.selectById(orderId);
        // 变更订单状态为待支付
        orderTable.setOrderStatus(0);
        // 清除支付时间
        orderTable.setPaymentTime(null);
        // 更新订单
        return orderTableMapper.updateById(orderTable) > 0;
    }

    @Override
    public Integer updateOrder(OrderUpdateDTO orderUpdateDTO) {
        // 查询订单详情
        OrderDetails orderDetails = orderDetailsMapper.selectById(Long.valueOf(orderUpdateDTO.getDetailId()));
        if (orderDetails == null) {
            throw new RuntimeException("订单详情不存在");
        }

        // 更新乘客姓名
        if (orderUpdateDTO.getPassengerName() != null && !orderUpdateDTO.getPassengerName().isEmpty()) {
            orderDetails.setPassengerName(orderUpdateDTO.getPassengerName());
        }

        // 更新座位号
        if (orderUpdateDTO.getSeatNumber() != null && !orderUpdateDTO.getSeatNumber().isEmpty()) {
            // 检查座位号是否已被占用
            Long busRouteId = orderTableMapper.selectById(Long.valueOf(orderUpdateDTO.getOrderId())).getRouteId();
            List<String> occupiedSeats = orderDetailsMapper.selectOccupiedSeatsByRouteId(busRouteId);
            if (occupiedSeats.contains(orderUpdateDTO.getSeatNumber())) {
                return -1;
            }
            orderDetails.setSeatNumber(orderUpdateDTO.getSeatNumber());
        }

        // 更新订单详情
        return orderDetailsMapper.updateById(orderDetails) > 0 ? 1 : 0;
    }


    @Override
    public boolean deleteOrder(Long orderId) {
        // 删除订单详情
        orderDetailsService.deleteOrderDetails(orderId);
        // 删除订单
        return this.removeById(orderId);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean batchDeleteOrder(List<Long> orderIds) {
        // 批量删除订单详情
        for (Long orderId : orderIds) {
            orderDetailsService.deleteOrderDetails(orderId);
        }
        // 批量删除订单
        return this.removeByIds(orderIds);
    }

    @Override
    public OrderSearchVO searchOrderById(Long orderId) {
        // 查询订单信息
        OrderSearchVO order = orderTableMapper.searchOrderById(orderId);
        if (order == null) {
            throw new RuntimeException("订单不存在");
        }

        // 查询订单详情
        List<OrderDetails> orderDetails = orderDetailsMapper.selectByOrderId(orderId);
        List<OrderDetailSearchVO> orderDetailSearchVOList = getOrderDetailSearchVOList(order, orderDetails);

        // 设置订单详情
        order.setOrderDetailList(orderDetailSearchVOList);

        return order;
    }

    @Override
    public PageInfo<OrderSearchVO> searchOrders(OrderSearchDTO orderSearchDTO) {
        // 开启分页
        PageHelper.startPage(orderSearchDTO.getPageNum(), orderSearchDTO.getPageSize());

        // 查询订单
        List<OrderSearchVO> orderList = orderTableMapper.searchOrders(orderSearchDTO);

        // 封装为PageInfo对象，保证分页数据完整
        PageInfo<OrderSearchVO> pageInfo = new PageInfo<>(orderList);

        // 补充订单详情
        for (OrderSearchVO order : pageInfo.getList()) {
            List<OrderDetails> orderDetails = orderDetailsMapper.selectByOrderId(Long.valueOf(order.getOrderId()));
            List<OrderDetailSearchVO> orderDetailSearchVOList = getOrderDetailSearchVOList(order, orderDetails);
            order.setOrderDetailList(orderDetailSearchVOList);
        }

        return pageInfo;
    }

    private static List<OrderDetailSearchVO> getOrderDetailSearchVOList(OrderSearchVO order, List<OrderDetails> orderDetails) {
        List<OrderDetailSearchVO> orderDetailSearchVOList = new ArrayList<>();
        for(OrderDetails detail : orderDetails) {
            OrderDetailSearchVO orderDetailSearchVO = new OrderDetailSearchVO();
            orderDetailSearchVO.setDetailId(detail.getDetailId().toString());
            orderDetailSearchVO.setSeatNumber(detail.getSeatNumber());
            orderDetailSearchVO.setPassengerName(detail.getPassengerName());
            orderDetailSearchVO.setOrderId(order.getOrderId());
            orderDetailSearchVOList.add(orderDetailSearchVO);
        }
        return orderDetailSearchVOList;
    }


}




