package com.yuminsoft.com.autoweb.system.service;

import java.util.List;
import java.util.Map;

import com.yuminsoft.com.autoweb.system.model.SysMenu;
import com.yuminsoft.com.autoweb.system.model.SysMenumx;
import com.yuminsoft.com.autoweb.system.model.SysModule;
import com.yuminsoft.com.autoweb.system.model.SysSystem;

/**
 * 
 * FileName:    SysSystemService.java  
 * @author: YM10095
 * @version:    1.0  
 * @date:   	2017年7月29日 下午11:08:55
 */
public interface SysSystemService {
    public SysSystem getSysSystem(String syscode);
    
    public List<SysModule> getSysModuleList(String syscode);
    
    public List<SysMenu> getSysMenuList(String syscode, String mdcode);
    
    public List<SysMenumx> getSysMenumxList(String syscode);
    
    public List<SysMenumx> getSysMenumxList(String mncode,String syscode);
    
    public SysSystem getSystem(String syscode);
    
    public SysSystem getSystemFromCache(String syscode);
    
    public Map<String,SysMenumx> getMenumxMap(String syscode);
    
    public Map<String,SysMenumx> getMenumxMapFromCache(String syscode);
    
}
