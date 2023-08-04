package com.shooterj.common.datafilter.config;

import com.shooterj.common.datafilter.interceptor.DataFilterInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 添加数据过滤相关的拦截器。
 *
 * @author shooterj
 */
@Configuration
public class DataFilterWebMvcConfigurer implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new DataFilterInterceptor()).addPathPatterns("/**");
    }
}
