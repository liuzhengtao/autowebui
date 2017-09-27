package com.yuminsoft.com.autoweb.user.form;

import com.yuminsoft.com.autoweb.base.form.BasicVo;

public class SysUserQxVo extends BasicVo {
    //自增主键,唯一
    private Integer id;
    //用户登录代码
    private String usercode;
    //菜单编号
    private String mncode;
    //控制器访问路径
    private String contorller;
    //控制器方法
    private String method;
    //系统编号
    private String syscode;
    
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public String getUsercode() {
        return usercode;
    }
    public void setUsercode(String usercode) {
        this.usercode = usercode;
    }
    public String getContorller() {
        return contorller;
    }
    public void setContorller(String contorller) {
        this.contorller = contorller;
    }
    public String getMethod() {
        return method;
    }
    public void setMethod(String method) {
        this.method = method;
    }
    public String getSyscode() {
        return syscode;
    }
    public void setSyscode(String syscode) {
        this.syscode = syscode;
    }
    public String getMncode() {
        return mncode;
    }
    public void setMncode(String mncode) {
        this.mncode = mncode;
    }
    
}
