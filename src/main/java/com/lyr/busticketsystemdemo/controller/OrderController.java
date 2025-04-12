package com.lyr.busticketsystemdemo.controller;

import cn.dev33.satoken.util.SaResult;
import com.github.pagehelper.PageInfo;
import com.lyr.busticketsystemdemo.model.dto.OrderBatchDeleteDTO;
import com.lyr.busticketsystemdemo.model.dto.OrderDTO;
import com.lyr.busticketsystemdemo.model.dto.OrderSearchDTO;
import com.lyr.busticketsystemdemo.model.dto.OrderUpdateDTO;
import com.lyr.busticketsystemdemo.model.vo.OrderSearchVO;
import com.lyr.busticketsystemdemo.service.OrderDetailsService;
import com.lyr.busticketsystemdemo.service.OrderTableService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 订单控制器
 *
 * @author yunruili
 * @date 2025/03/31/18:56
 **/
@RestController
@RequestMapping("/order")
public class OrderController {
    private static final Logger LOGGER = LoggerFactory.getLogger(OrderController.class);

    @Autowired
    private OrderTableService orderTableService;

    @Autowired
    private OrderDetailsService orderDetailsService;

    @PostMapping("/add")
    public SaResult add(@RequestBody OrderDTO orderDTO) {
        Long orderId = orderTableService.addOrder(orderDTO);
        if(orderId > 0) {
            return SaResult.ok("添加成功").setData(orderId);
        } else {
            return SaResult.error("余票不足");
        }
    }

    @GetMapping("/searchOrder")
    public OrderSearchVO searchOrder(@RequestParam String orderId) {
        return orderTableService.searchOrderById(Long.valueOf(orderId));
    }

    @PostMapping("/search")
    public PageInfo<OrderSearchVO> searchOrders(@RequestBody OrderSearchDTO orderSearchDTO) {
        return orderTableService.searchOrders(orderSearchDTO);
    }

    @PutMapping("/payOrder")
    public SaResult payOrder(@RequestParam String orderId) {
        return orderTableService.payOrder(Long.valueOf(orderId)) ? SaResult.ok("支付成功") : SaResult.error("支付失败");
    }

    @PutMapping("/cancelOrder")
    public SaResult cancelOrder(@RequestParam String orderId) {
        return orderTableService.cancelOrder(Long.valueOf(orderId)) ? SaResult.ok("取消成功") : SaResult.error("取消失败");
    }

    @PutMapping("/resetOrder")
    public SaResult resetOrder(@RequestParam String orderId) {
        return orderTableService.resetOrder(Long.valueOf(orderId)) ? SaResult.ok("重置成功") : SaResult.error("重置失败");
    }

    @PutMapping("/update")
    public SaResult update(@RequestBody OrderUpdateDTO orderUpdateDTO) {
        int  result = orderTableService.updateOrder(orderUpdateDTO);
        if(result > 0) {
            return SaResult.ok("修改成功");
        } else if(result == -1) {
            return SaResult.error("座位号已被占用");
        } else {
            return SaResult.error("修改失败");
        }
    }

    @DeleteMapping("/delete")
    public SaResult delete(@RequestParam String id) {
        return orderTableService.deleteOrder(Long.valueOf(id)) ? SaResult.ok("删除成功") : SaResult.error("删除失败");
    }

    @DeleteMapping("/batchDelete")
    public SaResult batchDelete(@RequestBody OrderBatchDeleteDTO orderBatchDeleteDTO) {
        List<String> ids = orderBatchDeleteDTO.getIds();
        // id不能为空
        if (ids == null || ids.isEmpty()) {
            return SaResult.error("id不能为空");
        }
        // 将String类型的id转换为Long类型
        List<Long> orderIds = ids.stream().map(Long::valueOf).toList();
        // 批量删除留言
        return orderTableService.batchDeleteOrder(orderIds) ? SaResult.ok("批量删除成功") : SaResult.error("批量删除失败");
    }

}
