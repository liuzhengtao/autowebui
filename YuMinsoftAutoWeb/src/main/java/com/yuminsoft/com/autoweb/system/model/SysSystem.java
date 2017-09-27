package com.yuminsoft.com.autoweb.system.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * 
 * FileName:    SysSystem.java  
 * @author: YM10095
 * @version:    1.0  
 * @date:   	2017年7月29日 下午10:47:05
 */
@Entity
@Table(name="sys_system")
public class SysSystem {
    //自增，唯一主键
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id;
    //系统编号
    private String syscode;
    //系统名称
    private String sysname;
    //默认主页url
    private String url1;
    //登录url
    private String url2;
    //无权限跳转url
    private String url3;
    
    /* 非数据库字段  */
    @Transient
    private List<SysModule> sysmodules;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSyscode() {
        return syscode;
    }

    public void setSyscode(String syscode) {
        this.syscode = syscode;
    }

    public String getSysname() {
        return sysname;
    }

    public void setSysname(String sysname) {
        this.sysname = sysname;
    }

    public String getUrl1() {
        return url1;
    }

    public void setUrl1(String url1) {
        this.url1 = url1;
    }

    public String getUrl2() {
        return url2;
    }

    public void setUrl2(String url2) {
        this.url2 = url2;
    }

    public String getUrl3() {
        return url3;
    }

    public void setUrl3(String url3) {
        this.url3 = url3;
    }

    public List<SysModule> getSysmodules() {
        return sysmodules;
    }

    public void setSysmodules(List<SysModule> sysmodules) {
        this.sysmodules = sysmodules;
    }
    
}
