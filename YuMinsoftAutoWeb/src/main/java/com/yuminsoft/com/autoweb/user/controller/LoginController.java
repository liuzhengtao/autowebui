package com.yuminsoft.com.autoweb.user.controller;

import javax.servlet.http.HttpSession;

import com.yuminsoft.com.autoweb.base.controller.BasicController;
import com.yuminsoft.com.autoweb.common.constants.SysConstants;
import com.yuminsoft.com.autoweb.common.util.StringUtil;
import com.yuminsoft.com.autoweb.system.model.SysSystem;
import com.yuminsoft.com.autoweb.system.service.SysSystemService;
import com.yuminsoft.com.autoweb.user.form.LoginVo;
import com.yuminsoft.com.autoweb.user.model.SysUser;
import com.yuminsoft.com.autoweb.user.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 
 * FileName:    LoginController.java  
 * @author: YM10095
 * @version:    1.0  
 * @date:   	2017年7月29日 下午11:09:33
 */
@Controller
@RequestMapping("/login")
public class LoginController extends BasicController {
    private final String MAIN_PATH = "/pages/login/";
    private final String LOGIN_PAGE = MAIN_PATH + "login";
    
    private final String INDEX_URL = "redirect:/login/index";
    private final String MANAGER_INDEX_URL = "redirect:/";
    
    @Autowired
    protected SysUserService sysUserService;
    @Autowired
    SysSystemService sysSystemService;
    
    @Value("${spring.systyp:null}")
    private String systyp;
    
    /**
     * 登录页面
     * @return
     * @author: YM10095
     * @date:	2017年6月12日 下午3:05:17
     */
    @RequestMapping("/index")
    public String index() {
        if(this.getSysUser() != null){
            return MANAGER_INDEX_URL;
        }
        
        String url = this.getParameter("url");
        this.setBeanValue("url", url);
        
        return LOGIN_PAGE;
    }
    
    /**
     * 登录
     * @param request
     * @param map
     * @return
     * @author: YM10095
     * @date:	2017年6月12日 下午3:05:22
     */
    @RequestMapping("/doLogin")
    public String doLogin() {
        if(this.getSysUser() != null){
            return MANAGER_INDEX_URL;
        }
        
        LoginVo form = this.getFormValues(LoginVo.class);
        
        //用户编码
        String operatorcode = form.getOperatorcode();
        if(StringUtil.isNull(operatorcode)){
            this.setError("用户名为空");
            return INDEX_URL;
        }
        operatorcode = operatorcode.toUpperCase();
        //用户密码
        String operatorpass = form.getOperatorpass();
        if(StringUtil.isNull(operatorpass)){
            this.setError("密码为空");            
            return INDEX_URL;
        }
        operatorpass = StringUtil.getMD5(operatorpass);
        
        //登录操作
        SysUser user = null;
        if("1".equals(systyp)){
            user = sysUserService.getSysUser(operatorcode);
            if(user == null){
                this.setError("用户名或密码错误");
                return INDEX_URL;
            }
            if(!operatorpass.equals(user.getUserpwd())){
                this.setError("用户名或密码错误");
                return INDEX_URL;
            } 
            if(user.getIsuse() != 1){
                this.setError("该用户已经被禁用");
                return INDEX_URL;
            }
        } else {
            if(!"admin".toUpperCase().equals(operatorcode) || !"1".equals(form.getOperatorpass())){
                this.setError("用户名或密码错误");            
                return INDEX_URL;
            }
            user = new SysUser();
            user.setUsercode(operatorcode);
            user.setUsername("系统管理员");
            user.setIsadmin(1);
        }
        
        HttpSession session = this.getSession();
        session.setMaxInactiveInterval(1800);//失效时间(秒)
        session.setAttribute(SysConstants.USER_SESSION_KEY, user);
        //获取可访问系统菜单
        SysSystem sysSystem = sysUserService.getLoginSysSystem(user.getUsercode(),SysConstants.SYS_DEFAULT_SYSCODE_KEY);
        session.setAttribute(SysConstants.SYS_MENU_SYSTEM_KEY,sysSystem);
        
        //返回url
        String returl = MANAGER_INDEX_URL;
        String url = this.getParameter("url");
        if(StringUtil.isNotNull(url)){
            returl = "redirect:" + url;
        }
        
        return returl;
    }
    
    /**
     * 登出
     * @param request
     * @param map
     * @return
     * @author: YM10095
     * @date:	2017年6月12日 下午3:05:28
     */
    @RequestMapping("/doLogout")
    public String doLogout() {        
        HttpSession session = this.getSession();
        //移除session
        session.removeAttribute(SysConstants.USER_SESSION_KEY);
        session.removeAttribute(SysConstants.SYS_MENU_SYSTEM_KEY);
        
        //重定向url
        String url = this.getParameter("url");
        if(StringUtil.isNotNull(url)){
            return INDEX_URL + "?url=" + url;
        }
        
        return INDEX_URL;
    }
    
}
