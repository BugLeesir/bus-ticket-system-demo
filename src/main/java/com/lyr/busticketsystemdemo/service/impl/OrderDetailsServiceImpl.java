package com.lyr.busticketsystemdemo.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.yitter.idgen.YitIdHelper;
import com.lyr.busticketsystemdemo.dao.mapper.BusRouteMapper;
import com.lyr.busticketsystemdemo.dao.mapper.OrderTableMapper;
import com.lyr.busticketsystemdemo.domain.OrderDetails;
import com.lyr.busticketsystemdemo.service.OrderDetailsService;
import com.lyr.busticketsystemdemo.dao.mapper.OrderDetailsMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
* @author yunruili
* @description 针对表【order_details(订单详情表)】的数据库操作Service实现
* @createDate 2025-03-13 18:34:26
*/
@Service
public class OrderDetailsServiceImpl extends ServiceImpl<OrderDetailsMapper, OrderDetails>
    implements OrderDetailsService{

    @Autowired
    private OrderDetailsMapper orderDetailsMapper;

    @Autowired
    private OrderTableMapper orderTableMapper;

    @Autowired
    private BusRouteMapper busRouteMapper;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void addOrderDetails(List<String> passengerNames , Long orderId , Long busRouteId) {
        // 查询已占用的座位号
        List<String> occupiedSeats = orderDetailsMapper.selectOccupiedSeatsByRouteId(busRouteId);

        // 获取车次的总座位数
        int totalSeats = busRouteMapper.selectById(busRouteId).getSeatsTotal();

        // 生成所有座位号
        List<String> allSeats = new ArrayList<>();
        for (int i = 1; i <= totalSeats; i++) {
            allSeats.add(String.valueOf(i));
        }

        // 找到可用座位号
        allSeats.removeAll(occupiedSeats);
        if (allSeats.size() < passengerNames.size()) {
            throw new RuntimeException("可用座位不足");
        }

        // 为每位乘客分配座位号并插入订单详情
        for (int i = 0; i < passengerNames.size(); i++) {
            String seatNumber = allSeats.get(i);
            OrderDetails orderDetails = new OrderDetails();
            orderDetails.setDetailId(YitIdHelper.nextId());
            orderDetails.setOrderId(orderId);
            orderDetails.setPassengerName(passengerNames.get(i));
            orderDetails.setSeatNumber(seatNumber);
            orderDetailsMapper.insert(orderDetails);
        }

        // 更新车次的可用座位数
        busRouteMapper.updateSeatsAvailable(busRouteId, -passengerNames.size());

    }

    @Override
    public void deleteOrderDetails(Long orderId) {
        // 如果有订单详情，释放座位
        List<OrderDetails> orderDetailsList = orderDetailsMapper.selectByOrderId(orderId);
        if (orderDetailsList != null && !orderDetailsList.isEmpty()) {
            // 获取订单详情中的座位号
            List<String> seatNumbers = new ArrayList<>();
            for (OrderDetails orderDetails : orderDetailsList) {
                seatNumbers.add(orderDetails.getSeatNumber());
            }

            // 更新车次的可用座位数
            Long busRouteId = orderTableMapper.selectById(orderId).getRouteId();
            busRouteMapper.updateSeatsAvailable(busRouteId, seatNumbers.size());

            // 删除订单详情
            orderDetailsMapper.deleteByOrderId(orderId);
        }
    }
}




