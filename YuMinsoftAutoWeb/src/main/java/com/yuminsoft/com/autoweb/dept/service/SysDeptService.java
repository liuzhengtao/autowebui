package com.yuminsoft.com.autoweb.dept.service;

import java.util.List;

import com.yuminsoft.com.autoweb.common.page.Page;
import com.yuminsoft.com.autoweb.dept.form.SysDeptVo;
import com.yuminsoft.com.autoweb.dept.model.SysDept;

public interface SysDeptService {
    public Page<SysDept> getPage(SysDeptVo form);
    
    public List<SysDept> getSysDeptList();
    
    public SysDept getSysDept(Integer id);
    
    public SysDept getSysDept(String deptcode);
    
    public SysDept getSysDeptByName(String deptname);
    
    public void save(SysDeptVo form);
    
    public void update(SysDeptVo form);
    
    public void delete(Integer id);
    
}
