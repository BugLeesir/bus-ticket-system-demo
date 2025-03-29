package com.lyr.busticketsystemdemo.model.dto;

import lombok.Data;

import java.util.Date;

/**
 * 公告搜索DTO
 * @author yunruili
 * @date 2025/03/29/19:54
 **/
@Data
public class AnnouncementSearchDTO {

    /**
     * 公告标题
     */
    private String title;

    /**
     * 发布时间
     */
    private String publishTime;

    /**
     * 当前页
     */
    private Integer pageNum;

    /**
     * 每页显示数量
     */
    private Integer pageSize;

}
