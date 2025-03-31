package com.lyr.busticketsystemdemo.model.vo;

import lombok.Data;

import java.util.List;

/**
 * 留言查询VO
 *
 * @author yunruili
 * @date 2025/03/30/20:16
 **/
@Data
public class MessageSearchVO {
    /**
     * 留言ID（主键）
     */
    private String messageId;

    /**
     * 留言用户ID
     */
    private String userId;

    /**
     * 留言用户名称
     */
    private String userName;

    /**
     * 留言内容
     */
    private String content;

    /**
     * 回复的留言ID（可为空）
     */
    private String replyId;

    /**
     * 留言时间
     */
    private String createTime;

    /**
     * 留言回复列表
     */
    private List<MessageSearchVO> replyList;

}
