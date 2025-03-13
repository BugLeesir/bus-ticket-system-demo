package com.lyr.busticketsystemdemo.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lyr.busticketsystemdemo.domain.OrderTable;
import com.lyr.busticketsystemdemo.service.OrderTableService;
import com.lyr.busticketsystemdemo.dao.mapper.OrderTableMapper;
import org.springframework.stereotype.Service;

/**
* @author yunruili
* @description 针对表【order_table(订单信息表)】的数据库操作Service实现
* @createDate 2025-03-13 18:34:26
*/
@Service
public class OrderTableServiceImpl extends ServiceImpl<OrderTableMapper, OrderTable>
    implements OrderTableService{

}




