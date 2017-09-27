package com.yuminsoft.com.autoweb.common.configurer;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.vendor.HibernateJpaSessionFactoryBean;

/**
 * 
 * FileName:    HibernateJpaConfigurer.java  
 * @author: YM10095
 * @version:    1.0  
 * @date:   	2017年7月29日 下午10:10:06
 */
@Configuration
@EnableJpaRepositories("com.tutu.projectjsp.*.dao")
public class HibernateJpaConfigurer {
    @Bean
    public HibernateJpaSessionFactoryBean sessionFactory() {
        return new HibernateJpaSessionFactoryBean();
    }
}
