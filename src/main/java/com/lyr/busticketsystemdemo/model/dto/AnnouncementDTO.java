package com.lyr.busticketsystemdemo.model.dto;

import lombok.Data;

/**
 * 公告信息DTO
 *
 * @author yunruili
 * @date 2025/03/29/19:52
 **/
@Data
public class AnnouncementDTO {

    /**
     * 公告标题
     */
    private String title;

    /**
     * 公告内容
     */
    private String content;

}
