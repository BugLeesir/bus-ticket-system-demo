package com.lyr.busticketsystemdemo.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 * 订单详情表
 * @TableName order_details
 */
@TableName(value ="order_details")
@Data
public class OrderDetails implements Serializable {
    /**
     * 订单详情ID（主键）
     */
    @TableId(type = IdType.AUTO)
    private Long detailId;

    /**
     * 关联订单ID
     */
    private Long orderId;

    /**
     * 乘客姓名
     */
    private String passengerName;

    /**
     * 座位号
     */
    private String seatNumber;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        OrderDetails other = (OrderDetails) that;
        return (this.getDetailId() == null ? other.getDetailId() == null : this.getDetailId().equals(other.getDetailId()))
            && (this.getOrderId() == null ? other.getOrderId() == null : this.getOrderId().equals(other.getOrderId()))
            && (this.getPassengerName() == null ? other.getPassengerName() == null : this.getPassengerName().equals(other.getPassengerName()))
            && (this.getSeatNumber() == null ? other.getSeatNumber() == null : this.getSeatNumber().equals(other.getSeatNumber()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getDetailId() == null) ? 0 : getDetailId().hashCode());
        result = prime * result + ((getOrderId() == null) ? 0 : getOrderId().hashCode());
        result = prime * result + ((getPassengerName() == null) ? 0 : getPassengerName().hashCode());
        result = prime * result + ((getSeatNumber() == null) ? 0 : getSeatNumber().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", detailId=").append(detailId);
        sb.append(", orderId=").append(orderId);
        sb.append(", passengerName=").append(passengerName);
        sb.append(", seatNumber=").append(seatNumber);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}