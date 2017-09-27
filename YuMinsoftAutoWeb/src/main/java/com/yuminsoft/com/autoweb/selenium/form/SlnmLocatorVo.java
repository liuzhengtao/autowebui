package com.yuminsoft.com.autoweb.selenium.form;

import java.util.Date;

import com.yuminsoft.com.autoweb.base.form.BasicVo;

public class SlnmLocatorVo extends BasicVo {
    //自增主键,唯一
    private Integer id;
    //定位器代码,唯一
    private String locatorcode;
    //页面代码
    private String pagecode;
    //定位器名称
    private String locatorname;
    //查找规则类型 id|name|linkText|partialLinkText|cssSelector|xpath|className|tagName
    private String bytype;
    //查找规则值
    private String byvalue;
    //超时时间(秒)
    private Integer timeout;
    //等待时间(秒)
    private Double waittime;
    //操作类型 操作类型 点击(Click)|输入(Type)|清空(Clear)
    private String opttype;
    //数据值
    private String datavalue;
    //排序号
    private Integer orderno;
    //描述
    private String description;
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
    public String getLocatorcode() {
        return locatorcode;
    }
    public void setLocatorcode(String locatorcode) {
        this.locatorcode = locatorcode;
    }
    public String getPagecode() {
        return pagecode;
    }
    public void setPagecode(String pagecode) {
        this.pagecode = pagecode;
    }
    public String getLocatorname() {
        return locatorname;
    }
    public void setLocatorname(String locatorname) {
        this.locatorname = locatorname;
    }
    public String getBytype() {
        return bytype;
    }
    public void setBytype(String bytype) {
        this.bytype = bytype;
    }
    public String getByvalue() {
        return byvalue;
    }
    public void setByvalue(String byvalue) {
        this.byvalue = byvalue;
    }
    public Integer getTimeout() {
        return timeout;
    }
    public void setTimeout(Integer timeout) {
        this.timeout = timeout;
    }
    public String getOpttype() {
        return opttype;
    }
    public void setOpttype(String opttype) {
        this.opttype = opttype;
    }
    public Integer getOrderno() {
        return orderno;
    }
    public void setOrderno(Integer orderno) {
        this.orderno = orderno;
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
    public Double getWaittime() {
        return waittime;
    }
    public void setWaittime(Double waittime) {
        this.waittime = waittime;
    }
    public String getDatavalue() {
        return datavalue;
    }
    public void setDatavalue(String datavalue) {
        this.datavalue = datavalue;
    }
    
}
