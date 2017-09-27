package com.yuminsoft.com.autoweb.system.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 
 * FileName:    SysMenumx.java  
 * @author: YM10095
 * @version:    1.0  
 * @date:   	2017年7月29日 下午11:08:44
 */
@Entity
@Table(name="sys_menumx")
public class SysMenumx {
    //自增，唯一主键
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id;
    //菜单明细编号
    private String mnmxcode;
    //菜单编号
    private String mncode;
    //控制器访问路径
    private String contorller;
    //控制器方法
    private String method;
    //权限标识 0-不需要权限 1-需要权限
    private Integer authflag;
    //备注
    private String remark;
    //系统编号
    private String syscode;
    
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public String getMnmxcode() {
        return mnmxcode;
    }
    public void setMnmxcode(String mnmxcode) {
        this.mnmxcode = mnmxcode;
    }
    public String getMncode() {
        return mncode;
    }
    public void setMncode(String mncode) {
        this.mncode = mncode;
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
    public Integer getAuthflag() {
        return authflag;
    }
    public void setAuthflag(Integer authflag) {
        this.authflag = authflag;
    }
    public String getRemark() {
        return remark;
    }
    public void setRemark(String remark) {
        this.remark = remark;
    }
    public String getSyscode() {
        return syscode;
    }
    public void setSyscode(String syscode) {
        this.syscode = syscode;
    }
        
}
