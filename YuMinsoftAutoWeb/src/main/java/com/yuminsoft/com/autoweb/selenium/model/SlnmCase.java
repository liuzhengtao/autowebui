package com.yuminsoft.com.autoweb.selenium.model;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * 用例信息
 * FileName:    SlnmCase.java  
 * @author: YM10095
 * @version:    1.0  
 * @date:   	2017年7月29日 下午10:43:05
 */
@Entity
@Table(name="slnm_case")
public class SlnmCase {
    //自增主键,唯一
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id;
    //用例代码,唯一
    private String casecode;
    //用例名称
    private String casename;
    //驱动类型 1-IE 2-Firefox 3-Chrome
    private Integer drivertype;
    //驱动文件路径
    private String driverpath;
    //浏览器安装路径
    private String browserpath;
    //是否退出浏览器 0 or null-否 1-是
    private Integer isquitbrowser;
    //描述
    private String description;
    //操作员代码
    private String optcode;
    //生成时间
    private Date createtime;
    //修改时间
    private Date modifiedtime;
    
    /* 非数据库字段  */
    
    @Transient
    private List<SlnmChapter> chapterlist;
    
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }    
    public String getCasecode() {
        return casecode;
    }
    public void setCasecode(String casecode) {
        this.casecode = casecode;
    }
    public String getCasename() {
        return casename;
    }
    public void setCasename(String casename) {
        this.casename = casename;
    }    
    public String getDriverpath() {
        return driverpath;
    }
    public void setDriverpath(String driverpath) {
        this.driverpath = driverpath;
    }
    public Integer getDrivertype() {
        return drivertype;
    }
    public void setDrivertype(Integer drivertype) {
        this.drivertype = drivertype;
    }
    public String getBrowserpath() {
        return browserpath;
    }
    public void setBrowserpath(String browserpath) {
        this.browserpath = browserpath;
    }
    public Integer getIsquitbrowser() {
        return isquitbrowser;
    }
    public void setIsquitbrowser(Integer isquitbrowser) {
        this.isquitbrowser = isquitbrowser;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public Date getCreatetime() {
        return createtime;
    }
    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }
    public Date getModifiedtime() {
        return modifiedtime;
    }
    public void setModifiedtime(Date modifiedtime) {
        this.modifiedtime = modifiedtime;
    }
    public String getOptcode() {
        return optcode;
    }
    public void setOptcode(String optcode) {
        this.optcode = optcode;
    }
    public List<SlnmChapter> getChapterlist() {
        return chapterlist;
    }
    public void setChapterlist(List<SlnmChapter> chapterlist) {
        this.chapterlist = chapterlist;
    }
    
}
