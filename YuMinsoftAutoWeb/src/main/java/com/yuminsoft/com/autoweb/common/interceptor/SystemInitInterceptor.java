package com.yuminsoft.com.autoweb.common.interceptor;

import java.util.Map;

import com.yuminsoft.com.autoweb.common.constants.SysConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.yuminsoft.com.autoweb.common.cache.OsCache;
import com.yuminsoft.com.autoweb.system.model.SysMenumx;
import com.yuminsoft.com.autoweb.system.model.SysSystem;
import com.yuminsoft.com.autoweb.system.service.SysSystemService;

/**
 * 
 * FileName:    SystemInitInterceptor.java  
 * @author: YM10095
 * @version:    1.0  
 * @date:   	2017年7月29日 下午10:25:59
 */
@Component
public class SystemInitInterceptor implements CommandLineRunner {
    private Logger logger = LoggerFactory.getLogger(SystemInitInterceptor.class);
    private OsCache cache = OsCache.getInstance();
    
    @Autowired
    SysSystemService sysSystemService;
    
    @Override
    public void run(String... args) throws Exception {        
        //初始化系统
        //logger.info("初始化系统");
        String syscode = SysConstants.SYS_DEFAULT_SYSCODE_KEY;
        //加载系统信息
        initSystem(syscode);
        //加载菜单明细信息
        initMenumxs(syscode);
        
    }
    
    /**
     * 加载系统信息
     * @author: YM10095
     * @date:	2017年7月29日 下午10:27:05
     */
    public void initSystem(String syscode){
        logger.info("加载系统信息...");
        SysSystem system = sysSystemService.getSystem(syscode);
        //
        cache.put(SysConstants.SYS_SYSTEM_KEY, system);
    }
    
    /**
     * 加载菜单明细信息
     * @param system
     * @author: YM10095
     * @date:	2017年6月23日 下午1:56:40
     */
    public void initMenumxs(String syscode){
        logger.info("加载菜单明细信息...");
        Map<String,SysMenumx> menusMap = sysSystemService.getMenumxMap(syscode);
        cache.put(SysConstants.SYS_MENUMX_MAP_KEY, menusMap);
    }

}