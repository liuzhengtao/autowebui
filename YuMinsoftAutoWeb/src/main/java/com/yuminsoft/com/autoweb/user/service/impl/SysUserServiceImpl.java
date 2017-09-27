package com.yuminsoft.com.autoweb.user.service.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.yuminsoft.com.autoweb.base.exception.BasicException;
import com.yuminsoft.com.autoweb.common.constants.SysConstants;
import com.yuminsoft.com.autoweb.dept.model.SysDept;
import com.yuminsoft.com.autoweb.system.model.SysModule;
import com.yuminsoft.com.autoweb.user.form.SysUserVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.yuminsoft.com.autoweb.common.page.Page;
import com.yuminsoft.com.autoweb.common.util.StringUtil;
import com.yuminsoft.com.autoweb.dept.dao.SysDeptDao;
import com.yuminsoft.com.autoweb.system.model.SysMenu;
import com.yuminsoft.com.autoweb.system.model.SysMenumx;
import com.yuminsoft.com.autoweb.system.model.SysSystem;
import com.yuminsoft.com.autoweb.system.service.SysSystemService;
import com.yuminsoft.com.autoweb.user.dao.SysUserDao;
import com.yuminsoft.com.autoweb.user.model.SysUser;
import com.yuminsoft.com.autoweb.user.model.SysUserQx;
import com.yuminsoft.com.autoweb.user.service.SysUserService;

@Service
public class SysUserServiceImpl implements SysUserService {
    @Autowired
    protected SysUserDao sysUserDao;
    @Autowired
    protected SysDeptDao sysDeptDao;
    @Autowired
    SysSystemService sysSystemService;
    
    @Override
    public Page<SysUser> getPage(SysUserVo form) {
        Page<SysUser> page = sysUserDao.getPage(form);
        
        for(SysUser bean : page.getResults()){
            SysDept pSysDept = sysDeptDao.getSysDept(bean.getDeptcode());
            if(pSysDept != null){
                //部门名称
                bean.setDeptname(pSysDept.getDeptname());
            }
        }
        
        return page;
    }

    @Override
    public SysUser getSysUser(Integer id) {
        SysUser bean = sysUserDao.getSysUser(id);
        return bean;
    }

    @Override
    public SysUser getSysUser(String usercode) {
        SysUser bean = sysUserDao.getSysUser(usercode);
        return bean;
    }
    
    @Override
    @Transactional()
    public void save(SysUserVo form) {
        SysUser model = sysUserDao.getSysUser(form.getUsercode());
        if(model != null){
            throw new BasicException("该用户已经存在");
        }
        
        SysUser bean = new SysUser();
        BeanUtils.copyProperties(form, bean);
        
        //
        String userpwd = form.getUserpwd();
        userpwd = StringUtil.getMD5(userpwd);
        bean.setUserpwd(userpwd);
        //
        bean.setCreatetime(new Date());
        
        this.sysUserDao.save(bean);
    }

    @Override
    @Transactional()
    public void update(SysUserVo form) {
        Integer id = form.getId();
        SysUser model = sysUserDao.getSysUser(id);
        if(model == null){
            throw new BasicException("该用户不存在");
        }
        String usercode = form.getUsercode();
        if(!usercode.equals(model.getUsercode())){
            SysUser model0 = sysUserDao.getSysUser(usercode);
            if(model0 != null){
                throw new BasicException("该用户已经存在");
            }
        }
        
        model.setUsername(form.getUsername());
        model.setIsadmin(form.getIsadmin());
        model.setIsuse(form.getIsuse());
        model.setDeptcode(form.getDeptcode());
        model.setTelno(form.getTelno());
        model.setMobileno(form.getMobileno());
        model.setEmail(form.getEmail());
        model.setRemark(form.getRemark());
        
        this.sysUserDao.update(model);
    }

    @Override
    @Transactional()
    public void delete(Integer id) {
        SysUser model = sysUserDao.getSysUser(id);
        if(model == null){
            throw new BasicException("该用户不存在");
        }
        String usercode = model.getUsercode();
        int ret = this.sysUserDao.delete(id);
        if(ret == 0){
            throw new BasicException("该用户不存在");
        }
        String syscode = SysConstants.SYS_DEFAULT_SYSCODE_KEY;
        //删除用户权限
        this.sysUserDao.deleteUserQx(usercode,syscode);
    }

    @Override
    public SysSystem getQxSysSystem(String usercode,String syscode) {
        SysSystem sysSystem = sysSystemService.getSystem(syscode);
        for(SysModule sysModule : sysSystem.getSysmodules()){
            for(SysMenu sysMenu : sysModule.getSysmenus()){
                String mncode =sysMenu.getMncode();
                int qxcount = sysUserDao.getUserQxCount(usercode, mncode, syscode);
                if(qxcount > 0){
                    sysMenu.setIssetqx(1);
                }
            }
        }
        
        return sysSystem;
    }

    @Override
    public SysSystem getLoginSysSystem(String usercode,String syscode) {
        SysSystem sysSystem = sysSystemService.getSysSystem(syscode);
        
        List<SysModule> sysmodules = new ArrayList<SysModule>();
        SysSystem sysSystem0 = this.getQxSysSystem(usercode,syscode);
        for(SysModule sysModule0 : sysSystem0.getSysmodules()){
            List<SysMenu> sysmenus = new ArrayList<SysMenu>();
            for(SysMenu sysMenu0 : sysModule0.getSysmenus()){
                if(sysMenu0.getIssetqx() != null && sysMenu0.getIssetqx() == 1){
                    sysmenus.add(sysMenu0);
                }
            }
            if(sysmenus.size() > 0){
                sysModule0.setSysmenus(sysmenus);
                sysmodules.add(sysModule0);
            }
        }
        sysSystem.setSysmodules(sysmodules);
        
        return sysSystem;
    }
    
    @Override
    @Transactional()
    public void doSetUserQx(SysUserVo form) {
        Integer id = form.getId();
        SysUser model = sysUserDao.getSysUser(id);
        if(model == null){
            throw new BasicException("该用户不存在");
        }
        String usercode = model.getUsercode();
        
        String syscode = SysConstants.SYS_DEFAULT_SYSCODE_KEY;
        //删除用户权限
        this.sysUserDao.deleteUserQx(usercode,syscode);
        //
        String jsonstring = form.getJsonstring();
        if(StringUtil.isNull(jsonstring)){
            return;
        }
        
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            List<Map<String,String>> list = objectMapper.readValue(jsonstring, List.class);
            for(Map<String,String> map :list){
                String mncode = map.get("mncode");
                List<SysMenumx> sysMenumxList = sysSystemService.getSysMenumxList(mncode,syscode);
                for(SysMenumx sysMenumx :sysMenumxList){
                    SysUserQx bean = new SysUserQx();
                    bean.setUsercode(usercode);
                    bean.setMncode(mncode);
                    bean.setContorller(sysMenumx.getContorller());
                    bean.setMethod(sysMenumx.getMethod());
                    bean.setSyscode(syscode);
                    this.sysUserDao.save(bean);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            throw new BasicException("转换json数据异常");
        }
    }
    
}
