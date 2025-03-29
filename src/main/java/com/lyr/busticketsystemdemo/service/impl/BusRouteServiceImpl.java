package com.lyr.busticketsystemdemo.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.github.yitter.idgen.YitIdHelper;
import com.lyr.busticketsystemdemo.domain.BusRoute;
import com.lyr.busticketsystemdemo.model.dto.BusRouteDTO;
import com.lyr.busticketsystemdemo.model.dto.BusRouteSearchDTO;
import com.lyr.busticketsystemdemo.model.dto.BusRouteUpdateDTO;
import com.lyr.busticketsystemdemo.model.vo.BusRouteSearchVO;
import com.lyr.busticketsystemdemo.service.BusRouteService;
import com.lyr.busticketsystemdemo.dao.mapper.BusRouteMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

/**
* @author yunruili
* @description 针对表【bus_route(汽车车次信息表)】的数据库操作Service实现
* @createDate 2025-03-13 18:34:26
*/
@Service
public class BusRouteServiceImpl extends ServiceImpl<BusRouteMapper, BusRoute>
    implements BusRouteService{

    @Autowired
    private BusRouteMapper busRouteMapper;

    @Override
    public boolean checkHasBusRoute(String busRouteName) {
        return busRouteMapper.getBusRouteByBusRouteName(busRouteName) != null;
    }

    @Override
    public boolean addBusRoute(BusRouteDTO busRouteDTO) {
        // 将BusRouteDTO转换为BusRoute实体
        BusRoute busRoute = new BusRoute();
        busRoute.setRouteId(YitIdHelper.nextId());
        busRoute.setRouteName(busRouteDTO.getRouteName());
        busRoute.setDeparture(busRouteDTO.getDeparture());
        busRoute.setDestination(busRouteDTO.getDestination());
        busRoute.setDepartureTime(busRouteDTO.getDepartureTime());
        busRoute.setArrivalTime(busRouteDTO.getArrivalTime());
        busRoute.setPrice(new BigDecimal(busRouteDTO.getPrice()));
        busRoute.setSeatsTotal(busRouteDTO.getSeatsTotal());
        busRoute.setSeatsAvailable(busRouteDTO.getSeatsTotal());

        // 保存BusRoute实体到数据库
        return this.save(busRoute);
    }

    @Override
    public boolean deleteBusRoute(Long busRouteId) {
        // 根据id查看是否存在车次
        if(busRouteMapper.selectById(busRouteId) == null) {
            return false;
        }
        // 删除车次
        return this.removeById(busRouteId);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean batchDeleteBusRoute(List<Long> busRouteIds) {
        // 删除车次
        return this.removeByIds(busRouteIds);
    }

    @Override
    public boolean updateBusRoute(BusRouteUpdateDTO busRouteUpdateDTO) {
        // 查询此车次
        BusRoute busRoute = busRouteMapper.selectById(busRouteUpdateDTO.getRouteId());
        // 更新车次信息
        busRoute.setRouteName(busRouteUpdateDTO.getRouteName());
        busRoute.setDeparture(busRouteUpdateDTO.getDeparture());
        busRoute.setDestination(busRouteUpdateDTO.getDestination());
        busRoute.setDepartureTime(busRouteUpdateDTO.getDepartureTime());
        busRoute.setArrivalTime(busRouteUpdateDTO.getArrivalTime());
        busRoute.setPrice(new BigDecimal(busRouteUpdateDTO.getPrice()));
        busRoute.setSeatsTotal(busRouteUpdateDTO.getSeatsTotal());
        busRoute.setSeatsAvailable(busRouteUpdateDTO.getSeatsAvailable());
        return this.updateById(busRoute);
    }

    @Override
    public PageInfo<BusRouteSearchVO> searchBusRoutes(BusRouteSearchDTO busRouteSearchDTO) {
        // 开启分页
        PageHelper.startPage(busRouteSearchDTO.getPageNum(), busRouteSearchDTO.getPageSize());

        // 查询车次信息
        List<BusRoute> busRouteList = busRouteMapper.searchBusRoutes(busRouteSearchDTO);

        // 封装为PageInfo对象，确保分页数据完整
        PageInfo<BusRoute> pageInfo = new PageInfo<>(busRouteList);

        // 将BusRoute转换为BusRouteSearchVO
        List<BusRouteSearchVO> busRouteSearchVOList = pageInfo.getList().stream()
                .map(busRoute -> {
                    BusRouteSearchVO busRouteSearchVO = new BusRouteSearchVO();
                    busRouteSearchVO.setRouteId(busRoute.getRouteId().toString());
                    busRouteSearchVO.setRouteName(busRoute.getRouteName());
                    busRouteSearchVO.setDeparture(busRoute.getDeparture());
                    busRouteSearchVO.setDestination(busRoute.getDestination());
                    busRouteSearchVO.setDepartureTime(busRoute.getDepartureTime());
                    busRouteSearchVO.setArrivalTime(busRoute.getArrivalTime());
                    busRouteSearchVO.setPrice(busRoute.getPrice().toString());
                    busRouteSearchVO.setSeatsTotal(busRoute.getSeatsTotal());
                    busRouteSearchVO.setSeatsAvailable(busRoute.getSeatsAvailable());
                    return busRouteSearchVO;
                }).toList();
        // 创建新的PageInfo对象，包含转换后的数据
        PageInfo<BusRouteSearchVO> resultPageInfo = new PageInfo<>(busRouteSearchVOList);
        // 复制分页信息
        resultPageInfo.setPageNum(pageInfo.getPageNum());
        resultPageInfo.setPageSize(pageInfo.getPageSize());
        resultPageInfo.setTotal(pageInfo.getTotal());
        resultPageInfo.setPages(pageInfo.getPages());

        return resultPageInfo;
    }

}




