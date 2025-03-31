package com.lyr.busticketsystemdemo.model.dto;

import lombok.Data;

/**
 * 留言DTO
 *
 * @author yunruili
 * @date 2025/03/30/19:59
 **/
@Data
public class MessageDTO {

    /**
     * 留言用户id
     */
    private String userId;

    /**
     * 留言内容
     */
    private String content;

    /**
     * 回复的留言ID（可为空）
     */
    private String replyId;

}
