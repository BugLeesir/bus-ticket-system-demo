package com.lyr.busticketsystemdemo.model.vo;

import lombok.Data;

import java.util.List;

/**
 * 订单查询VO
 *
 * @author yunruili
 * @date 2025/04/10/03:53
 **/
@Data
public class OrderSearchVO {
    /**
     * 订单ID（主键）
     */
    private String orderId;

    /**
     * 下单用户ID
     */
    private String userId;

    /**
     * 下单用户名称
     */
    private String username;

    /**
     * 关联车次ID
     */
    private String routeId;

    /**
     * 关联车次名称
     */
    private String routeName;

    /**
     * 订单状态（待支付:0/已支付:1/已取消:2）
     */
    private String orderStatus;

    /**
     * 订单创建时间
     */
    private String createTime;

    /**
     * 支付时间
     */
    private String paymentTime;

    /**
     * 订单详情
     */
    List<OrderDetailSearchVO> orderDetailList;
}
