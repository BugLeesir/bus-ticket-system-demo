package com.lyr.busticketsystemdemo.util;

import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.asymmetric.KeyType;
import cn.hutool.crypto.asymmetric.RSA;

import static com.lyr.busticketsystemdemo.consts.SecretConstant.PRIVATE_KEY;

/**
 * 解密工具
 *
 * @author yunruili
 * @date 2025/03/16/23:55
 **/
public class RSAUtil {
    private static final RSA RSA = new RSA(PRIVATE_KEY, null);

    // 解密
    public static String decryptPassword(String encryptedPassword) {
        return StrUtil.utf8Str(RSA.decrypt(encryptedPassword, KeyType.PrivateKey));
    }
}
