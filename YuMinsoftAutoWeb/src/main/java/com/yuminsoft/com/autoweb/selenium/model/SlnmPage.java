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
 * 页面信息
 * FileName:    SlnmPage.java  
 * @author: YM10095
 * @version:    1.0  
 * @date:   	2017年7月29日 下午10:43:21
 */
@Entity
@Table(name="slnm_page")
public class SlnmPage {
    //自增主键,唯一
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id;
    //页面代码,唯一
    private String pagecode;
    //章节代码
    private String chaptercode;
    //用例代码
    private String casecode;
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
    //生成时间
    private Date createtime;
    //修改时间
    private Date modifiedtime;
    
    /* 非数据库字段  */
    @Transient
    private List<SlnmLocator> locatorlist;
    
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public String getPagecode() {
        return pagecode;
    }
    public void setPagecode(String pagecode) {
        this.pagecode = pagecode;
    }
    
    public String getCasecode() {
        return casecode;
    }
    public void setCasecode(String casecode) {
        this.casecode = casecode;
    }
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
    public List<SlnmLocator> getLocatorlist() {
        return locatorlist;
    }
    public void setLocatorlist(List<SlnmLocator> locatorlist) {
        this.locatorlist = locatorlist;
    }
    public Integer getIsrun() {
        return isrun;
    }
    public void setIsrun(Integer isrun) {
        this.isrun = isrun;
    }
    public String getChaptercode() {
        return chaptercode;
    }
    public void setChaptercode(String chaptercode) {
        this.chaptercode = chaptercode;
    }
    
}
