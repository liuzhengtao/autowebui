package com.yuminsoft.com.autoweb.common.interceptor;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.yuminsoft.com.autoweb.common.constants.SysConstants;
import com.yuminsoft.com.autoweb.system.model.SysMenumx;
import com.yuminsoft.com.autoweb.system.model.SysSystem;
import com.yuminsoft.com.autoweb.system.service.SysSystemService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

/**
 * 
 * FileName:    LoginInterceptor.java  
 * @author: YM10095
 * @version:    1.0  
 * @date:   	2017年7月29日 下午10:25:21
 */
@Component
public class LoginInterceptor extends HandlerInterceptorAdapter {
    private Logger logger = LoggerFactory.getLogger(AccessInfoInterceptor.class);
    
    @Autowired
    SysSystemService sysSystemService;
    
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response,Object handler) throws Exception {
        //登录判断
        boolean isuserlogin = isUserLogin(request);
        if (isuserlogin){
            return true;
        }
        
        //上下文路径
        String contextPath = request.getContextPath();
        //访问路径
        String path = request.getServletPath();
        
        //判断访问权限
        int checkauthflag = checkAuth(path);
        
        //不需要权限
        if (checkauthflag == 0){
            return true;
        }
        //
        SysSystem system = sysSystemService.getSystemFromCache(SysConstants.SYS_DEFAULT_SYSCODE_KEY);
        String redirecturl = "";
        //跳转至登录
        if (checkauthflag == 1){
            redirecturl = contextPath + system.getUrl2();
            redirecturl = redirecturl + "?url=" + path;
        }
        //跳转至无权限
        if (checkauthflag == 2){
            logger.info("未配置菜单明细:" + contextPath + "/" + path);
            redirecturl = contextPath + system.getUrl3();
            redirecturl = redirecturl + "?authflag=2";
        }
        response.sendRedirect(redirecturl);
        
        return false;
    }
       
    /**
     * 
     * @param request
     * @return
     * @author: YM10095
     * @date:	2017年8月15日 下午3:27:59
     */
    public boolean isUserLogin(HttpServletRequest request){
        HttpSession session = request.getSession();
        //登录判断
        if (session.getAttribute(SysConstants.USER_SESSION_KEY) != null){
            return true;
        }
        return false;
    }
    
    /**
     * 判断访问权限
     * @param path
     * @return 0-不需要权限 1-需要权限 2-未配置菜单明细
     * @author: YM10095
     * @date:	2017年8月15日 下午3:28:04
     */
    public int checkAuth(String path){
        int checkauthflag = 0;//默认不需要权限
        Map<String,SysMenumx> menusMap = sysSystemService.getMenumxMapFromCache(SysConstants.SYS_MENUMX_MAP_KEY);
        SysMenumx sysMenumx = menusMap.get(path);
        if(sysMenumx != null){
            //需要权限
            if(sysMenumx.getAuthflag() == 1){
                checkauthflag = 1;
            }
        }else{
            //未配置菜单明细
            checkauthflag = 2;
        }
        
        return checkauthflag;
    }
                             
}
