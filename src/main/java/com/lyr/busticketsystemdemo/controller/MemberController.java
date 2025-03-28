package com.lyr.busticketsystemdemo.controller;

import cn.dev33.satoken.util.SaResult;
import com.github.pagehelper.PageInfo;
import com.lyr.busticketsystemdemo.model.dto.MemberDTO;
import com.lyr.busticketsystemdemo.model.dto.MemberSearchDTO;
import com.lyr.busticketsystemdemo.model.vo.MemberSearchVO;
import com.lyr.busticketsystemdemo.service.UserService;
import com.lyr.busticketsystemdemo.util.RSAUtil;
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
    public SaResult add(@RequestBody MemberDTO memberDTO){
       // 检查用户名是否存在
        if(userService.checkHasUser(memberDTO.getUsername())){
            return SaResult.error("用户名已存在");
        }
        // 解密密码
        memberDTO.setPassword(RSAUtil.decryptPassword(memberDTO.getPassword()));
        // 添加会员
        boolean added = userService.addMember(memberDTO);
        if(added){
            return SaResult.ok("添加成功");
        }else{
            return SaResult.error("添加失败");
        }
    }

    @PostMapping("/search")
    public PageInfo<MemberSearchVO> search(@RequestBody MemberSearchDTO memberSearchDTO){
        return userService.searchMembers(memberSearchDTO);
    }
}
