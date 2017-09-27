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
 * FileName:    SysMenu.java  
 * @author: YM10095
 * @version:    1.0  
 * @date:   	2017年7月29日 下午11:08:36
 */
@Entity
@Table(name="sys_menu")
public class SysMenu {
    //自增，唯一主键
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id;
    //菜单编号
    private String mncode;
    //模块编号
    private String mdcode;
    //模块名称
    private String mdname;
    //菜单名称
    private String mnname;
    //是否使用 0-否 1-是
    private Integer isuse;
    //菜单url
    private String url;
    //菜单选中url
    private String url0;
    //排序号
    private Integer orderno;
    //系统编号
    private String syscode;
    
    /* 非数据库字段  */
    @Transient
    private Integer issetqx;
    
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public String getMncode() {
        return mncode;
    }
    public void setMncode(String mncode) {
        this.mncode = mncode;
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
    public String getMnname() {
        return mnname;
    }
    public void setMnname(String mnname) {
        this.mnname = mnname;
    }
    public Integer getIsuse() {
        return isuse;
    }
    public void setIsuse(Integer isuse) {
        this.isuse = isuse;
    }
    public String getUrl() {
        return url;
    }
    public void setUrl(String url) {
        this.url = url;
    }
    public String getUrl0() {
        return url0;
    }
    public void setUrl0(String url0) {
        this.url0 = url0;
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
    public Integer getIssetqx() {
        return issetqx;
    }
    public void setIssetqx(Integer issetqx) {
        this.issetqx = issetqx;
    }
        
}
