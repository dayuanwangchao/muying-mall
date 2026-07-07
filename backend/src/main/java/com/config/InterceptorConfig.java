package com.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import com.interceptor.AuthorizationInterceptor;

@Configuration
public class InterceptorConfig extends WebMvcConfigurationSupport{
	
	@Bean
    public AuthorizationInterceptor getAuthorizationInterceptor() {
        return new AuthorizationInterceptor();
    }
	
	@Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(getAuthorizationInterceptor()).addPathPatterns(
        		"/api/**", "/users/**", "/yonghu/**", "/config/**", "/news/**",
        		"/shangpinxinxi/**", "/shangpinfenlei/**", "/shangpinpingjia/**",
        		"/discussshangpinxinxi/**", "/storeup/**", "/cart/**", "/orders/**",
        		"/address/**", "/chat/**", "/file/**", "/location", "/matchFace",
        		"/option/**", "/follow/**", "/sh/**", "/remind/**", "/cal/**",
        		"/group/**", "/value/**")
        .excludePathPatterns("/users/login")
        .excludePathPatterns("/users/register")
        .excludePathPatterns("/yonghu/login")
        .excludePathPatterns("/yonghu/register");
        super.addInterceptors(registry);
	}
	
	/**
	 * springboot 2.0 配置 WebMvcConfigurationSupport 之后，会导致默认配置被覆盖，要访问静态资源需要重写 addResourceHandlers 方法
	 */
	@Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
    	// 配置上传文件的静态资源映射
    	registry.addResourceHandler("/upload/**")
        .addResourceLocations("classpath:/static/upload/")
        .addResourceLocations("file:static/upload/")
        .addResourceLocations("file:upload/");
        
    	// 配置其他静态资源
		registry.addResourceHandler("/**")
        .addResourceLocations("classpath:/resources/")
        .addResourceLocations("classpath:/static/")
        .addResourceLocations("classpath:/admin/")
        .addResourceLocations("classpath:/front/")
        .addResourceLocations("classpath:/public/");
		super.addResourceHandlers(registry);
    }
}
