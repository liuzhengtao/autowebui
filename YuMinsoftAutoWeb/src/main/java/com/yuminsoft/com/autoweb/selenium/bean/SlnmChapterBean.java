package com.yuminsoft.com.autoweb.selenium.bean;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder = {   
  "chaptername",
  "isrun",
  "orderno",
  "description",
  "optcode",
  "pagebeanlist",
  "expectedresults"
})
public class SlnmChapterBean {
    //章节名称
    private String chaptername;
    //是否运行 0-否 1-是
    private Integer isrun;
    //章节的预期结果
    private String expectedresults;
    //排序号
    private Integer orderno;
    //描述
    private String description;
    //操作员代码
    private String optcode;


    @XmlElementWrapper(name = "pagebeanlist")
    @XmlElement(name = "slnmpage")
    List<SlnmPageBean> pagebeanlist;
    
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
    public String getChaptername() {
        return chaptername;
    }
    public void setChaptername(String chaptername) {
        this.chaptername = chaptername;
    }
    public List<SlnmPageBean> getPagebeanlist() {
        return pagebeanlist;
    }
    public void setPagebeanlist(List<SlnmPageBean> pagebeanlist) {
        this.pagebeanlist = pagebeanlist;
    }

    public String getExpectedresults() {
        return expectedresults;
    }

    public void setExpectedresults(String expectedResults) {
        this.expectedresults = expectedResults;
    }
    
}
