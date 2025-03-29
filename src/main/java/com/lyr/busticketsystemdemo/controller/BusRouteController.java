package com.lyr.busticketsystemdemo.controller;

import cn.dev33.satoken.util.SaResult;
import com.github.pagehelper.PageInfo;
import com.lyr.busticketsystemdemo.model.dto.BusRouteBatchDeleteDTO;
import com.lyr.busticketsystemdemo.model.dto.BusRouteDTO;
import com.lyr.busticketsystemdemo.model.dto.BusRouteSearchDTO;
import com.lyr.busticketsystemdemo.model.dto.BusRouteUpdateDTO;
import com.lyr.busticketsystemdemo.model.vo.BusRouteSearchVO;
import com.lyr.busticketsystemdemo.service.BusRouteService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 车次信息管理控制器
 *
 * @author yunruili
 * @date 2025/03/28/18:29
 **/
@RestController
@RequestMapping("/busRoute")
public class BusRouteController {
    private static final Logger LOGGER = LoggerFactory.getLogger(BusRouteController.class);

    @Autowired
    private BusRouteService busRouteService;

    @PostMapping("/add")
    public SaResult add(@RequestBody BusRouteDTO busRouteDTO){
        // 检查车次是否存在
        if(busRouteService.checkHasBusRoute(busRouteDTO.getRouteName())){
            return SaResult.error("车次已存在");
        }
        // 添加车次
        boolean added = busRouteService.addBusRoute(busRouteDTO);
        if(added) {
            return SaResult.ok("添加成功");
        }else {
            return SaResult.error("添加失败");
        }
    }

    @PostMapping("/search")
    public PageInfo<BusRouteSearchVO> search(@RequestBody BusRouteSearchDTO busRouteSearchDTO){
        return busRouteService.searchBusRoutes(busRouteSearchDTO);
    }

    @DeleteMapping("/delete")
    public SaResult delete(@RequestParam String id){
        boolean result = busRouteService.deleteBusRoute(Long.valueOf(id));
        if(result) {
            return SaResult.ok("删除成功");
        }else {
            return SaResult.error("删除失败");
        }
    }

    @DeleteMapping("/batchDelete")
    public SaResult batchDelete(@RequestBody BusRouteBatchDeleteDTO busRouteBatchDeleteDTO) {
        List<String> ids = busRouteBatchDeleteDTO.getIds();
        // id不能为空
        if (ids == null || ids.isEmpty()) {
            return SaResult.error("id不能为空");
        }
        // 将String类型的id转换为Long类型
        List<Long> busRouteIds = ids.stream().map(Long::valueOf).toList();
        // 批量删除车次
        boolean result = busRouteService.batchDeleteBusRoute(busRouteIds);
        if (result) {
            return SaResult.ok("批量删除成功");
        } else {
            return SaResult.error("批量删除失败");
        }
    }

    @PutMapping("/update")
    public SaResult update(@RequestBody BusRouteUpdateDTO busRouteUpdateDTO){
        boolean result = busRouteService.updateBusRoute(busRouteUpdateDTO);
        if(result) {
            return SaResult.ok("更新成功");
        }else {
            return SaResult.error("更新失败");
        }
    }
}
