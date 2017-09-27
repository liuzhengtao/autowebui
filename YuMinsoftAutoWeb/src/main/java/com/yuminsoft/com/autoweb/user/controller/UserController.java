package com.yuminsoft.com.autoweb.user.controller;

import java.util.List;

import com.yuminsoft.com.autoweb.base.exception.BasicException;
import com.yuminsoft.com.autoweb.common.constants.SysConstants;
import com.yuminsoft.com.autoweb.common.page.Page;
import com.yuminsoft.com.autoweb.dept.model.SysDept;
import com.yuminsoft.com.autoweb.system.model.SysSystem;
import com.yuminsoft.com.autoweb.user.form.SysUserVo;
import com.yuminsoft.com.autoweb.user.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.yuminsoft.com.autoweb.base.controller.BasicController;
import com.yuminsoft.com.autoweb.dept.service.SysDeptService;
import com.yuminsoft.com.autoweb.user.model.SysUser;

/**
 * 用户管理
 * Filename:    UserController.java  
 * Description: 
 * Company:     tutu.com
 * @author: YM10095
 * Create at:   2017年7月5日 下午5:00:15
 */
@Controller
@RequestMapping("/user")
public class UserController extends BasicController {
    private final String MAIN_PATH = "/pages/user/";
    private final String LIST_PAGE = MAIN_PATH + "userList";
    private final String ADD_PAGE = MAIN_PATH + "userAdd";
    
    private final String SET_USERQX_PAGE = MAIN_PATH + "userSetQx";//
    
    private final String LIST_URL = "redirect:/user/list";
    
    @Autowired  
    private SysUserService sysUserService;
    @Autowired  
    private SysDeptService sysDeptService;
    
    /**
     * 用户列表
     * @return
     * @author: YM10095
     * @date:	2017年7月29日 下午11:14:08
     */
    @RequestMapping("/list")
    public String list() {
        SysUserVo form = this.getFormValues(SysUserVo.class);
        Page<SysUser> page = sysUserService.getPage(form);
        this.savePageInfo(page, form);
        
        return LIST_PAGE;
    }
    
    /**
     * 新增用户页面
     * @return
     * @author: YM10095
     * @date:	2017年7月29日 下午11:15:37
     */
    @RequestMapping("/toAdd")
    public String toAdd() {
        //获取部门列表
        List<SysDept> sysDeptList = sysDeptService.getSysDeptList();
        this.setBeanValue("sysDeptList", sysDeptList);
        
        return ADD_PAGE;
    }
    
    /**
     * 新增用户
     * @return
     * @author: YM10095
     * @date:	2017年7月29日 下午11:15:45
     */
    @RequestMapping("/save")
    public String save() {
        SysUserVo form = this.getFormValues(SysUserVo.class);
        String msg = "新增用户信息";
        try{
            SysUser user = (SysUser) this.getSysUser();
            form.setOptcode(user.getUsercode());
            //
            sysUserService.save(form);
            
            this.setSuccess(msg + "成功");
        } catch(BasicException be){
            logger.error(be.getMessage());
            this.setError(msg + "失败:" + be.getMessage());
        } catch(Exception e){
            logger.error(e.getMessage());
            this.setError(msg + "失败:" + this.SYSTEM_ERROR);
        }
        
        return LIST_URL;
    }
    
    /**
     * 编辑用户页面
     * @return
     * @author: YM10095
     * @date:	2017年7月29日 下午11:16:42
     */
    @RequestMapping("/toEdit")
    public String toEdit() {
        SysUserVo form = this.getFormValues(SysUserVo.class);
        SysUser bean = sysUserService.getSysUser(form.getId());
        if(bean == null){
            this.setError("该用户不存在");
            return OPT_SUCCESS;
        }
        this.setBeanValue("bean", bean);
        
        //获取部门列表
        List<SysDept> sysDeptList = sysDeptService.getSysDeptList();
        this.setBeanValue("sysDeptList", sysDeptList);
        
        return ADD_PAGE;
    }
    
    /**
     * 更新用户
     * @return
     * @author: YM10095
     * @date:	2017年7月29日 下午11:16:51
     */
    @RequestMapping("/update")
    public String update() {
        SysUserVo form = this.getFormValues(SysUserVo.class);
        String msg = "修改用户信息";
        try{
            SysUser user = (SysUser) this.getSysUser();
            form.setOptcode(user.getUsercode());
            //
            sysUserService.update(form);
            
            this.setSuccess(msg + "成功");
        } catch(BasicException be){
            logger.error(be.getMessage());
            this.setError(msg + "失败:" + be.getMessage());
        } catch(Exception e){
            logger.error(e.getMessage());
            this.setError(msg + "失败:" + this.SYSTEM_ERROR);
        }
        
        return LIST_URL;
    }
    
    /**
     * 删除用户
     * @return
     * @author: YM10095
     * @date:	2017年7月29日 下午11:17:00
     */
    @RequestMapping("/delete")
    public String delete() {
        SysUserVo form = this.getFormValues(SysUserVo.class);
        String msg = "删除用户";
        try{
            SysUser user = (SysUser) this.getSysUser();
            form.setOptcode(user.getUsercode());
            //
            sysUserService.delete(form.getId());
            
            this.setSuccess(msg + "成功");
        } catch(BasicException be){
            logger.error(be.getMessage());
            this.setError(msg + "失败:" + be.getMessage());
        } catch(Exception e){
            logger.error(e.getMessage());
            this.setError(msg + "失败:" + this.SYSTEM_ERROR);
        }
        
        return LIST_URL;
    }
    
    /**
     * 设置用户权限页面
     * @return
     * @author: YM10095
     * @date:	2017年8月14日 下午1:54:45
     */
    @RequestMapping("/toSetUserQx")
    public String toSetUserQx() {
        SysUserVo form = this.getFormValues(SysUserVo.class);
        SysUser bean = sysUserService.getSysUser(form.getId());
        if(bean == null){
            this.setError("该用户不存在");
            return OPT_SUCCESS;
        }
        this.setBeanValue("bean", bean);
        
        //
        SysSystem sysSystem = sysUserService.getQxSysSystem(bean.getUsercode(), SysConstants.SYS_DEFAULT_SYSCODE_KEY);
        this.setBeanValue("qxsystem", sysSystem);
        
        return SET_USERQX_PAGE;
    }
    
    /**
     * 设置用户权限
     * @return
     * @author: YM10095
     * @date:	2017年8月14日 下午1:54:41
     */
    @RequestMapping("/doSetUserQx")
    public String doSetUserQx() {
        SysUserVo form = this.getFormValues(SysUserVo.class);
        String msg = "设置用户权限";
        try{
            //
            sysUserService.doSetUserQx(form);
            
            this.setSuccess(msg + "成功");
        } catch(BasicException be){
            logger.error(be.getMessage());
            this.setError(msg + "失败:" + be.getMessage());
        } catch(Exception e){
            logger.error(e.getMessage());
            this.setError(msg + "失败:" + this.SYSTEM_ERROR);
        }
        
        return OPT_SUCCESS;
    }
    
}
