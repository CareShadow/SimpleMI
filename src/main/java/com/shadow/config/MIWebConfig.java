package com.shadow.config;

import com.shadow.interceptors.LoginInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.annotation.Resource;

/**
 * @ClassName MIWebConfig
 * @Description TODO
 * @Author CareShadow
 * @Date 2023/11/25 22:59
 * @Version 1.0
 **/
@Configuration
public class MIWebConfig implements WebMvcConfigurer {
    @Resource
    private LoginInterceptor loginInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(loginInterceptor)
                .addPathPatterns("/admin/**")
                .addPathPatterns("/base/**")
                .excludePathPatterns("/admin/login");
    }
}
