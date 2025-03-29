package com.lyr.busticketsystemdemo.model.dto;

import lombok.Data;

/**
 * 公告更新DTO
 *
 * @author yunruili
 * @date 2025/03/29/19:55
 **/
@Data
public class AnnouncementUpdateDTO {

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

}
