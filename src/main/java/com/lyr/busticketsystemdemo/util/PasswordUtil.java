package com.lyr.busticketsystemdemo.util;

import cn.hutool.crypto.digest.DigestUtil;

/**
 * 密码加密工具
 *
 * @author yunruili
 * @date 2025/03/17/00:00
 **/
public class PasswordUtil {
    // 使用 SHA-256 哈希密码
    public static String hashPassword(String rawPassword) {
        return DigestUtil.sha256Hex(rawPassword);
    }

    // 校验密码（手动比对哈希）
    public static boolean matches(String rawPassword, String storedHashedPassword) {
        return storedHashedPassword.equals(hashPassword(rawPassword));
    }
}
