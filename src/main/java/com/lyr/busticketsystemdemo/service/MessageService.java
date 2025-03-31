package com.lyr.busticketsystemdemo.service;

import com.github.pagehelper.PageInfo;
import com.lyr.busticketsystemdemo.domain.Message;
import com.baomidou.mybatisplus.extension.service.IService;
import com.lyr.busticketsystemdemo.model.dto.MessageDTO;
import com.lyr.busticketsystemdemo.model.dto.MessageSearchDTO;
import com.lyr.busticketsystemdemo.model.dto.MessageUpdateDTO;
import com.lyr.busticketsystemdemo.model.vo.MessageSearchVO;

import java.util.List;

/**
* @author yunruili
* @description 针对表【message(留言信息表)】的数据库操作Service
* @createDate 2025-03-13 18:34:26
*/
public interface MessageService extends IService<Message> {
    /**
     * 添加留言
     * @param messageDTO
     * @return
     */
    boolean addMessage(MessageDTO messageDTO);

    /**
     * 删除留言
     * @param messageId
     * @return
     */
    boolean deleteMessage(Long messageId);

    /**
     * 批量删除留言
     * @param messageIds
     * @return
     */
    boolean batchDeleteMessage(List<Long> messageIds);

    /**
     * 更新留言信息
     * @param messageUpdateDTO
     * @return
     */
    boolean updateMessage(MessageUpdateDTO messageUpdateDTO);

    /**
     * 留言查询
     * @param messageSearchDTO
     * @return
     */
    PageInfo<MessageSearchVO> searchMessages(MessageSearchDTO messageSearchDTO);

}
