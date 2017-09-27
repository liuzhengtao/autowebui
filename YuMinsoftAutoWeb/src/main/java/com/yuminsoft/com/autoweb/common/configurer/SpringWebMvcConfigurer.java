package com.yuminsoft.com.autoweb.common.configurer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.yuminsoft.com.autoweb.common.interceptor.AccessInfoInterceptor;
import com.yuminsoft.com.autoweb.common.interceptor.LoginInterceptor;

import java.nio.charset.Charset;
import java.util.List;

/**
 * 
 * FileName:    SpringWebMvcConfigurer.java  
 * @author: YM10095
 * @version:    1.0  
 * @date:   	2017年7月29日 下午10:10:14
 */
@Configuration
public class SpringWebMvcConfigurer extends WebMvcConfigurerAdapter {
    @Autowired
    AccessInfoInterceptor accessInfoInterceptor;
    @Autowired
    LoginInterceptor loginInterceptor;
    
    /**
     * 
     * @param registry
     * @author: YM10095
     * @date:	2017年6月22日 下午4:17:30
     */
    public void addInterceptors(InterceptorRegistry registry) {
        //登录拦截器
        InterceptorRegistration loginInterceptor = registry.addInterceptor(this.loginInterceptor);        
        //排除配置
        loginInterceptor.excludePathPatterns("/error");//
        //loginInterceptor.excludePathPatterns("/login/**");//登录
        //拦截配置
        loginInterceptor.addPathPatterns("/**");
        
        //访问信息拦截器
        InterceptorRegistration accessInfoInterceptor = registry.addInterceptor(this.accessInfoInterceptor);        
        //排除配置
        accessInfoInterceptor.excludePathPatterns("/error");//
        accessInfoInterceptor.excludePathPatterns("/promptmessage/removemessage");//清除提示消息
        accessInfoInterceptor.excludePathPatterns("/layui/upload");//
        //拦截配置
        accessInfoInterceptor.addPathPatterns("/**");
        
    }


    @Bean
    public HttpMessageConverter<String> responseBodyConverter() {
        StringHttpMessageConverter converter = new StringHttpMessageConverter(
                Charset.forName("UTF-8"));
        return converter;
    }

    @Override
    public void configureMessageConverters(
            List<HttpMessageConverter<?>> converters) {
        super.configureMessageConverters(converters);
        converters.add(responseBodyConverter());
    }

    @Override
    public void configureContentNegotiation(
            ContentNegotiationConfigurer configurer) {
        configurer.favorPathExtension(false);
    }


}
