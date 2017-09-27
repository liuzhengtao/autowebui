package com.yuminsoft.com.autoweb.selenium.form;

import java.util.Date;

import com.yuminsoft.com.autoweb.base.form.BasicVo;

public class SlnmCaseVo extends BasicVo {
    //自增主键,唯一
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
    
}
