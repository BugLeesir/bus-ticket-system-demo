package com.lyr.busticketsystemdemo.controller;

import cn.dev33.satoken.util.SaResult;
import com.github.pagehelper.PageInfo;
import com.lyr.busticketsystemdemo.model.dto.AnnouncementBatchDeleteDTO;
import com.lyr.busticketsystemdemo.model.dto.AnnouncementDTO;
import com.lyr.busticketsystemdemo.model.dto.AnnouncementSearchDTO;
import com.lyr.busticketsystemdemo.model.dto.AnnouncementUpdateDTO;
import com.lyr.busticketsystemdemo.model.vo.AnnouncementSearchVO;
import com.lyr.busticketsystemdemo.service.AnnouncementService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 公告管理控制器
 *
 * @author yunruili
 * @date 2025/03/29/19:47
 **/
@RestController
@RequestMapping("/announcement")
public class AnnouncementController {
    private static final Logger LOGGER = LoggerFactory.getLogger(AnnouncementController.class);

    @Autowired
    private AnnouncementService announcementService;

    @PostMapping("/add")
    public SaResult add(@RequestBody AnnouncementDTO announcementDTO){
        // 添加公告
        boolean added = announcementService.addAnnouncement(announcementDTO);
        if(added) {
            return SaResult.ok("添加成功");
        }else {
            return SaResult.error("添加失败");
        }
    }

    @PostMapping("/search")
    public PageInfo<AnnouncementSearchVO> search(@RequestBody AnnouncementSearchDTO announcementSearchDTO){
        return announcementService.searchAnnouncements(announcementSearchDTO);
    }

    @DeleteMapping("/delete")
    public SaResult delete(@RequestParam String id){
        boolean result = announcementService.deleteAnnouncement(Long.valueOf(id));
        if(result) {
            return SaResult.ok("删除成功");
        }else {
            return SaResult.error("删除失败");
        }
    }

    @DeleteMapping("/batchDelete")
    public SaResult batchDelete(@RequestBody AnnouncementBatchDeleteDTO announcementBatchDeleteDTO) {
        List<String> ids = announcementBatchDeleteDTO.getIds();
        // id不能为空
        if (ids == null || ids.isEmpty()) {
            return SaResult.error("id不能为空");
        }
        // 将String类型的id转换为Long类型
        List<Long> announcementIds = ids.stream().map(Long::valueOf).toList();
        // 批量删除公告
        boolean result = announcementService.batchDeleteAnnouncement(announcementIds);
        if (result) {
            return SaResult.ok("批量删除成功");
        } else {
            return SaResult.error("批量删除失败");
        }
    }

    @PutMapping("/update")
    public SaResult update(@RequestBody AnnouncementUpdateDTO announcementUpdateDTO){
        boolean result = announcementService.updateAnnouncement(announcementUpdateDTO);
        if(result) {
            return SaResult.ok("更新成功");
        }else {
            return SaResult.error("更新失败");
        }
    }
}
