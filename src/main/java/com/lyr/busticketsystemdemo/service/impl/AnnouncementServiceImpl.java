package com.lyr.busticketsystemdemo.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.github.yitter.idgen.YitIdHelper;
import com.lyr.busticketsystemdemo.domain.Announcement;
import com.lyr.busticketsystemdemo.model.dto.AnnouncementDTO;
import com.lyr.busticketsystemdemo.model.dto.AnnouncementSearchDTO;
import com.lyr.busticketsystemdemo.model.dto.AnnouncementUpdateDTO;
import com.lyr.busticketsystemdemo.model.vo.AnnouncementSearchVO;
import com.lyr.busticketsystemdemo.service.AnnouncementService;
import com.lyr.busticketsystemdemo.dao.mapper.AnnouncementMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
* @author yunruili
* @description 针对表【announcement(公告信息表)】的数据库操作Service实现
* @createDate 2025-03-13 18:34:26
*/
@Service
public class AnnouncementServiceImpl extends ServiceImpl<AnnouncementMapper, Announcement>
    implements AnnouncementService{

    @Autowired
    private AnnouncementMapper announcementMapper;

    @Override
    public boolean addAnnouncement(AnnouncementDTO announcementDTO) {
        // 将AnnouncementDTO转换为Announcement实体
        Announcement announcement = new Announcement();
        announcement.setAnnouncementId(YitIdHelper.nextId());
        announcement.setTitle(announcementDTO.getTitle());
        announcement.setContent(announcementDTO.getContent());

        // 保存Announcement实体到数据库
        return this.save(announcement);
    }

    @Override
    public boolean deleteAnnouncement(Long announcementId) {
        // 根据id查看是否存在公告
        if(announcementMapper.selectById(announcementId) == null) {
            return false;
        }
        // 删除公告
        return this.removeById(announcementId);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean batchDeleteAnnouncement(List<Long> announcementIds) {
        return this.removeByIds(announcementIds);
    }

    @Override
    public boolean updateAnnouncement(AnnouncementUpdateDTO announcementUpdateDTO) {
        // 查询该公告
        Announcement announcement = announcementMapper.selectById(announcementUpdateDTO.getAnnouncementId());
        // 更新公告信息
        announcement.setTitle(announcementUpdateDTO.getTitle());
        announcement.setContent(announcementUpdateDTO.getContent());
        return this.updateById(announcement);
    }

    @Override
    public PageInfo<AnnouncementSearchVO> searchAnnouncements(AnnouncementSearchDTO announcementSearchDTO) {
        //开启分页
        PageHelper.startPage(announcementSearchDTO.getPageNum(), announcementSearchDTO.getPageSize());

        //查询公告
        List<Announcement> announcementList = announcementMapper.searchAnnouncements(announcementSearchDTO);

        // 封装为PageInfo对象，确保分页数据完整
        PageInfo<Announcement> pageInfo = new PageInfo<>(announcementList);

        // 将查询结果转换为AnnouncementSearchVO对象

        List<AnnouncementSearchVO> announcementSearchVOList = pageInfo.getList().stream()
                .map(announcement -> {
                    AnnouncementSearchVO announcementSearchVO = new AnnouncementSearchVO();
                    announcementSearchVO.setAnnouncementId(announcement.getAnnouncementId());
                    announcementSearchVO.setTitle(announcement.getTitle());
                    announcementSearchVO.setContent(announcement.getContent());
                    announcementSearchVO.setPublishTime(announcement.getPublishTime());
                    return announcementSearchVO;
                })
                .toList();
        // 创建新的PageInfo对象，包含查询结果和分页信息
        PageInfo<AnnouncementSearchVO> resultPageInfo = new PageInfo<>(announcementSearchVOList);

        // 设置分页信息
        resultPageInfo.setPageNum(pageInfo.getPageNum());
        resultPageInfo.setPageSize(pageInfo.getPageSize());
        resultPageInfo.setTotal(pageInfo.getTotal());
        resultPageInfo.setPages(pageInfo.getPages());
        return resultPageInfo;
    }
}




