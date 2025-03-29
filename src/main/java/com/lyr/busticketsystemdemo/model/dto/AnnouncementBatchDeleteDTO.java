package com.lyr.busticketsystemdemo.model.dto;

import lombok.Data;

import java.util.List;

/**
 * 公告信息批量删除DTO
 *
 * @author yunruili
 * @date 2025/03/29/20:01
 **/
@Data
public class AnnouncementBatchDeleteDTO {
    /**
     * 公告id列表
     */
    private List<String> ids;
}
