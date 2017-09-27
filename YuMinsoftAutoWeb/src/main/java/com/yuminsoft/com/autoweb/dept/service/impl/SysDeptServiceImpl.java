package com.yuminsoft.com.autoweb.dept.service.impl;

import java.util.Date;
import java.util.List;

import com.yuminsoft.com.autoweb.base.exception.BasicException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yuminsoft.com.autoweb.common.dao.SysMaxKeyValueDao;
import com.yuminsoft.com.autoweb.common.page.Page;
import com.yuminsoft.com.autoweb.common.util.StringUtil;
import com.yuminsoft.com.autoweb.dept.dao.SysDeptDao;
import com.yuminsoft.com.autoweb.dept.form.SysDeptVo;
import com.yuminsoft.com.autoweb.dept.model.SysDept;
import com.yuminsoft.com.autoweb.dept.service.SysDeptService;

@Service
public class SysDeptServiceImpl implements SysDeptService {
    @Autowired
    protected SysDeptDao sysDeptDao;
    @Autowired
    protected SysMaxKeyValueDao sysMaxKeyValueDao;
    
    @Override
    public Page<SysDept> getPage(SysDeptVo form) {
        Page<SysDept> page = sysDeptDao.getPage(form);
        
        for(SysDept bean : page.getResults()){
            SysDept pSysDept = sysDeptDao.getSysDept(bean.getPdeptcode());
            if(pSysDept != null){
                //上级部门名称
                bean.setPdeptname(pSysDept.getDeptname());
            }
        }
        
        return page;
    }

    @Override
    public List<SysDept> getSysDeptList() {
        List<SysDept> list = sysDeptDao.getSysDeptList();
        return list;
    }
    
    @Override
    public SysDept getSysDept(Integer id) {
        SysDept bean = sysDeptDao.getSysDept(id);
        return bean;
    }
    
    public SysDept getSysDept(String deptcode) {
        SysDept bean = sysDeptDao.getSysDept(deptcode);
        return bean;
    }

    @Override
    public SysDept getSysDeptByName(String deptname) {
        SysDept bean = sysDeptDao.getSysDeptByName(deptname);
        return bean;
    }
    
    @Override
    @Transactional()
    public void save(SysDeptVo form) {
        SysDept model = sysDeptDao.getSysDeptByName(form.getDeptname());
        if(model != null){
            throw new BasicException("该部门已经存在");
        }
        
        SysDept bean = new SysDept();
        BeanUtils.copyProperties(form, bean);
        
        //上级部门代码
        String deptcode = "";
        String pdeptcode = form.getPdeptcode();
        if(StringUtil.isNull(pdeptcode) || "0".equals(pdeptcode)){
            //一级部门
            int id = sysMaxKeyValueDao.getMaxValue("sys_dept");
            deptcode = StringUtil.formatLeft(id, "0000");
            
        }else{
            //次级部门
            int id = sysMaxKeyValueDao.getMaxValue("sys_dept",pdeptcode);
            deptcode = StringUtil.formatLeft(id, "0000");
            deptcode = pdeptcode + deptcode;
        }
        
        bean.setDeptcode(deptcode);
        bean.setPdeptcode(form.getPdeptcode());
        bean.setOptcode(form.getOptcode());
        bean.setCreatetime(new Date());
        bean.setModifiedtime(new Date());
        
        this.sysDeptDao.save(bean);
    }

    @Override
    @Transactional()
    public void update(SysDeptVo form) {
        Integer id = form.getId();
        SysDept model = sysDeptDao.getSysDept(id);
        if(model == null){
            throw new BasicException("该部门不存在");
        }
        String deptname = form.getDeptname();
        if(!deptname.equals(model.getDeptname())){
            SysDept model0 = sysDeptDao.getSysDeptByName(deptname);
            if(model0 != null){
                throw new BasicException("该部门已经存在");
            }
        }
        
        model.setDeptname(form.getDeptname());
        model.setIsuse(form.getIsuse());
        model.setTelno(form.getTelno());
        model.setFaxno(form.getFaxno());
        model.setEmail(form.getEmail());
        model.setRemark(form.getRemark());
        
        model.setOptcode(form.getOptcode());
        model.setCreatetime(new Date());
        model.setModifiedtime(new Date());
        
        this.sysDeptDao.update(model);
    }

    @Override
    @Transactional()
    public void delete(Integer id) {
        SysDept model = sysDeptDao.getSysDept(id);
        if(model == null){
            throw new BasicException("该部门不存在");
        }
        int ret = this.sysDeptDao.delete(id);
        if(ret == 0){
            throw new BasicException("该部门不存在");
        }
        //删除子部门
        this.sysDeptDao.deleteByPdeptcode(model.getDeptcode());
    }
    
}
