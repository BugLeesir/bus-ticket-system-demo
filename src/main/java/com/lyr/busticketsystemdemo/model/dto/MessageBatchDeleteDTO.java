package com.lyr.busticketsystemdemo.model.dto;

import lombok.Data;

import java.util.List;

/**
 * 留言批量删除DTO
 *
 * @author yunruili
 * @date 2025/03/30/20:43
 **/
@Data
public class MessageBatchDeleteDTO {

    /**
     * 留言ID列表
     */
    private List<String> ids;
}
