package com.lyr.busticketsystemdemo.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lyr.busticketsystemdemo.domain.BusRoute;
import com.lyr.busticketsystemdemo.service.BusRouteService;
import com.lyr.busticketsystemdemo.dao.mapper.BusRouteMapper;
import org.springframework.stereotype.Service;

/**
* @author yunruili
* @description 针对表【bus_route(汽车车次信息表)】的数据库操作Service实现
* @createDate 2025-03-13 18:34:26
*/
@Service
public class BusRouteServiceImpl extends ServiceImpl<BusRouteMapper, BusRoute>
    implements BusRouteService{

}




