package com.lyr.busticketsystemdemo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

/**
 * 跨域配置
 *
 * @author yunruili
 * @date 2025/03/13/22:05
 **/
@Configuration
public class CorsConfig {
    @Bean
    public CorsFilter corsFilter() {
        CorsConfiguration config = new CorsConfiguration();
        config.addAllowedOriginPattern("*"); // 允许所有域名访问（生产环境建议指定域名）
        config.addAllowedMethod("*");  // 允许所有请求方法
        config.addAllowedHeader("*");  // 允许所有请求头
        config.setAllowCredentials(true);  // 允许携带 Cookie

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config);
        return new CorsFilter(source);
    }
}
