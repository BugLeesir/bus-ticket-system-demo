package com.lyr.busticketsystemdemo.model.vo;

import lombok.Data;

import java.util.Date;

/**
 * 公告信息查询VO
 *
 * @author yunruili
 * @date 2025/03/29/19:57
 **/
@Data
public class AnnouncementSearchVO {
    /**
     * 公告ID（主键）
     */
    private Long announcementId;

    /**
     * 公告标题
     */
    private String title;

    /**
     * 公告内容
     */
    private String content;

    /**
     * 发布时间
     */
    private Date publishTime;

}
