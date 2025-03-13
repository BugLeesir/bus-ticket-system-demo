package com.lyr.busticketsystemdemo.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lyr.busticketsystemdemo.domain.OrderDetails;
import com.lyr.busticketsystemdemo.service.OrderDetailsService;
import com.lyr.busticketsystemdemo.dao.mapper.OrderDetailsMapper;
import org.springframework.stereotype.Service;

/**
* @author yunruili
* @description 针对表【order_details(订单详情表)】的数据库操作Service实现
* @createDate 2025-03-13 18:34:26
*/
@Service
public class OrderDetailsServiceImpl extends ServiceImpl<OrderDetailsMapper, OrderDetails>
    implements OrderDetailsService{

}




