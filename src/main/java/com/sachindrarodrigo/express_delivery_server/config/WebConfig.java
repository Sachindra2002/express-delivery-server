package com.sachindrarodrigo.express_delivery_server.config;

import com.sachindrarodrigo.express_delivery_server.interceptor.RestLoggerInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new RestLoggerInterceptor()).addPathPatterns("/api/**");
    }

}
