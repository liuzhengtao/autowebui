package com.yuminsoft.com.autoweb.common.controller;

import javax.servlet.http.HttpSession;

import com.yuminsoft.com.autoweb.common.constants.SysConstants;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yuminsoft.com.autoweb.base.controller.BasicController;

/**
 * 提示消息
 * FileName:    PromptMessageController.java  
 * @author: YM10095
 * @version:    1.0  
 * @date:   	2017年7月30日 上午10:25:52
 */
@Controller
@RequestMapping("/promptmessage")
public class PromptMessageController extends BasicController {
    
    /**
     * 清除提示消息
     * @param
     * @author: YM10095
     * @date:	2017年6月14日 上午10:04:31
     */
    @RequestMapping("/removemessage")
    @ResponseBody
    public void removeflash() {
        HttpSession session = this.getSession();
        session.removeAttribute(SysConstants.FLASS_MESSAGE_KEY);
    }
}
