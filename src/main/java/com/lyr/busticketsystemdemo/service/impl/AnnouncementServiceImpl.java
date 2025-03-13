package com.lyr.busticketsystemdemo.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lyr.busticketsystemdemo.domain.Announcement;
import com.lyr.busticketsystemdemo.service.AnnouncementService;
import com.lyr.busticketsystemdemo.dao.mapper.AnnouncementMapper;
import org.springframework.stereotype.Service;

/**
* @author yunruili
* @description 针对表【announcement(公告信息表)】的数据库操作Service实现
* @createDate 2025-03-13 18:34:26
*/
@Service
public class AnnouncementServiceImpl extends ServiceImpl<AnnouncementMapper, Announcement>
    implements AnnouncementService{

}




