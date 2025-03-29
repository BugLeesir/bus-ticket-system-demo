package com.lyr.busticketsystemdemo.service;

import com.github.pagehelper.PageInfo;
import com.lyr.busticketsystemdemo.domain.BusRoute;
import com.baomidou.mybatisplus.extension.service.IService;
import com.lyr.busticketsystemdemo.model.dto.BusRouteDTO;
import com.lyr.busticketsystemdemo.model.dto.BusRouteSearchDTO;
import com.lyr.busticketsystemdemo.model.dto.BusRouteUpdateDTO;
import com.lyr.busticketsystemdemo.model.vo.BusRouteSearchVO;

import java.util.List;

/**
* @author yunruili
* @description 针对表【bus_route(汽车车次信息表)】的数据库操作Service
* @createDate 2025-03-13 18:34:26
*/
public interface BusRouteService extends IService<BusRoute> {
    /**
     * 检查是否存在车次
     * @param busRouteName
     * @return
     */
    boolean checkHasBusRoute(String busRouteName);

    /**
     * 添加车次
     * @param busRouteDTO
     * @return
     */
    boolean addBusRoute(BusRouteDTO busRouteDTO);

    /**
     * 删除车次
     * @param busRouteId
     * @return
     */
    boolean deleteBusRoute(Long busRouteId);

    /**
     * 批量删除车次
     * @param busRouteIds
     * @return
     */
    boolean batchDeleteBusRoute(List<Long> busRouteIds);

    /**
     * 更新车次信息
     * @param busRouteUpdateDTO
     * @return
     */
    boolean updateBusRoute(BusRouteUpdateDTO busRouteUpdateDTO);

    /**
     * 车次查询
     * @param busRouteSearchDTO
     * @return
     */
    PageInfo<BusRouteSearchVO> searchBusRoutes(BusRouteSearchDTO busRouteSearchDTO);
}
