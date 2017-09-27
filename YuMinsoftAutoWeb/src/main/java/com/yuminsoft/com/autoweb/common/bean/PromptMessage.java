package com.yuminsoft.com.autoweb.common.bean;

/**
 * 提示消息
 * FileName:    PromptMessage.java  
 * @author: YM10095
 * @version:    1.0  
 * @date:   	2017年7月29日 下午9:57:09
 */
public class PromptMessage {
    //成功消息
    public String success;
    //失败消息
    public String error;
    
    public String getSuccess() {
        return success;
    }
    public void setSuccess(String success) {
        this.success = success;
    }
    public String getError() {
        return error;
    }
    public void setError(String error) {
        this.error = error;
    }
    
}
