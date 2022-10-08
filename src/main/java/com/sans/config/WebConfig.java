package com.sans.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {


    // 跨域
    //@Override
    //public void addCorsMappings(CorsRegistry registry) {
    //    // 允许访问的路径
    //    registry.addMapping("/**")
    //            // 是否发送 cookie
    //            .allowCredentials(true)
    //            // 允许 跨域访问的源
    //            .allowedOrigins("*")
    //            // 允许 接收的请求类型
    //            .allowedMethods("POST","GET")
    //            // 允许 头部设置
    //            .allowedHeaders("*")
    //            // 允许 有效期
    //
    //            .maxAge(1800);
    //}

}
