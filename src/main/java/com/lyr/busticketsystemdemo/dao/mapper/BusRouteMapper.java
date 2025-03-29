package com.lyr.busticketsystemdemo.dao.mapper;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.lyr.busticketsystemdemo.domain.BusRoute;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lyr.busticketsystemdemo.model.dto.BusRouteSearchDTO;

import java.util.List;

/**
* @author yunruili
* @description 针对表【bus_route(汽车车次信息表)】的数据库操作Mapper
* @createDate 2025-03-13 18:34:26
* @Entity com.lyr.busticketsystemdemo.domain.BusRoute
*/
public interface BusRouteMapper extends BaseMapper<BusRoute> {

    default BusRoute getBusRouteByBusRouteName(String busRouteName) {
        return this.selectOne(Wrappers.<BusRoute>query().lambda()
                .eq(BusRoute::getRouteName, busRouteName)
        );
    }

    List<BusRoute> searchBusRoutes(BusRouteSearchDTO  busRouteSearchDTO);
}




