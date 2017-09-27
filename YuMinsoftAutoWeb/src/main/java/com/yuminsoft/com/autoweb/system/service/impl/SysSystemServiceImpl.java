package com.yuminsoft.com.autoweb.system.service.impl;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.yuminsoft.com.autoweb.common.constants.SysConstants;
import com.yuminsoft.com.autoweb.system.model.SysModule;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yuminsoft.com.autoweb.common.cache.OsCache;
import com.yuminsoft.com.autoweb.common.util.StringUtil;
import com.yuminsoft.com.autoweb.system.dao.SysSystemDao;
import com.yuminsoft.com.autoweb.system.model.SysSystem;
import com.yuminsoft.com.autoweb.system.model.SysMenu;
import com.yuminsoft.com.autoweb.system.model.SysMenumx;
import com.yuminsoft.com.autoweb.system.service.SysSystemService;

@Service
public class SysSystemServiceImpl implements SysSystemService {
    @Autowired
    protected SysSystemDao sysSystemDao;
    
    private OsCache cache = OsCache.getInstance();
    
    @Override
    public SysSystem getSysSystem(String syscode) {
        SysSystem bean = sysSystemDao.getSysSystem(syscode);
        return bean;
    }

    @Override
    public List<SysModule> getSysModuleList(String syscode) {
        List<SysModule> list = sysSystemDao.getSysModuleList(syscode);
        return list;
    }

    @Override
    public List<SysMenu> getSysMenuList(String syscode, String mdcode) {
        List<SysMenu> list = sysSystemDao.getSysMenuList(syscode,mdcode);
        return list;
    }

    @Override
    public List<SysMenumx> getSysMenumxList(String syscode) {
        List<SysMenumx> list = sysSystemDao.getSysMenumxList(syscode);
        return list;
    }
    
    @Override
    public List<SysMenumx> getSysMenumxList(String mncode, String syscode) {
        List<SysMenumx> list = sysSystemDao.getSysMenumxList(mncode,syscode);
        return list;
    }
    
    @Override
    public SysSystem getSystem(String syscode) {
        //系统
        SysSystem system = this.getSysSystem(syscode);
        List<SysModule> sysModuleList = this.getSysModuleList(syscode);
        
        //系统模块
        List<SysModule> sysmodules = new ArrayList<SysModule>();
        for(SysModule sysModule : sysModuleList){
            String mdcode = sysModule.getMdcode();
            List<SysMenu> sysMenuList = this.getSysMenuList(syscode, mdcode);
            //系统设置
            List<SysMenu> sysmenus = new ArrayList<SysMenu>();
            for(SysMenu sysMenu : sysMenuList){
                if(1 == sysMenu.getIsuse()){
                    SysMenu xtsz = new SysMenu();
                    BeanUtils.copyProperties(sysMenu, xtsz);
                    sysmenus.add(xtsz);
                }
            }
            if(!sysmenus.isEmpty()){
                //
                SysModule sysmodule = new SysModule();
                BeanUtils.copyProperties(sysModule, sysmodule);
                sysmodule.setSysmenus(sysmenus);
                sysmodules.add(sysmodule);
            }
        }
        system.setSysmodules(sysmodules);
        
        return system;
    }

    @Override
    public SysSystem getSystemFromCache(String syscode) {
        SysSystem bean = (SysSystem) cache.get(SysConstants.SYS_SYSTEM_KEY);
        if(bean == null){
            bean = this.getSystem(syscode);
            cache.put(SysConstants.SYS_SYSTEM_KEY, bean);
        }
        
        return bean;
    }

    @Override
    public Map<String, SysMenumx> getMenumxMap(String syscode) {
        Map<String,SysMenumx> menusMap = new LinkedHashMap<String,SysMenumx>();
        List<SysMenumx> sysMenumxList = this.getSysMenumxList(syscode);
        for(SysMenumx sysMenumx : sysMenumxList){
            String url = sysMenumx.getContorller();
            if(StringUtil.isNotNull(sysMenumx.getMethod())){
                url = url + "/" + sysMenumx.getMethod();
            }
            //
            menusMap.put(url, sysMenumx);
        }
        
        return menusMap;
    }
    
    @Override
    public Map<String, SysMenumx> getMenumxMapFromCache(String syscode) {
        Map<String,SysMenumx> menusMap = (Map<String, SysMenumx>) cache.get(SysConstants.SYS_MENUMX_MAP_KEY);
        if(menusMap == null){
            menusMap = this.getMenumxMap(syscode);
            cache.put(SysConstants.SYS_MENUMX_MAP_KEY, menusMap);
        }
        
        return menusMap;
    }
    
}
