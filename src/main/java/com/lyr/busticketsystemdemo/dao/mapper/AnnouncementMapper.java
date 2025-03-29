package com.lyr.busticketsystemdemo.dao.mapper;

import com.lyr.busticketsystemdemo.domain.Announcement;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lyr.busticketsystemdemo.model.dto.AnnouncementSearchDTO;

import java.util.List;

/**
* @author yunruili
* @description 针对表【announcement(公告信息表)】的数据库操作Mapper
* @createDate 2025-03-13 18:34:26
* @Entity com.lyr.busticketsystemdemo.domain.Announcement
*/
public interface AnnouncementMapper extends BaseMapper<Announcement> {

    List<Announcement> searchAnnouncements(AnnouncementSearchDTO announcementSearchDTO);

}




