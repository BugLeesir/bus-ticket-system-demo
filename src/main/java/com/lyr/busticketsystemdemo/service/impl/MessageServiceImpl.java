package com.lyr.busticketsystemdemo.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.github.yitter.idgen.YitIdHelper;
import com.lyr.busticketsystemdemo.dao.mapper.UserMapper;
import com.lyr.busticketsystemdemo.domain.Message;
import com.lyr.busticketsystemdemo.model.dto.MessageDTO;
import com.lyr.busticketsystemdemo.model.dto.MessageSearchDTO;
import com.lyr.busticketsystemdemo.model.dto.MessageUpdateDTO;
import com.lyr.busticketsystemdemo.model.vo.MessageSearchVO;
import com.lyr.busticketsystemdemo.service.MessageService;
import com.lyr.busticketsystemdemo.dao.mapper.MessageMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
* @author yunruili
* @description 针对表【message(留言信息表)】的数据库操作Service实现
* @createDate 2025-03-13 18:34:26
*/
@Service
public class MessageServiceImpl extends ServiceImpl<MessageMapper, Message>
    implements MessageService{

    @Autowired
    private MessageMapper messageMapper;

    @Autowired
    private UserMapper userMapper;

    @Override
    public boolean addMessage(MessageDTO messageDTO) {
        // 将MessageDTO转换为Message实体
        Message message = new Message();
        message.setMessageId(YitIdHelper.nextId());
        message.setContent(messageDTO.getContent());
        message.setUserId(Long.valueOf(messageDTO.getUserId()));
        if (messageDTO.getReplyId() != null) {
            message.setReplyId(Long.valueOf(messageDTO.getReplyId()));
        }
        return this.save(message);
    }

    @Override
    public boolean deleteMessage(Long messageId) {
        // 根据id查看是否存在留言
        if(messageMapper.selectById(messageId) == null) {
            return false;
        }
        // 删除留言
        return this.removeById(messageId);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean batchDeleteMessage(List<Long> messageIds) {
        // 批量删除留言
        return this.removeByIds(messageIds);
    }

    @Override
    public boolean updateMessage(MessageUpdateDTO messageUpdateDTO) {
        // 查询该留言
        Message message = messageMapper.selectById(messageUpdateDTO.getMessageId());
        // 更新留言信息
        message.setContent(messageUpdateDTO.getContent());
        return this.updateById(message);
    }

    @Override
    public PageInfo<MessageSearchVO> searchMessages(MessageSearchDTO messageSearchDTO) {
        // 开启分页
        PageHelper.startPage(messageSearchDTO.getPageNum(), messageSearchDTO.getPageSize());

        // 查询留言
        List<Message> messageList = messageMapper.searchMessages(messageSearchDTO);

        // 封装为PageInfo对象，保证分页数据完整
        PageInfo<Message> pageInfo = new PageInfo<>(messageList);

        // 将Message对象转换为MessageSearchVO对象
        List<MessageSearchVO> messageSearchVOList = pageInfo.getList().stream()
                .map(message -> {
                    MessageSearchVO messageSearchVO = new MessageSearchVO();
                    messageSearchVO.setMessageId(message.getMessageId().toString());
                    messageSearchVO.setContent(message.getContent());
                    messageSearchVO.setUserId(message.getUserId().toString());
                    // 转换注册时间格式
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                    LocalDateTime localDateTime = message.getCreateTime().toInstant()
                            .atZone(ZoneOffset.UTC) // 确保时区一致
                            .toLocalDateTime();
                    messageSearchVO.setCreateTime(localDateTime.format(formatter));
                    messageSearchVO.setUserName(userMapper.selectById(message.getUserId()).getUsername());
                    // 设置回复信息
                    if(message.getMessageId() != null) {
                        List<Message> replies = messageMapper.selectRepliesByMessageId(message.getMessageId());
                        messageSearchVO.setReplyList(replies.stream().map(reply -> {
                            MessageSearchVO replyVO = new MessageSearchVO();
                            replyVO.setMessageId(reply.getMessageId().toString());
                            replyVO.setContent(reply.getContent());
                            replyVO.setUserId(reply.getUserId().toString());
                            LocalDateTime localDateTimeReply = reply.getCreateTime().toInstant()
                                    .atZone(ZoneOffset.UTC) // 确保时区一致
                                    .toLocalDateTime();
                            replyVO.setCreateTime(localDateTimeReply.format(formatter));
                            replyVO.setUserName(userMapper.selectById(reply.getUserId()).getUsername());
                            return replyVO;
                        }).toList());
                    }
                    return messageSearchVO;
                }).toList();
        // 创建新的PageInfo对象，包含转换后的数据
        PageInfo<MessageSearchVO> resultPageInfo = new PageInfo<>(messageSearchVOList);
        // 复制分页信息
        resultPageInfo.setPageNum(pageInfo.getPageNum());
        resultPageInfo.setPageSize(pageInfo.getPageSize());
        resultPageInfo.setTotal(pageInfo.getTotal());
        resultPageInfo.setPages(pageInfo.getPages());
        return resultPageInfo;
    }
}




