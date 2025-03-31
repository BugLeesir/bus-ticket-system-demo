package com.lyr.busticketsystemdemo.controller;

import cn.dev33.satoken.util.SaResult;
import com.github.pagehelper.PageInfo;
import com.lyr.busticketsystemdemo.model.dto.MessageBatchDeleteDTO;
import com.lyr.busticketsystemdemo.model.dto.MessageDTO;
import com.lyr.busticketsystemdemo.model.dto.MessageSearchDTO;
import com.lyr.busticketsystemdemo.model.dto.MessageUpdateDTO;
import com.lyr.busticketsystemdemo.model.vo.MessageSearchVO;
import com.lyr.busticketsystemdemo.service.MessageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 留言控制器
 *
 * @author yunruili
 * @date 2025/03/30/19:27
 **/
@RestController
@RequestMapping("/message")
public class MessageController {
    private static final Logger LOGGER = LoggerFactory.getLogger(MessageController.class);

    @Autowired
    private MessageService messageService;

    @PostMapping("/add")
    public SaResult add(@RequestBody MessageDTO messageDTO) {
        return messageService.addMessage(messageDTO) ? SaResult.ok("添加成功") : SaResult.error("添加失败");
    }

    @PostMapping("/search")
    public PageInfo<MessageSearchVO> searchMessages(@RequestBody MessageSearchDTO messageSearchDTO) {
        return messageService.searchMessages(messageSearchDTO);
    }

    @DeleteMapping("/delete")
    public SaResult delete(@RequestParam String id){
        return messageService.deleteMessage(Long.valueOf(id)) ? SaResult.ok("删除成功") : SaResult.error("删除失败");
    }

    @DeleteMapping("/batchDelete")
    public SaResult batchDelete(@RequestBody MessageBatchDeleteDTO messageBatchDeleteDTO) {
        List<String> ids = messageBatchDeleteDTO.getIds();
        // id不能为空
        if (ids == null || ids.isEmpty()) {
            return SaResult.error("id不能为空");
        }
        // 将String类型的id转换为Long类型
        List<Long> messageIds = ids.stream().map(Long::valueOf).toList();
        // 批量删除留言
        return messageService.batchDeleteMessage(messageIds) ? SaResult.ok("批量删除成功") : SaResult.error("批量删除失败");
    }

    @PutMapping("/update")
    public SaResult update(@RequestBody MessageUpdateDTO messageUpdateDTO) {
        // 更新留言
        return messageService.updateMessage(messageUpdateDTO) ? SaResult.ok("更新成功") : SaResult.error("更新失败");
    }
}
