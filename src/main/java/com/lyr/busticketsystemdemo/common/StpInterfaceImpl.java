package com.lyr.busticketsystemdemo.common;

import cn.dev33.satoken.stp.StpInterface;

import java.util.List;

/**
 * 自定义权限加载接口实现类
 *
 * @author yunruili
 * @date 2025/03/13/21:21
 **/
public class StpInterfaceImpl implements StpInterface {
    @Override
    public List<String> getPermissionList(Object o, String s) {
        return List.of();
    }

    @Override
    public List<String> getRoleList(Object o, String s) {
        return List.of();
    }
}
