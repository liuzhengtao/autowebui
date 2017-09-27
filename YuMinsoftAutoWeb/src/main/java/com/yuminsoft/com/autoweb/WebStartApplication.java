package com.yuminsoft.com.autoweb;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;

/**
 * 
 * FileName:    WebStartApplication.java  
 * @author: YM10095
 * @version:    1.0  
 * @date:   	2017年7月29日 下午6:06:06
 */
public class WebStartApplication extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        builder.sources(SeleniumUiApplication.class);
        return super.configure(builder);
    }

}