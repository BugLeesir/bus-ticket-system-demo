package com.lyr.busticketsystemdemo.model.dto;

import lombok.Data;

import java.util.List;

/**
 * 留言查询DTO
 *
 * @author yunruili
 * @date 2025/03/30/20:09
 **/
@Data
public class MessageSearchDTO {

    /**
     * 留言用户名
     */
    private String username;

    /**
     * 留言创建时间
     */
    private List<String> createTime;

    /**
     * 当前页
     */
    private Integer pageNum;

    /**
     * 每页显示数量
     */
    private Integer pageSize;

}
