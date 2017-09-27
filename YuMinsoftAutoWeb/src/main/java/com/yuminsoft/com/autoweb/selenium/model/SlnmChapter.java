package com.yuminsoft.com.autoweb.selenium.model;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name="slnm_chapter")
public class SlnmChapter {
    //自增主键,唯一
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id;
    //章节代码,唯一
    private String chaptercode;
    //用例代码
    private String casecode;
    //章节名称
    private String chaptername;
    //是否运行 0-否 1-是
    private Integer isrun;
    //预期结果
    private String expectedresults;
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
    private List<SlnmPage> pagelist;
    
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
    public Integer getIsrun() {
        return isrun;
    }
    public void setIsrun(Integer isrun) {
        this.isrun = isrun;
    }
    public String getExpectedresults() {
        return expectedresults;
    }
    public void setExpectedresults(String expectedresults) {
        this.expectedresults = expectedresults;
    }
    public String getChaptercode() {
        return chaptercode;
    }
    public void setChaptercode(String chaptercode) {
        this.chaptercode = chaptercode;
    }
    public String getChaptername() {
        return chaptername;
    }
    public void setChaptername(String chaptername) {
        this.chaptername = chaptername;
    }
    public List<SlnmPage> getPagelist() {
        return pagelist;
    }
    public void setPagelist(List<SlnmPage> pagelist) {
        this.pagelist = pagelist;
    }

}
