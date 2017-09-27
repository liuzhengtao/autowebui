package com.yuminsoft.com.autoweb.selenium.bean;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder = {
  "locatorname",
  "bytype",
  "byvalue",
  "timeout",
  "waittime",
  "opttype",
  "datavalue",
  "orderno",
  "description"
})
public class SlnmLocatorBean {
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
