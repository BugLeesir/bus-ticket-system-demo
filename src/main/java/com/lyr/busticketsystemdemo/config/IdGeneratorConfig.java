package com.lyr.busticketsystemdemo.config;

import com.github.yitter.contract.IdGeneratorOptions;
import com.github.yitter.idgen.YitIdHelper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * id生成器配置类
 *
 * @author yunruili
 * @date 2025/03/12/23:23
 **/
@Configuration
public class IdGeneratorConfig {
    @Bean
    public IdGeneratorOptions idGeneratorOptions() {
        // 通过 IP 地址 hash 计算 WorkerId，保证每台机器 WorkerId 唯一
        long workerId = Math.abs(com.github.yitter.contract.IdGeneratorOptions.class.hashCode() % 32);

        IdGeneratorOptions options = new IdGeneratorOptions((short) workerId);
        YitIdHelper.setIdGenerator(options);

        return options;
    }
}
