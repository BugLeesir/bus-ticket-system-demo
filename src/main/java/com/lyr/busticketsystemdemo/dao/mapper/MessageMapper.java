package com.lyr.busticketsystemdemo.dao.mapper;

import com.lyr.busticketsystemdemo.domain.Message;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lyr.busticketsystemdemo.model.dto.MessageSearchDTO;

import java.util.List;

/**
* @author yunruili
* @description 针对表【message(留言信息表)】的数据库操作Mapper
* @createDate 2025-03-13 18:34:26
* @Entity com.lyr.busticketsystemdemo.domain.Message
*/
public interface MessageMapper extends BaseMapper<Message> {
    List<Message> searchMessages(MessageSearchDTO messageSearchDTO);

    List<Message> selectRepliesByMessageId(Long messageId);
}




