package com.lyr.busticketsystemdemo.controller;

import com.github.pagehelper.PageInfo;
import com.lyr.busticketsystemdemo.model.dto.MemberDTO;
import com.lyr.busticketsystemdemo.model.dto.MemberSearchDTO;
import com.lyr.busticketsystemdemo.model.vo.MemberSearchVO;
import com.lyr.busticketsystemdemo.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 会员控制器
 *
 * @author yunruili
 * @date 2025/03/12/20:23
 **/
@RestController
@RequestMapping("/member")
public class MemberController {

    private static final Logger LOGGER = LoggerFactory.getLogger(MemberController.class);

    @Autowired
    private UserService userService;

    @PostMapping("/add")
    public void add(@RequestBody MemberDTO memberDTO){
        boolean isAdded = userService.addMember(memberDTO);
        if (isAdded) {
            LOGGER.info("添加会员成功");
        } else {
            LOGGER.info("添加会员失败");
        }
    }

    @PostMapping("/search")
    public PageInfo<MemberSearchVO> search(@RequestBody MemberSearchDTO memberSearchDTO){
        return userService.searchMembers(memberSearchDTO);
    }
}
