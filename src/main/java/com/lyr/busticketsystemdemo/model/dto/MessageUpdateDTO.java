package com.lyr.busticketsystemdemo.model.dto;

import lombok.Data;

/**
 * 留言更新DTO
 *
 * @author yunruili
 * @date 2025/03/30/20:38
 **/
@Data
public class MessageUpdateDTO {

    /**
     * 留言ID
     */
    private String messageId;

    /**
     * 留言内容
     */
    private String content;

}
