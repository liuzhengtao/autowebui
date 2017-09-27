package com.yuminsoft.com.autoweb.selenium.controller;

import java.util.ArrayList;
import java.util.List;

import com.yuminsoft.com.autoweb.base.exception.BasicException;
import com.yuminsoft.com.autoweb.common.page.Page;
import com.yuminsoft.com.autoweb.selenium.form.SlnmLocatorTempVo;
import com.yuminsoft.com.autoweb.selenium.model.SlnmLocatorTemp;
import com.yuminsoft.com.autoweb.selenium.service.SlnmPageTempService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.yuminsoft.com.autoweb.base.controller.BasicController;
import com.yuminsoft.com.autoweb.common.util.StringUtil;
import com.yuminsoft.com.autoweb.selenium.form.SlnmPageTempVo;
import com.yuminsoft.com.autoweb.selenium.model.SlnmPageTemp;
import com.yuminsoft.com.autoweb.user.model.SysUser;

/**
 * 页面模板管理
 * Filename:  	SlnmPageTempController.java  
 * @author:    	tutu
 * @version:   	1.0  
 * @date:      	2017年8月8日 下午1:44:14
 */
@Controller
@RequestMapping("/slnmpagetemp")
public class SlnmPageTempController extends BasicController {
    private final String MAIN_PATH = "/pages/slnmpagetemp/";
    private final String LIST_PAGE = MAIN_PATH + "slnmpagetempList";
    private final String ADD_PAGE = MAIN_PATH + "slnmpagetempAdd";    
    private final String COPY_SLNMPAGE_PAGE = MAIN_PATH + "slnmpagetempCopy";//
    
    private final String ADD_SLNMLOCATOR_PAGE = MAIN_PATH + "slnmlocatortempAdd";//
    private final String COPY_SLNMLOCATOR_PAGE = MAIN_PATH + "slnmlocatortempCopy";//
    private final String MOVE_SLNMLOCATOR_PAGE = MAIN_PATH + "slnmlocatortempMove";//
    
    private final String LIST_URL = "redirect:/slnmpagetemp/list";
    
    @Autowired
    protected SlnmPageTempService slnmPageTempService;
    
    /**
     * 页面模板列表
     * @return
     * @author: YM10095
     * @date:	2017年8月8日 上午11:14:18
     */
    @RequestMapping("/list")
    public String list() {
        SlnmPageTempVo form = this.getFormValues(SlnmPageTempVo.class);
        SysUser user = (SysUser) this.getSysUser();
        form.setOptcode(user.getUsercode());
        Page<SlnmPageTemp> page = slnmPageTempService.getPage(form);
        this.savePageInfo(page, form);
        
        return LIST_PAGE;
    }
    
    /**
     * 新增页面模板页面
     * @return
     * @author: YM10095
     * @date:	2017年8月8日 上午11:14:42
     */
    @RequestMapping("/toAdd")
    public String toAdd() {
        
        return ADD_PAGE;
    }
    
    /**
     * 新增页面模板
     * @return
     * @author: YM10095
     * @date:	2017年8月8日 上午11:15:04
     */
    @RequestMapping("/save")
    public String save() {        
        SlnmPageTempVo form = this.getFormValues(SlnmPageTempVo.class);
        String msg = "新增页面模板";
        try{
            SysUser user = (SysUser) this.getSysUser();
            form.setOptcode(user.getUsercode());
            //
            slnmPageTempService.save(form);
            
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
    
    /**
     * 编辑页面模板页面
     * @return
     * @author: YM10095
     * @date:	2017年8月8日 上午11:15:14
     */
    @RequestMapping("/toEdit")
    public String toEdit() {
        SlnmPageTempVo form = this.getFormValues(SlnmPageTempVo.class);
        SlnmPageTemp bean = slnmPageTempService.getSlnmPageTemp(form.getId());
        if(bean == null){
            this.setError("该页面模板不存在");
            return OPT_SUCCESS;
        }
        this.setBeanValue("bean", bean);
        
        return ADD_PAGE;
    }
    
    /**
     * 更新页面模板
     * @return
     * @author: YM10095
     * @date:	2017年8月8日 上午11:15:26
     */
    @RequestMapping("/update")
    public String update() {        
        SlnmPageTempVo form = this.getFormValues(SlnmPageTempVo.class);
        String msg = "更新页面模板";
        try{
            SysUser user = (SysUser) this.getSysUser();
            form.setOptcode(user.getUsercode());
            //
            slnmPageTempService.update(form);
            
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
    
    /**
     * 删除页面模板
     * @return
     * @author: YM10095
     * @date:	2017年8月8日 上午11:15:40
     */
    @RequestMapping("/delete")
    public String delete() {
        SlnmPageTempVo form = this.getFormValues(SlnmPageTempVo.class);
        String msg = "删除页面模板";
        try{
            //
            slnmPageTempService.delete(form.getId());
            
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
     * 复制页面模板页面
     * @return
     * @author: YM10095
     * @date:	2017年8月8日 上午11:20:04
     */
    @RequestMapping("/toCopyPage")
    public String toCopyPage() {
        SlnmPageTempVo form = this.getFormValues(SlnmPageTempVo.class);
        SlnmPageTemp bean = slnmPageTempService.getSlnmPageTemp(form.getId());
        if(bean == null){
            this.setError("该页面模板不存在");
            return OPT_SUCCESS;
        }
        this.setBeanValue("bean", bean);
        
        return COPY_SLNMPAGE_PAGE;
    }
    
    /**
     * 复制页面模板
     * @return
     * @author: YM10095
     * @date:	2017年8月8日 上午11:20:10
     */
    @RequestMapping("/doCopyPage")
    public String doCopyPage() {
        SlnmPageTempVo form = this.getFormValues(SlnmPageTempVo.class);
        String msg = "复制页面模板";
        try{
            SysUser user = (SysUser) this.getSysUser();
            form.setOptcode(user.getUsercode());
            //
            slnmPageTempService.doCopyPage(form);
            
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
    
    /**
     * 移动页面模板顺序
     * @return
     * @author: YM10095
     * @date:	2017年8月8日 上午11:20:19
     */
    @RequestMapping("/changePageOrder")
    public String changePageOrder() {
        String id = this.getParameter("id");
        String changedid = this.getParameter("changedid");
        String msg = "移动页面模板";
        try{
            //
            slnmPageTempService.changePageOrder(new Integer(id),new Integer(changedid));
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
     * 新增元素模板页面
     * @return
     * @author: YM10095
     * @date:	2017年8月8日 上午11:20:50
     */
    @RequestMapping("/toAddLocator")
    public String toAddLocator() {
        SlnmLocatorTempVo form = this.getFormValues(SlnmLocatorTempVo.class);
        if(StringUtil.isNull(form.getPagecode())){
            this.setError("页面模板代码为空");
            return OPT_SUCCESS;
        }
        SlnmPageTemp slnmPage = slnmPageTempService.getSlnmPageTemp(form.getPagecode());
        if(slnmPage == null){
            this.setError("页面模板不存在");
            return OPT_SUCCESS;
        }
        this.setBeanValue("slnmPage", slnmPage);
        
        return ADD_SLNMLOCATOR_PAGE;
    }
    
    /**
     * 新增元素模板
     * @return
     * @author: YM10095
     * @date:	2017年8月8日 上午11:21:23
     */
    @RequestMapping("/doAddLocator")
    public String doAddLocator() {        
        SlnmLocatorTempVo form = this.getFormValues(SlnmLocatorTempVo.class);
        String msg = "新增元素";
        try{
            //
            slnmPageTempService.doAddLocator(form);
            
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
    
    /**
     * 编辑元素模板页面
     * @return
     * @author: YM10095
     * @date:	2017年8月8日 上午11:21:30
     */
    @RequestMapping("/toEditLocator")
    public String toEditLocator() {
        SlnmLocatorTempVo form = this.getFormValues(SlnmLocatorTempVo.class);
        SlnmLocatorTemp bean = slnmPageTempService.getSlnmLocatorTemp(form.getId());
        if(bean == null){
            this.setError("该元素不存在");
            return OPT_SUCCESS;
        }
        this.setBeanValue("bean", bean);
        
        SlnmPageTemp slnmPage = slnmPageTempService.getSlnmPageTemp(bean.getPagecode());
        if(slnmPage == null){
            this.setError("页面模板不存在");
            return OPT_SUCCESS;
        }
        this.setBeanValue("slnmPage", slnmPage);
        
        return ADD_SLNMLOCATOR_PAGE;
    }
    
    /**
     * 更新元素模板
     * @return
     * @author: YM10095
     * @date:	2017年8月8日 上午11:21:46
     */
    @RequestMapping("/doUpdateLocator")
    public String doUpdateLocator() {
        SlnmLocatorTempVo form = this.getFormValues(SlnmLocatorTempVo.class);
        String msg = "更新元素";
        try{
            //
            slnmPageTempService.doUpdateLocator(form);
            
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
    
    /**
     * 复制元素模板页面
     * @return
     * @author: YM10095
     * @date:	2017年8月8日 上午11:21:58
     */
    @RequestMapping("/toCopyLocator")
    public String toCopyLocator() {
        SlnmLocatorTempVo form = this.getFormValues(SlnmLocatorTempVo.class);
        SlnmLocatorTemp bean = slnmPageTempService.getSlnmLocatorTemp(form.getId());
        if(bean == null){
            this.setError("该元素不存在");
            return OPT_SUCCESS;
        }
        this.setBeanValue("bean", bean);
        
        List<SlnmPageTemp> slnmPageList = slnmPageTempService.getSlnmPageTempList();
        if(slnmPageList == null || slnmPageList.isEmpty()){
            this.setError("页面模板不存在");
            return OPT_SUCCESS;
        }
        this.setBeanValue("slnmPageList", slnmPageList);
        
        return COPY_SLNMLOCATOR_PAGE;
    }
    
    /**
     * 复制元素模板
     * @return
     * @author: YM10095
     * @date:	2017年8月8日 上午11:22:12
     */
    @RequestMapping("/doCopyLocator")
    public String doCopyLocator() {
        SlnmLocatorTempVo form = this.getFormValues(SlnmLocatorTempVo.class);
        String msg = "复制元素";
        try{
            //
            slnmPageTempService.doCopyLocator(form);
            
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
    
    /**
     * 移动元素模板页面
     * @return
     * @author: YM10095
     * @date:	2017年8月8日 上午11:22:19
     */
    @RequestMapping("/toMoveLocator")
    public String toMoveLocator() {
        SlnmLocatorTempVo form = this.getFormValues(SlnmLocatorTempVo.class);
        SlnmLocatorTemp bean = slnmPageTempService.getSlnmLocatorTemp(form.getId());
        if(bean == null){
            this.setError("该元素不存在");
            return OPT_SUCCESS;
        }
        this.setBeanValue("bean", bean);
        
        SlnmPageTemp slnmPage = slnmPageTempService.getSlnmPageTemp(bean.getPagecode());
        if(slnmPage == null){
            this.setError("页面模板不存在");
            return OPT_SUCCESS;
        }
        List<SlnmPageTemp> slnmPageList0 = slnmPageTempService.getSlnmPageTempList();
        if(slnmPageList0 == null || slnmPageList0.isEmpty()){
            this.setError("页面模板不存在");
            return OPT_SUCCESS;
        }
        List<SlnmPageTemp> slnmPageList = new ArrayList<SlnmPageTemp>();
        for(SlnmPageTemp slnmPage0 : slnmPageList0){
            if(!slnmPage0.getPagecode().equals(slnmPage.getPagecode())){
                slnmPageList.add(slnmPage0);
            }
        }
        if(slnmPageList.isEmpty()){
            this.setError("没有可移动的页面模板");
            return OPT_SUCCESS;
        }
        this.setBeanValue("slnmPageList", slnmPageList);
        
        return MOVE_SLNMLOCATOR_PAGE;
    }
    
    /**
     * 移动元素模板
     * @return
     * @author: YM10095
     * @date:	2017年8月8日 上午11:22:42
     */
    @RequestMapping("/doMoveLocator")
    public String doMoveLocator() {
        SlnmLocatorTempVo form = this.getFormValues(SlnmLocatorTempVo.class);
        String msg = "移动元素";
        try{
            //
            slnmPageTempService.doMoveLocator(form);
            
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
    
    /**
     * 删除元素模板
     * @return
     * @author: YM10095
     * @date:	2017年8月8日 上午11:22:49
     */
    @RequestMapping("/doDeleteLocator")
    public String doDeleteLocator() {
        SlnmLocatorTempVo form = this.getFormValues(SlnmLocatorTempVo.class);
        String msg = "删除元素";
        try{
            //
            slnmPageTempService.doDeleteLocator(form.getId());
            
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
     * 移动元素模板顺序
     * @return
     * @author: YM10095
     * @date:	2017年8月8日 上午11:23:02
     */
    @RequestMapping("/changeLocatorOrder")
    public String changeLocatorOrder() {
        String id = this.getParameter("id");
        String changedid = this.getParameter("changedid");
        String msg = "移动元素";
        try{
            //
            slnmPageTempService.changeLocatorOrder(new Integer(id),new Integer(changedid));
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
