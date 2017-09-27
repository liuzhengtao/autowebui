package com.yuminsoft.com.autoweb.dept.controller;

import java.util.List;

import com.yuminsoft.com.autoweb.base.exception.BasicException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.yuminsoft.com.autoweb.base.controller.BasicController;
import com.yuminsoft.com.autoweb.common.page.Page;
import com.yuminsoft.com.autoweb.dept.form.SysDeptVo;
import com.yuminsoft.com.autoweb.dept.model.SysDept;
import com.yuminsoft.com.autoweb.dept.service.SysDeptService;
import com.yuminsoft.com.autoweb.user.model.SysUser;

/**
 * 
 * FileName:    DeptController.java  
 * @author: YM10095
 * @version:    1.0  
 * @date:   	2017年7月29日 下午10:33:00
 */
@Controller
@RequestMapping("/dept")
public class DeptController extends BasicController {
    private final String MAIN_PATH = "/pages/dept/";
    private final String LIST_PAGE = MAIN_PATH + "deptList";
    private final String ADD_PAGE = MAIN_PATH + "deptAdd";
    
    private final String LIST_URL = "redirect:/dept/list";
    
    @Autowired  
    private SysDeptService sysDeptService;
    
    /**
     * 部门列表
     * @return
     * @author: YM10095
     * @date:	2017年7月17日 下午3:06:02
     */
    @RequestMapping("/list")
    public String list() {
        SysDeptVo form = this.getFormValues(SysDeptVo.class);
        Page<SysDept> page = sysDeptService.getPage(form);
        this.savePageInfo(page, form);
        
        return LIST_PAGE;
    }
    
    /**
     * 新增部门页面
     * @return
     * @author: YM10095
     * @date:	2017年7月17日 下午3:09:57
     */
    @RequestMapping("/toAdd")
    public String toAdd() {
        //获取部门列表
        List<SysDept> sysDeptList = sysDeptService.getSysDeptList();
        this.setBeanValue("sysDeptList", sysDeptList);
        
        return ADD_PAGE;
    }
    
    /**
     * 新增
     * @return
     * @author: YM10095
     * @date:	2017年7月17日 下午3:10:07
     */
    @RequestMapping("/save")
    public String save() {
        SysDeptVo form = this.getFormValues(SysDeptVo.class);
        String msg = "新增部门信息";
        try{
            SysUser user = (SysUser) this.getSysUser();
            form.setOptcode(user.getUsercode());
            //
            sysDeptService.save(form);
            
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
     * 编辑部门页面
     * @return
     * @author: YM10095
     * @date:	2017年7月17日 下午3:10:26
     */
    @RequestMapping("/toEdit")
    public String toEdit() {
        SysDeptVo form = this.getFormValues(SysDeptVo.class);
        SysDept bean = sysDeptService.getSysDept(form.getId());
        if(bean == null){
            this.setError("该部门不存在");
            return OPT_SUCCESS;
        }
        
        SysDept pSysDept = sysDeptService.getSysDept(bean.getPdeptcode());
        if(pSysDept != null){
            //上级部门名称
            bean.setPdeptname(pSysDept.getDeptname());
        }
        
        this.setBeanValue("bean", bean);
        
        return ADD_PAGE;
    }
    
    /**
     * 更新部门
     * @return
     * @author: YM10095
     * @date:	2017年7月17日 下午3:10:32
     */
    @RequestMapping("/update")
    public String update() {
        SysDeptVo form = this.getFormValues(SysDeptVo.class);
        String msg = "修改部门信息";
        try{
            SysUser user = (SysUser) this.getSysUser();
            form.setOptcode(user.getUsercode());
            //
            sysDeptService.update(form);
            
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
     * 删除部门
     * @return
     * @author: YM10095
     * @date:	2017年7月17日 下午3:10:37
     */
    @RequestMapping("/delete")
    public String delete() {
        SysDeptVo form = this.getFormValues(SysDeptVo.class);
        String msg = "删除部门";
        try{
            SysUser user = (SysUser) this.getSysUser();
            form.setOptcode(user.getUsercode());
            //
            sysDeptService.delete(form.getId());
            
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
    
}
