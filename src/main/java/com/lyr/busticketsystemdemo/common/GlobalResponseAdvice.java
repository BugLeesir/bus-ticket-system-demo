package com.lyr.busticketsystemdemo.common;

import cn.dev33.satoken.util.SaResult;
import com.alibaba.fastjson2.JSON;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

/**
 * 响应实体封装切面
 *
 * @author yunruili
 * @date 2025/03/13/19:56
 **/
@RestControllerAdvice(basePackages = {"com.lyr.busticketsystemdemo.controller"})
public class GlobalResponseAdvice implements ResponseBodyAdvice<Object> {
    @Override
    public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
        // 方法没有IgnoreRestControllerResponseAdvice注解，且response不是SaResult类型时启用beforeBodyWrite
        return !returnType.hasMethodAnnotation(IgnoreRestControllerResponseAdvice.class)
                && !returnType.getParameterType().isAssignableFrom(SaResult.class);
    }

    @Override
    public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType, Class<? extends HttpMessageConverter<?>> selectedConverterType, ServerHttpRequest request, ServerHttpResponse response) {
        // 如果返回值是void类型，直接返回200状态信息
        if (returnType.getParameterType().isAssignableFrom(void.class)) {
            return SaResult.ok();
        }
        if (!(body instanceof SaResult)) {
            // warning: RestController方法上返回值类型为String时，响应的Content-Type是text/plain，需要手动指定为application/json
            if (body instanceof String) {
                try {
                    return JSON.toJSONString(SaResult.data(body));
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
            return SaResult.data(body);
        }
        return body;
    }
}
