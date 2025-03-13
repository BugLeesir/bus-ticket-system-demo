package com.lyr.busticketsystemdemo.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lyr.busticketsystemdemo.domain.Message;
import com.lyr.busticketsystemdemo.service.MessageService;
import com.lyr.busticketsystemdemo.dao.mapper.MessageMapper;
import org.springframework.stereotype.Service;

/**
* @author yunruili
* @description 针对表【message(留言信息表)】的数据库操作Service实现
* @createDate 2025-03-13 18:34:26
*/
@Service
public class MessageServiceImpl extends ServiceImpl<MessageMapper, Message>
    implements MessageService{

}




