package com.yuminsoft.com.autoweb.dept.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name="sys_dept")
public class SysDept {
    //自增主键,唯一
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id;
    //部门代码,唯一
    private String deptcode;
    //上级部门代码
    private String pdeptcode;
    //部门名称
    private String deptname;
    //是否可用 0-否 1-是
    private Integer isuse;
    //部门电话号码
    private String telno;
    //部门传真号码
    private String faxno;
    //部门邮箱
    private String email;
    //备注
    private String remark;
    //操作员代码
    private String optcode;
    //生成时间
    private Date createtime;
    //修改时间
    private Date modifiedtime;
    
    /* 非数据库字段  */
    @Transient
    private String pdeptname;
    
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public String getDeptcode() {
        return deptcode;
    }
    public void setDeptcode(String deptcode) {
        this.deptcode = deptcode;
    }
    public String getPdeptcode() {
        return pdeptcode;
    }
    public void setPdeptcode(String pdeptcode) {
        this.pdeptcode = pdeptcode;
    }
    public String getDeptname() {
        return deptname;
    }
    public void setDeptname(String deptname) {
        this.deptname = deptname;
    }
    public Integer getIsuse() {
        return isuse;
    }
    public void setIsuse(Integer isuse) {
        this.isuse = isuse;
    }
    public String getFaxno() {
        return faxno;
    }
    public void setFaxno(String faxno) {
        this.faxno = faxno;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getRemark() {
        return remark;
    }
    public void setRemark(String remark) {
        this.remark = remark;
    }
    public String getOptcode() {
        return optcode;
    }
    public void setOptcode(String optcode) {
        this.optcode = optcode;
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
    public String getPdeptname() {
        return pdeptname;
    }
    public void setPdeptname(String pdeptname) {
        this.pdeptname = pdeptname;
    }
    public String getTelno() {
        return telno;
    }
    public void setTelno(String telno) {
        this.telno = telno;
    }
    
}
