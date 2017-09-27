package com.yuminsoft.com.autoweb.selenium.bean;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder = {   
  "casename",
  "drivertype",
  "driverpath",
  "browserpath",
  "isquitbrowser",
  "description",
  "optcode",
  "chapterbeanlist"
})
public class SlnmCaseBean {
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
    
    @XmlElementWrapper(name = "chapterbeanlist")
    @XmlElement(name = "slnmchapter")
    List<SlnmChapterBean> chapterbeanlist;
    
    public String getCasename() {
        return casename;
    }
    public void setCasename(String casename) {
        this.casename = casename;
    }
    public Integer getDrivertype() {
        return drivertype;
    }
    public void setDrivertype(Integer drivertype) {
        this.drivertype = drivertype;
    }
    public String getDriverpath() {
        return driverpath;
    }
    public void setDriverpath(String driverpath) {
        this.driverpath = driverpath;
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
    public String getOptcode() {
        return optcode;
    }
    public void setOptcode(String optcode) {
        this.optcode = optcode;
    }
    public List<SlnmChapterBean> getChapterbeanlist() {
        return chapterbeanlist;
    }
    public void setChapterbeanlist(List<SlnmChapterBean> chapterbeanlist) {
        this.chapterbeanlist = chapterbeanlist;
    }
    
}
