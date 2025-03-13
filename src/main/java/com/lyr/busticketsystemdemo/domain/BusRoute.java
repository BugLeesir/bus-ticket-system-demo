package com.lyr.busticketsystemdemo.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import lombok.Data;

/**
 * 汽车车次信息表
 * @TableName bus_route
 */
@TableName(value ="bus_route")
@Data
public class BusRoute implements Serializable {
    /**
     * 车次ID（主键）
     */
    @TableId(type = IdType.AUTO)
    private Long routeId;

    /**
     * 车次名称
     */
    private String routeName;

    /**
     * 出发地
     */
    private String departure;

    /**
     * 目的地
     */
    private String destination;

    /**
     * 发车时间
     */
    private Date departureTime;

    /**
     * 到达时间
     */
    private Date arrivalTime;

    /**
     * 票价
     */
    private BigDecimal price;

    /**
     * 总座位数
     */
    private Integer seatsTotal;

    /**
     * 可用座位数
     */
    private Integer seatsAvailable;

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
        BusRoute other = (BusRoute) that;
        return (this.getRouteId() == null ? other.getRouteId() == null : this.getRouteId().equals(other.getRouteId()))
            && (this.getRouteName() == null ? other.getRouteName() == null : this.getRouteName().equals(other.getRouteName()))
            && (this.getDeparture() == null ? other.getDeparture() == null : this.getDeparture().equals(other.getDeparture()))
            && (this.getDestination() == null ? other.getDestination() == null : this.getDestination().equals(other.getDestination()))
            && (this.getDepartureTime() == null ? other.getDepartureTime() == null : this.getDepartureTime().equals(other.getDepartureTime()))
            && (this.getArrivalTime() == null ? other.getArrivalTime() == null : this.getArrivalTime().equals(other.getArrivalTime()))
            && (this.getPrice() == null ? other.getPrice() == null : this.getPrice().equals(other.getPrice()))
            && (this.getSeatsTotal() == null ? other.getSeatsTotal() == null : this.getSeatsTotal().equals(other.getSeatsTotal()))
            && (this.getSeatsAvailable() == null ? other.getSeatsAvailable() == null : this.getSeatsAvailable().equals(other.getSeatsAvailable()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getRouteId() == null) ? 0 : getRouteId().hashCode());
        result = prime * result + ((getRouteName() == null) ? 0 : getRouteName().hashCode());
        result = prime * result + ((getDeparture() == null) ? 0 : getDeparture().hashCode());
        result = prime * result + ((getDestination() == null) ? 0 : getDestination().hashCode());
        result = prime * result + ((getDepartureTime() == null) ? 0 : getDepartureTime().hashCode());
        result = prime * result + ((getArrivalTime() == null) ? 0 : getArrivalTime().hashCode());
        result = prime * result + ((getPrice() == null) ? 0 : getPrice().hashCode());
        result = prime * result + ((getSeatsTotal() == null) ? 0 : getSeatsTotal().hashCode());
        result = prime * result + ((getSeatsAvailable() == null) ? 0 : getSeatsAvailable().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", routeId=").append(routeId);
        sb.append(", routeName=").append(routeName);
        sb.append(", departure=").append(departure);
        sb.append(", destination=").append(destination);
        sb.append(", departureTime=").append(departureTime);
        sb.append(", arrivalTime=").append(arrivalTime);
        sb.append(", price=").append(price);
        sb.append(", seatsTotal=").append(seatsTotal);
        sb.append(", seatsAvailable=").append(seatsAvailable);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}