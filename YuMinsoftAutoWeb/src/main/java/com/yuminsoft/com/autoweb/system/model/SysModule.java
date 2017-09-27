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
 * FileName:    SysModule.java  
 * @author: YM10095
 * @version:    1.0  
 * @date:   	2017年7月29日 下午11:08:27
 */
@Entity
@Table(name="sys_module")
public class SysModule {
    //自增，唯一主键
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id;
    //模块编号
    private String mdcode;
    //模块名称
    private String mdname;
    //排序号
    private Integer orderno;
    //系统编号
    private String syscode;
    
    /* 非数据库字段  */
    @Transient
    private List<SysMenu> sysmenus;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMdcode() {
        return mdcode;
    }

    public void setMdcode(String mdcode) {
        this.mdcode = mdcode;
    }

    public String getMdname() {
        return mdname;
    }

    public void setMdname(String mdname) {
        this.mdname = mdname;
    }

    public Integer getOrderno() {
        return orderno;
    }

    public void setOrderno(Integer orderno) {
        this.orderno = orderno;
    }

    public String getSyscode() {
        return syscode;
    }

    public void setSyscode(String syscode) {
        this.syscode = syscode;
    }

    public List<SysMenu> getSysmenus() {
        return sysmenus;
    }

    public void setSysmenus(List<SysMenu> sysmenus) {
        this.sysmenus = sysmenus;
    }
    
}
