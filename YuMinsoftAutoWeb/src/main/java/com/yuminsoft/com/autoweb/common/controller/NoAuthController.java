package com.yuminsoft.com.autoweb.common.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.yuminsoft.com.autoweb.base.controller.BasicController;

/**
 * 
 * FileName:    NoAuthController.java  
 * @author: YM10095
 * @version:    1.0  
 * @date:   	2017年7月29日 下午10:12:26
 */
@Controller
@RequestMapping("/noauth")
public class NoAuthController extends BasicController {
    private final String MAIN_PAGE = "/pages/noauth/";
    private final String INDEX_PAGE = MAIN_PAGE + "noauth";
    
    @RequestMapping("/index")
    public String index() {
        String authflag = this.getParameter("authflag");
        //
        String noAuthMessage = "无访问权限";
        if("2".equals(authflag)){
            noAuthMessage = "未配置菜单明细";
        } else if("400".equals(authflag)){
            noAuthMessage = "（错误请求）服务器不理解请求的语法 ";
        } else if("500".equals(authflag)){
            noAuthMessage = "（服务器内部错误）服务器遇到错误，无法完成请求 ";
        } else if("404".equals(authflag)){
            noAuthMessage = "（请求的网页不存在）服务器找不到请求的网页 ";
        }
        this.setBeanValue("noAuthMessage", noAuthMessage);
        
        return INDEX_PAGE;
    }
    
}
