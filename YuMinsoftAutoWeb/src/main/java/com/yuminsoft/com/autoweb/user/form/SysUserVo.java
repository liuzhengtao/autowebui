package com.yuminsoft.com.autoweb.user.form;

import java.util.Date;

import com.yuminsoft.com.autoweb.base.form.BasicVo;

public class SysUserVo extends BasicVo {
    //自增主键,唯一
    private Integer id;
    //用户登录代码,唯一
    private String usercode;
    //用户名称
    private String username;
    //用户密码
    private String userpwd;
    //是否管理员 0-否 1-是
    private Integer isadmin;
    //是否可用 0-否 1-是
    private Integer isuse;
    //部门编号
    private String deptcode;
    //用户电话号码
    private String telno;
    //用户手机号码
    private String mobileno;
    //用户邮箱
    private String email;
    //备注
    private String remark;
    //操作员代码
    private String optcode;
    //生成时间
    private Date createtime;
    
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public String getUsercode() {
        return usercode;
    }
    public void setUsercode(String usercode) {
        this.usercode = usercode;
    }
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getUserpwd() {
        return userpwd;
    }
    public void setUserpwd(String userpwd) {
        this.userpwd = userpwd;
    }
    public Integer getIsadmin() {
        return isadmin;
    }
    public void setIsadmin(Integer isadmin) {
        this.isadmin = isadmin;
    }
    public Integer getIsuse() {
        return isuse;
    }
    public void setIsuse(Integer isuse) {
        this.isuse = isuse;
    }
    public String getDeptcode() {
        return deptcode;
    }
    public void setDeptcode(String deptcode) {
        this.deptcode = deptcode;
    }
    public String getTelno() {
        return telno;
    }
    public void setTelno(String telno) {
        this.telno = telno;
    }
    public String getMobileno() {
        return mobileno;
    }
    public void setMobileno(String mobileno) {
        this.mobileno = mobileno;
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
    
}
