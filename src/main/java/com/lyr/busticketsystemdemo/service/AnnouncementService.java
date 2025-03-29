package com.lyr.busticketsystemdemo.service;

import com.github.pagehelper.PageInfo;
import com.lyr.busticketsystemdemo.domain.Announcement;
import com.baomidou.mybatisplus.extension.service.IService;
import com.lyr.busticketsystemdemo.model.dto.AnnouncementDTO;
import com.lyr.busticketsystemdemo.model.dto.AnnouncementSearchDTO;
import com.lyr.busticketsystemdemo.model.dto.AnnouncementUpdateDTO;
import com.lyr.busticketsystemdemo.model.vo.AnnouncementSearchVO;

import java.util.List;

/**
* @author yunruili
* @description 针对表【announcement(公告信息表)】的数据库操作Service
* @createDate 2025-03-13 18:34:26
*/
public interface AnnouncementService extends IService<Announcement> {

    /**
     * 添加公告
     *
     * @param announcementDTO 公告信息
     * @return boolean 是否添加成功
     */
    boolean addAnnouncement(AnnouncementDTO announcementDTO);

    /**
     * 删除公告
     *
     * @param announcementId 公告ID
     * @return boolean 是否删除成功
     */
    boolean deleteAnnouncement(Long announcementId);

    /**
     * 批量删除公告
     *
     * @param announcementIds 公告ID列表
     * @return boolean 是否删除成功
     */
    boolean batchDeleteAnnouncement(List<Long> announcementIds);

    /**
     * 更新公告信息
     *
     * @param announcementUpdateDTO 公告更新信息
     * @return boolean 是否更新成功
     */
    boolean updateAnnouncement(AnnouncementUpdateDTO announcementUpdateDTO);

    /**
     *  查询公告
     * @author yunruili
     * @param announcementSearchDTO  公告搜索信息
     * @return * @return: com.github.pagehelper.PageInfo<com.lyr.busticketsystemdemo.model.vo.AnnouncementSearchVO>
     */
    PageInfo<AnnouncementSearchVO> searchAnnouncements(AnnouncementSearchDTO announcementSearchDTO);

}
