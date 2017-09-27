package com.yuminsoft.com.autoweb.user.service;

import com.yuminsoft.com.autoweb.common.page.Page;
import com.yuminsoft.com.autoweb.system.model.SysSystem;
import com.yuminsoft.com.autoweb.user.form.SysUserVo;
import com.yuminsoft.com.autoweb.user.model.SysUser;

/**
 * 
 * FileName:    SysUserService.java  
 * @author: YM10095
 * @version:    1.0  
 * @date:   	2017年7月29日 下午11:26:07
 */
public interface SysUserService {
    public Page<SysUser> getPage(SysUserVo form);
    
    public SysUser getSysUser(Integer id);
    
    public SysUser getSysUser(String usercode);
    
    public void save(SysUserVo form);
    
    public void update(SysUserVo form);
    
    public void delete(Integer id);
    
    public SysSystem getQxSysSystem(String usercode,String syscode);
    
    public SysSystem getLoginSysSystem(String usercode,String syscode);
    
    public void doSetUserQx(SysUserVo form);
    
}
