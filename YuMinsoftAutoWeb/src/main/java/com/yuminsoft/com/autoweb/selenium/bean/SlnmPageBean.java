package com.yuminsoft.com.autoweb.selenium.bean;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder = {
  "pagename",
  "pageurl",
  "datafiletype",
  "datafilepath",
  "datafilename",
  "isrun",
  "orderno",
  "description",
  "optcode",
  "locatorbeanlist"
})
public class SlnmPageBean {
    //页面名称
    private String pagename;
    //页面url
    private String pageurl;
    //数据文件类型 1-Excel文件 2-Xml文件
    private Integer datafiletype;
    //数据文件路径
    private String datafilepath;
    //数据文件名
    private String datafilename;
    //是否运行 0-否 1-是
    private Integer isrun;
    //排序号
    private Integer orderno;
    //描述
    private String description;
    //操作员代码
    private String optcode;
    
    @XmlElementWrapper(name = "locatorbeanlist")
    @XmlElement(name = "slnmlocator")
    List<SlnmLocatorBean> locatorbeanlist;
    
    public String getPagename() {
        return pagename;
    }
    public void setPagename(String pagename) {
        this.pagename = pagename;
    }
    public String getPageurl() {
        return pageurl;
    }
    public void setPageurl(String pageurl) {
        this.pageurl = pageurl;
    }
    public Integer getDatafiletype() {
        return datafiletype;
    }
    public void setDatafiletype(Integer datafiletype) {
        this.datafiletype = datafiletype;
    }
    public String getDatafilepath() {
        return datafilepath;
    }
    public void setDatafilepath(String datafilepath) {
        this.datafilepath = datafilepath;
    }
    public String getDatafilename() {
        return datafilename;
    }
    public void setDatafilename(String datafilename) {
        this.datafilename = datafilename;
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
    public String getOptcode() {
        return optcode;
    }
    public void setOptcode(String optcode) {
        this.optcode = optcode;
    }
    public Integer getIsrun() {
        return isrun;
    }
    public void setIsrun(Integer isrun) {
        this.isrun = isrun;
    }
    public List<SlnmLocatorBean> getLocatorbeanlist() {
        return locatorbeanlist;
    }
    public void setLocatorbeanlist(List<SlnmLocatorBean> locatorbeanlist) {
        this.locatorbeanlist = locatorbeanlist;
    }
        
}
