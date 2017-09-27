package com.yuminsoft.com.autoweb.selenium.controller;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.yuminsoft.com.autoweb.base.exception.BasicException;
import com.yuminsoft.com.autoweb.common.page.Page;
import com.yuminsoft.com.autoweb.selenium.form.SlnmCaseVo;
import com.yuminsoft.com.autoweb.selenium.form.SlnmChapterVo;
import com.yuminsoft.com.autoweb.selenium.form.SlnmPageVo;
import com.yuminsoft.com.autoweb.selenium.model.SlnmCase;
import com.yuminsoft.com.autoweb.selenium.model.SlnmPage;
import com.yuminsoft.com.autoweb.selenium.service.SlnmCaseService;
import com.yuminsoft.com.autoweb.selenium.util.ExcelUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yuminsoft.com.autoweb.base.controller.BasicController;
import com.yuminsoft.com.autoweb.common.util.JaxbUtil;
import com.yuminsoft.com.autoweb.common.util.StringUtil;
import com.yuminsoft.com.autoweb.selenium.bean.SlnmBean;
import com.yuminsoft.com.autoweb.selenium.form.SlnmLocatorVo;
import com.yuminsoft.com.autoweb.selenium.model.SlnmChapter;
import com.yuminsoft.com.autoweb.selenium.model.SlnmLocator;
import com.yuminsoft.com.autoweb.selenium.model.SlnmPageTemp;
import com.yuminsoft.com.autoweb.selenium.service.SlnmPageTempService;
import com.yuminsoft.com.autoweb.user.model.SysUser;

/**
 * 用例管理
 * FileName:    SlnmCaseController.java  
 * @author: YM10095
 * @version:    1.0  
 * @date:   	2017年7月30日 下午8:31:39
 */
@Controller
@RequestMapping("/slnmcase")
public class SlnmCaseController extends BasicController {
    private final String MAIN_PATH = "/pages/slnmcase/";
    private final String LIST_PAGE = MAIN_PATH + "slnmcaseList";
    private final String ADD_PAGE = MAIN_PATH + "slnmcaseAdd";
    private final String IMPORT_CASE_PAGE = MAIN_PATH + "slnmcaseImport";
    
    private final String LIST_CHAPTER_PAGE = MAIN_PATH + "slnmchapterList";
    private final String ADD_CHAPTER_PAGE = MAIN_PATH + "slnmchapterAdd";
    private final String COPY_CHAPTER_SLNMPAGE_PAGE = MAIN_PATH + "slnmchapterCopy";//
    
    private final String ADD_SLNMPAGE_PAGE = MAIN_PATH + "slnmpageAdd";//
    private final String COPY_SLNMPAGE_PAGE = MAIN_PATH + "slnmpageCopy";//
    private final String ADD_SLNMLOCATOR_PAGE = MAIN_PATH + "slnmlocatorAdd";//
    private final String COPY_SLNMLOCATOR_PAGE = MAIN_PATH + "slnmlocatorCopy";//
    private final String MOVE_SLNMLOCATOR_PAGE = MAIN_PATH + "slnmlocatorMove";//
    
    private final String CHOOSE_PAGETEMP__PAGE = MAIN_PATH + "slnmpageChooseTemp";//
    
    private final String LIST_URL = "redirect:/slnmcase/list";
    private final String CHAPTER_LIST_URL = "redirect:/slnmcase/chapterlist";
    
    @Autowired
    protected SlnmCaseService slnmCaseService;
    @Autowired
    protected SlnmPageTempService slnmPageTempService;
    
    /**
     * 用例列表
     * @return
     * @author: YM10095
     * @date:	2017年7月29日 下午10:37:10
     */
    @RequestMapping("/list")
    public String list() {        
        SlnmCaseVo form = this.getFormValues(SlnmCaseVo.class);
        SysUser user = (SysUser) this.getSysUser();
        form.setOptcode(user.getUsercode());
        Page<SlnmCase> page = slnmCaseService.getPage(form);
        this.savePageInfo(page, form);
        
        return LIST_PAGE;
    }
    
    /**
     * 新增用例页面
     * @return
     * @author: YM10095
     * @date:	2017年7月29日 下午10:37:17
     */
    @RequestMapping("/toAdd")
    public String toAdd() {
        
        return ADD_PAGE;
    }
    
    /**
     * 新增用例
     * @return
     * @author: YM10095
     * @date:	2017年7月29日 下午10:37:26
     */
    @RequestMapping("/save")
    public String save() {        
        SlnmCaseVo form = this.getFormValues(SlnmCaseVo.class);
        String msg = "新增用例";
        try{
            SysUser user = (SysUser) this.getSysUser();
            form.setOptcode(user.getUsercode());
            //
            slnmCaseService.save(form);
            
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
     * 编辑用例页面
     * @return
     * @author: YM10095
     * @date:	2017年7月29日 下午10:37:36
     */
    @RequestMapping("/toEdit")
    public String toEdit() {
        SlnmCaseVo form = this.getFormValues(SlnmCaseVo.class);
        SlnmCase bean = slnmCaseService.getSlnmCase(form.getId());
        if(bean == null){
            this.setError("该用例不存在");
            return OPT_SUCCESS;
        }
        this.setBeanValue("bean", bean);
        
        return ADD_PAGE;
    }
    
    /**
     * 更新用例
     * @return
     * @author: YM10095
     * @date:	2017年7月29日 下午10:38:15
     */
    @RequestMapping("/update")
    public String update() {        
        SlnmCaseVo form = this.getFormValues(SlnmCaseVo.class);
        String msg = "更新用例";
        try{
            SysUser user = (SysUser) this.getSysUser();
            form.setOptcode(user.getUsercode());
            //
            slnmCaseService.update(form);
            
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
     * 导出用例至xml文件
     * @author: YM10095
     * @date:	2017年8月1日 上午11:05:07
     */
    @RequestMapping("/exportCase2Xml")
    @ResponseBody
    public void exportCase2Xml() {
        SlnmCaseVo form = this.getFormValues(SlnmCaseVo.class);
        String msg = "导出Xml";
        try{
            SlnmBean bean = slnmCaseService.getSlnmBean(form);
            if(bean.getCasebeanlist() == null || bean.getCasebeanlist().isEmpty()){
                throw new BasicException("用例不存在");
            }
            
            //获取项目相对路径
            String relativeProjectPath = System.getProperty("user.dir");
            String relativeFilePath = relativeProjectPath.substring(0,relativeProjectPath.lastIndexOf(File.separator));
            //文件保存真实路径
            String realFilePath = relativeFilePath + File.separator + "upload" + File.separator + "temp" + File.separator;
            
            //保存数据文件路径
            String datafilepath = realFilePath;
            //数据文件名称
            String datafilename = StringUtil.getGuid() + ".xml";
            
            //生成xml文件
            JaxbUtil.transBeanToXml(bean, datafilepath, datafilename);
            
            String newfilename = "用例列表-共"+bean.getCasebeanlist().size()+"个用例.xml";
            if(bean.getCasebeanlist().size() == 1){
                newfilename = "用例-"+bean.getCasebeanlist().get(0).getCasename()+".xml";
            }
            //下载xml文件
            this.download(datafilepath + datafilename, newfilename);
            
            logger.info(msg + "成功:" + datafilepath + datafilename);
        } catch(BasicException be){
            logger.error(be.getMessage());
        } catch(Exception e){
            e.printStackTrace();
            logger.error(e.getMessage());
        }
    }
    
    /**
     * 导入用例页面
     * @return
     * @author: YM10095
     * @date:	2017年7月31日 下午4:04:26
     */
    @RequestMapping("/toImportCase")
    public String toImportCase() {
        
        return IMPORT_CASE_PAGE;
    }
    
    /**
     * 导入用例
     * @return
     * @author: YM10095
     * @date:	2017年7月31日 下午4:04:43
     */
    @RequestMapping("/doImportCase")
    public String doImportCase() {        
        SlnmCaseVo form = this.getFormValues(SlnmCaseVo.class);
        String msg = "导入用例";
        try{
            //获取项目相对路径
            String relativeProjectPath = System.getProperty("user.dir");
            String relativeFilePath = relativeProjectPath.substring(0,relativeProjectPath.lastIndexOf(File.separator));
            //
            String datafilepath = this.getParameter("datafilepath");
            //文件保存真实路径
            String realFilePath = relativeFilePath + File.separator + datafilepath;
            //将xml文件转换成bean
            SlnmBean slnmbean = JaxbUtil.transXmlToBean(SlnmBean.class, realFilePath);
            
            SysUser user = (SysUser) this.getSysUser();
            form.setOptcode(user.getUsercode());
            //
            slnmCaseService.doImportCase(form, slnmbean);
            
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
     * 删除用例
     * @return
     * @author: YM10095
     * @date:	2017年7月29日 下午10:40:24
     */
    @RequestMapping("/delete")
    public String delete() {
        SlnmCaseVo form = this.getFormValues(SlnmCaseVo.class);
        String msg = "删除用例";
        try{
            //
            slnmCaseService.delete(form.getId());
            
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
    
    @RequestMapping("/chapterlist")
    public String chapterlist() {        
        SlnmChapterVo form = this.getFormValues(SlnmChapterVo.class);
        if(StringUtil.isNull(form.getCasecode())){
            this.setError("用例代码为空");
            return LIST_URL;
        }
        SlnmCase slnmCase = slnmCaseService.getSlnmCase(form.getCasecode());
        if(slnmCase == null){
            this.setError("用例不存在");
            return OPT_SUCCESS;
        }
        this.setBeanValue("slnmCase", slnmCase);
        
        Page<SlnmChapter> page = slnmCaseService.getChapterPage(form);
        this.savePageInfo(page, form);
        
        return LIST_CHAPTER_PAGE;
    }
    
    @RequestMapping("/toAddChapter")
    public String toAddChapter() {
        SlnmChapterVo form = this.getFormValues(SlnmChapterVo.class);
        if(StringUtil.isNull(form.getCasecode())){
            this.setError("用例代码为空");
            return OPT_SUCCESS;
        }
        SlnmCase slnmCase = slnmCaseService.getSlnmCase(form.getCasecode());
        if(slnmCase == null){
            this.setError("用例不存在");
            return OPT_SUCCESS;
        }
        this.setBeanValue("slnmCase", slnmCase);
        
        return ADD_CHAPTER_PAGE;
    }
    
    @RequestMapping("/doAddChapter")
    public String doAddChapter() {        
        SlnmChapterVo form = this.getFormValues(SlnmChapterVo.class);
        String msg = "新增章节";
        try{
            SysUser user = (SysUser) this.getSysUser();
            form.setOptcode(user.getUsercode());
            slnmCaseService.doAddChapter(form);
            
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
    
    @RequestMapping("/toEditChapter")
    public String toEditChapter() {
        SlnmChapterVo form = this.getFormValues(SlnmChapterVo.class);
        SlnmChapter bean = slnmCaseService.getSlnmChapter(form.getId());
        System.out.println(new Gson().toJson(bean));
        if(bean == null){
            this.setError("该章节不存在");
            return OPT_SUCCESS;
        }
        this.setBeanValue("bean", bean);
        
        SlnmCase slnmCase = slnmCaseService.getSlnmCase(bean.getCasecode());
        if(slnmCase == null){
            this.setError("用例不存在");
            return OPT_SUCCESS;
        }
        this.setBeanValue("slnmCase", slnmCase);
        
        return ADD_CHAPTER_PAGE;
    }
    
    @RequestMapping("/doUpdateChapter")
    public String doUpdateChapter() {        
        SlnmChapterVo form = this.getFormValues(SlnmChapterVo.class);
        String msg = "更新章节";
        try{
            SysUser user = (SysUser) this.getSysUser();
            form.setOptcode(user.getUsercode());
            //
            slnmCaseService.doUpdateChapter(form);
            
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
    
    @RequestMapping("/toCopyChapter")
    public String toCopyChapter() {
        SlnmChapterVo form = this.getFormValues(SlnmChapterVo.class);
        SlnmChapter bean = slnmCaseService.getSlnmChapter(form.getId());
        if(bean == null){
            this.setError("该章节不存在");
            return OPT_SUCCESS;
        }
        this.setBeanValue("bean", bean);
        
        //获取用例列表
        List<SlnmCase> slnmcaselist = slnmCaseService.getSlnmCaseList();        
        this.setBeanValue("slnmcaselist", slnmcaselist);
        
        return COPY_CHAPTER_SLNMPAGE_PAGE;
    }
    
    @RequestMapping("/doCopyChapter")
    public String doCopyChapter() {
        SlnmChapterVo form = this.getFormValues(SlnmChapterVo.class);
        String msg = "复制章节";
        try{
            SysUser user = (SysUser) this.getSysUser();
            form.setOptcode(user.getUsercode());
            //
            slnmCaseService.doCopyChapter(form);
            
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
    
    @RequestMapping("/doDeleteChapter")
    public String doDeleteChapter() {
        SlnmChapterVo form = this.getFormValues(SlnmChapterVo.class);
        String msg = "删除章节";
        try{
            //
            slnmCaseService.doDeleteChapter(form.getId());
            
            this.setSuccess(msg + "成功");
        } catch(BasicException be){
            logger.error(be.getMessage());
            this.setError(msg + "失败:" + be.getMessage());
        } catch(Exception e){
            logger.error(e.getMessage());
            this.setError(msg + "失败:" + this.SYSTEM_ERROR);
        }
        
        return CHAPTER_LIST_URL + "?casecode=" + form.getCasecode();
    }
    
    @RequestMapping("/changeChapterOrder")
    public String changeChapterOrder() {
        SlnmChapterVo form = this.getFormValues(SlnmChapterVo.class);
        String id = this.getParameter("id");
        String changedid = this.getParameter("changedid");
        String msg = "移动章节顺序";
        try{
            //
            slnmCaseService.changeChapterOrder(new Integer(id),new Integer(changedid));
        } catch(BasicException be){
            logger.error(be.getMessage());
            this.setError(msg + "失败:" + be.getMessage());
        } catch(Exception e){
            logger.error(e.getMessage());
            this.setError(msg + "失败:" + this.SYSTEM_ERROR);
        }
        
        return CHAPTER_LIST_URL + "?casecode=" + form.getCasecode();
    }
    
    /**
     * 新增页面页面
     * @return
     * @author: YM10095
     * @date:	2017年7月29日 下午10:40:31
     */
    @RequestMapping("/toAddPage")
    public String toAddPage() {
        SlnmPageVo form = this.getFormValues(SlnmPageVo.class);
        if(StringUtil.isNull(form.getChaptercode())){
            this.setError("章节代码代码为空");
            return OPT_SUCCESS;
        }
        SlnmChapter slnmChapter = slnmCaseService.getSlnmChapter(form.getChaptercode());
        if(slnmChapter == null){
            this.setError("章节不存在");
            return OPT_SUCCESS;
        }
        this.setBeanValue("slnmChapter", slnmChapter);
        
        return ADD_SLNMPAGE_PAGE;
    }
    
    /**
     * 新增页面
     * @return
     * @author: YM10095
     * @date:	2017年7月29日 下午10:40:37
     */
    @RequestMapping("/doAddPage")
    public String doAddPage() {        
        SlnmPageVo form = this.getFormValues(SlnmPageVo.class);
        String msg = "新增页面";
        try{
            SysUser user = (SysUser) this.getSysUser();
            form.setOptcode(user.getUsercode());
            //
            slnmCaseService.doAddPage(form);
            
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
     * 
     * @return
     * @author: YM10095
     * @date:	2017年8月11日 下午2:12:49
     */
    @RequestMapping("/toChoosePageTemp")
    public String toChoosePageTemp() {
        SlnmPageVo form = this.getFormValues(SlnmPageVo.class);
        if(StringUtil.isNull(form.getChaptercode())){
            this.setError("章节代码代码为空");
            return OPT_SUCCESS;
        }
        SlnmChapter slnmChapter = slnmCaseService.getSlnmChapter(form.getChaptercode());
        if(slnmChapter == null){
            this.setError("章节不存在");
            return OPT_SUCCESS;
        }
        this.setBeanValue("slnmChapter", slnmChapter);
        
        //
        List<SlnmPageTemp> slnmPageTempList = slnmPageTempService.getSlnmPageTempList();
        this.setBeanValue("slnmPageTempList", slnmPageTempList);
        
        return CHOOSE_PAGETEMP__PAGE;
    }
    
    /**
     * 
     * @return
     * @author: YM10095
     * @date:	2017年8月11日 下午2:38:50
     */
    @RequestMapping("/doAddPageFromTemp")
    public String doAddPageFromTemp() {        
        SlnmPageVo form = this.getFormValues(SlnmPageVo.class);
        String msg = "选择页面模板新增页面";
        try{
            SysUser user = (SysUser) this.getSysUser();
            form.setOptcode(user.getUsercode());
            //
            slnmCaseService.doAddPageFromTemp(form);
            
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
     * 编辑页面页面
     * @return
     * @author: YM10095
     * @date:	2017年7月29日 下午10:40:44
     */
    @RequestMapping("/toEditPage")
    public String toEditPage() {
        SlnmPageVo form = this.getFormValues(SlnmPageVo.class);
        SlnmPage bean = slnmCaseService.getSlnmPage(form.getId());
        if(bean == null){
            this.setError("该页面不存在");
            return OPT_SUCCESS;
        }
        this.setBeanValue("bean", bean);
        
        SlnmChapter slnmChapter = slnmCaseService.getSlnmChapter(bean.getChaptercode());
        if(slnmChapter == null){
            this.setError("章节不存在");
            return OPT_SUCCESS;
        }
        this.setBeanValue("slnmChapter", slnmChapter);
        
        return ADD_SLNMPAGE_PAGE;
    }
    
    /**
     * 更新页面
     * @return
     * @author: YM10095
     * @date:	2017年7月29日 下午10:40:55
     */
    @RequestMapping("/doUpdatePage")
    public String doUpdatePage() {        
        SlnmPageVo form = this.getFormValues(SlnmPageVo.class);
        String msg = "更新页面";
        try{
            SysUser user = (SysUser) this.getSysUser();
            form.setOptcode(user.getUsercode());
            //
            slnmCaseService.doUpdatePage(form);
            
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
     * 保存页面至模板
     * @return
     * @author: YM10095
     * @date:   2017年8月11日 下午1:25:42
     */
    @RequestMapping("/doAddPageToTemp")
    public String doAddPageToTemp() {        
        SlnmPageVo form = this.getFormValues(SlnmPageVo.class);
        String msg = "保存页面至模板";
        try{
            SysUser user = (SysUser) this.getSysUser();
            form.setOptcode(user.getUsercode());
            //
            slnmCaseService.doAddPageToTemp(form);
            
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
     * 复制页面页面
     * @return
     * @author: YM10095
     * @date:	2017年7月29日 下午10:41:02
     */
    @RequestMapping("/toCopyPage")
    public String toCopyPage() {
        SlnmPageVo form = this.getFormValues(SlnmPageVo.class);
        SlnmPage bean = slnmCaseService.getSlnmPage(form.getId());
        if(bean == null){
            this.setError("该页面不存在");
            return OPT_SUCCESS;
        }
        this.setBeanValue("bean", bean);
        
        //
        SlnmChapter slnmChapter = slnmCaseService.getSlnmChapter(bean.getChaptercode());
        if(slnmChapter == null){
            this.setError("章节不存在");
            return OPT_SUCCESS;
        }
        
        //获取章节列表
        List<SlnmChapter> slnmchapterlist = slnmCaseService.getSlnmChapterList(slnmChapter.getCasecode());        
        this.setBeanValue("slnmchapterlist", slnmchapterlist);
        
        return COPY_SLNMPAGE_PAGE;
    }
    
    /**
     * 复制页面
     * @return
     * @author: YM10095
     * @date:	2017年7月29日 下午10:41:09
     */
    @RequestMapping("/doCopyPage")
    public String doCopyPage() {
        SlnmPageVo form = this.getFormValues(SlnmPageVo.class);
        String msg = "复制页面";
        try{
            SysUser user = (SysUser) this.getSysUser();
            form.setOptcode(user.getUsercode());
            //
            slnmCaseService.doCopyPage(form);
            
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
     * 生成数据模板
     * @return
     * @author: YM10095
     * @date:	2017年8月1日 上午11:05:19
     */
    @RequestMapping("/createDataFile")
    @ResponseBody
    public void createDataFile() {
        SlnmPageVo form = this.getFormValues(SlnmPageVo.class);
        String msg = "生成数据模板";        
        try{
            SlnmPage bean = slnmCaseService.getSlnmPage(form.getId());
            if(bean == null){
                throw new BasicException("生成数据模板失败:该页面不存在");
            }
            
            //获取项目相对路径
            String relativeProjectPath = System.getProperty("user.dir");
            String relativeFilePath = relativeProjectPath.substring(0,relativeProjectPath.lastIndexOf(File.separator));
            //文件保存真实路径
            String realFilePath = relativeFilePath + File.separator + "upload" + File.separator + "temp" + File.separator;
            
            //保存数据文件路径
            String datafilepath = realFilePath;
            //数据文件名称
            String datafilename = StringUtil.getGuid() + ".xls";
            
            //数据文件类型 1-Excel文件 2-Xml文件
            if(bean.getDatafiletype() == 1){
                //章节信息
                SlnmChapter slnmChapter = slnmCaseService.getSlnmChapter(bean.getChaptercode());
                if(slnmChapter == null){
                    throw new BasicException("章节不存在");
                }
                //用例信息
                SlnmCase slnmCase = slnmCaseService.getSlnmCase(slnmChapter.getCasecode());
                if(slnmCase == null){
                    throw new BasicException("用例不存在");
                }
                //定位器信息
                List<SlnmLocator> locatorlist = slnmCaseService.getSlnmLocatorList(bean.getPagecode());
                if(locatorlist == null || locatorlist.isEmpty()){
                    throw new BasicException("页面元素不存在");
                }
                
                //生成Excel文件
                bean.setLocatorlist(locatorlist);
                ExcelUtil.createDataExcel(datafilepath,datafilename,bean);
                
                String newfilename = slnmCase.getCasename() + "-" + slnmChapter.getChaptername() + "-" + bean.getPagename() + ".xls";
                //下载Excel文件
                this.download(datafilepath + datafilename, newfilename);
                
                logger.info(msg + "成功:" + datafilepath + datafilename);
            }else{
                throw new BasicException("暂时只支持Excel文件");
            }

        } catch(BasicException be){
            logger.error(be.getMessage());
        } catch(Exception e){
            e.printStackTrace();
            logger.error(e.getMessage());
        }
    }
    
    /**
     * 删除页面
     * @return
     * @author: YM10095
     * @date:	2017年7月10日 下午1:19:57
     */
    @RequestMapping("/doDeletePage")
    public String doDeletePage() {
        SlnmPageVo form = this.getFormValues(SlnmPageVo.class);
        String msg = "删除页面";
        try{
            //
            slnmCaseService.doDeletePage(form.getId());
            
            this.setSuccess(msg + "成功");
        } catch(BasicException be){
            logger.error(be.getMessage());
            this.setError(msg + "失败:" + be.getMessage());
        } catch(Exception e){
            logger.error(e.getMessage());
            this.setError(msg + "失败:" + this.SYSTEM_ERROR);
        }
        
        String casecode = this.getParameter("casecode");
        return CHAPTER_LIST_URL + "?casecode=" + casecode;
    }
    
    /**
     * 移动页面顺序
     * @return
     * @author: YM10095
     * @date:	2017年7月29日 下午10:41:17
     */
    @RequestMapping("/changePageOrder")
    public String changePageOrder() {
        String id = this.getParameter("id");
        String changedid = this.getParameter("changedid");
        String msg = "移动页面顺序";
        try{
            //
            slnmCaseService.changePageOrder(new Integer(id),new Integer(changedid));
        } catch(BasicException be){
            logger.error(be.getMessage());
            this.setError(msg + "失败:" + be.getMessage());
        } catch(Exception e){
            logger.error(e.getMessage());
            this.setError(msg + "失败:" + this.SYSTEM_ERROR);
        }
        
        String casecode = this.getParameter("casecode");
        return CHAPTER_LIST_URL + "?casecode=" + casecode;
    }
    
    /**
     * 新增元素页面
     * @return
     * @author: YM10095
     * @date:	2017年7月29日 下午10:41:24
     */
    @RequestMapping("/toAddLocator")
    public String toAddLocator() {
        SlnmLocatorVo form = this.getFormValues(SlnmLocatorVo.class);
        if(StringUtil.isNull(form.getPagecode())){
            this.setError("页面代码为空");
            return OPT_SUCCESS;
        }
        SlnmPage slnmPage = slnmCaseService.getSlnmPage(form.getPagecode());
        if(slnmPage == null){
            this.setError("页面不存在");
            return OPT_SUCCESS;
        }
        this.setBeanValue("slnmPage", slnmPage);
        
        return ADD_SLNMLOCATOR_PAGE;
    }
    
    /**
     * 新增元素
     * @return
     * @author: YM10095
     * @date:	2017年7月29日 下午10:41:31
     */
    @RequestMapping("/doAddLocator")
    public String doAddLocator() {        
        SlnmLocatorVo form = this.getFormValues(SlnmLocatorVo.class);
        String msg = "新增元素";
        try{
            //
            slnmCaseService.doAddLocator(form);
            
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
     * 编辑元素页面
     * @return
     * @author: YM10095
     * @date:	2017年7月29日 下午10:41:37
     */
    @RequestMapping("/toEditLocator")
    public String toEditLocator() {
        SlnmLocatorVo form = this.getFormValues(SlnmLocatorVo.class);
        SlnmLocator bean = slnmCaseService.getSlnmLocator(form.getId());
        if(bean == null){
            this.setError("该元素不存在");
            return OPT_SUCCESS;
        }
        this.setBeanValue("bean", bean);
        
        SlnmPage slnmPage = slnmCaseService.getSlnmPage(bean.getPagecode());
        if(slnmPage == null){
            this.setError("页面不存在");
            return OPT_SUCCESS;
        }
        this.setBeanValue("slnmPage", slnmPage);
        
        return ADD_SLNMLOCATOR_PAGE;
    }
    
    /**
     * 更新元素
     * @return
     * @author: YM10095
     * @date:	2017年7月29日 下午10:41:43
     */
    @RequestMapping("/doUpdateLocator")
    public String doUpdateLocator() {
        SlnmLocatorVo form = this.getFormValues(SlnmLocatorVo.class);
        String msg = "更新元素";
        try{
            //
            slnmCaseService.doUpdateLocator(form);
            
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
     * 复制元素页面
     * @return
     * @author: YM10095
     * @date:	2017年7月29日 下午10:41:50
     */
    @RequestMapping("/toCopyLocator")
    public String toCopyLocator() {
        SlnmLocatorVo form = this.getFormValues(SlnmLocatorVo.class);
        SlnmLocator bean = slnmCaseService.getSlnmLocator(form.getId());
        if(bean == null){
            this.setError("该元素不存在");
            return OPT_SUCCESS;
        }
        this.setBeanValue("bean", bean);
        
        SlnmPage slnmPage = slnmCaseService.getSlnmPage(bean.getPagecode());
        if(slnmPage == null){
            this.setError("页面不存在");
            return OPT_SUCCESS;
        }
        List<SlnmPage> slnmPageList = slnmCaseService.getSlnmPageList(slnmPage.getChaptercode());
        if(slnmPageList == null || slnmPageList.isEmpty()){
            this.setError("页面不存在");
            return OPT_SUCCESS;
        }
        this.setBeanValue("slnmPageList", slnmPageList);
        
        return COPY_SLNMLOCATOR_PAGE;
    }
    
    /**
     * 复制元素
     * @return
     * @author: YM10095
     * @date:	2017年7月29日 下午10:41:57
     */
    @RequestMapping("/doCopyLocator")
    public String doCopyLocator() {
        SlnmLocatorVo form = this.getFormValues(SlnmLocatorVo.class);
        String msg = "复制元素";
        try{
            //
            slnmCaseService.doCopyLocator(form);
            
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
     * 移动元素页面
     * @return
     * @author: YM10095
     * @date:	2017年8月2日 下午6:18:17
     */
    @RequestMapping("/toMoveLocator")
    public String toMoveLocator() {
        SlnmLocatorVo form = this.getFormValues(SlnmLocatorVo.class);
        SlnmLocator bean = slnmCaseService.getSlnmLocator(form.getId());
        if(bean == null){
            this.setError("该元素不存在");
            return OPT_SUCCESS;
        }
        this.setBeanValue("bean", bean);
        
        SlnmPage slnmPage = slnmCaseService.getSlnmPage(bean.getPagecode());
        if(slnmPage == null){
            this.setError("页面不存在");
            return OPT_SUCCESS;
        }
        List<SlnmPage> slnmPageList0 = slnmCaseService.getSlnmPageList(slnmPage.getChaptercode());
        if(slnmPageList0 == null || slnmPageList0.isEmpty()){
            this.setError("页面不存在");
            return OPT_SUCCESS;
        }
        List<SlnmPage> slnmPageList = new ArrayList<SlnmPage>();
        for(SlnmPage slnmPage0 : slnmPageList0){
            if(!slnmPage0.getPagecode().equals(slnmPage.getPagecode())){
                slnmPageList.add(slnmPage0);
            }
        }
        if(slnmPageList.isEmpty()){
            this.setError("没有可移动的页面");
            return OPT_SUCCESS;
        }
        this.setBeanValue("slnmPageList", slnmPageList);
        
        return MOVE_SLNMLOCATOR_PAGE;
    }
    
    /**
     * 移动元素
     * @return
     * @author: YM10095
     * @date:	2017年8月2日 下午7:23:33
     */
    @RequestMapping("/doMoveLocator")
    public String doMoveLocator() {
        SlnmLocatorVo form = this.getFormValues(SlnmLocatorVo.class);
        String msg = "移动元素";
        try{
            //
            slnmCaseService.doMoveLocator(form);
            
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
     * 删除元素
     * @return
     * @author: YM10095
     * @date:   2017年7月10日 下午1:19:57
     */
    @RequestMapping("/doDeleteLocator")
    public String doDeleteLocator() {
        SlnmLocatorVo form = this.getFormValues(SlnmLocatorVo.class);
        String msg = "删除元素";
        try{
            //
            slnmCaseService.doDeleteLocator(form.getId());
            
            this.setSuccess(msg + "成功");
        } catch(BasicException be){
            logger.error(be.getMessage());
            this.setError(msg + "失败:" + be.getMessage());
        } catch(Exception e){
            logger.error(e.getMessage());
            this.setError(msg + "失败:" + this.SYSTEM_ERROR);
        }
        
        String casecode = this.getParameter("casecode");
        return CHAPTER_LIST_URL + "?casecode=" + casecode;
    }
    
    /**
     * 移动元素顺序
     * @return
     * @author: YM10095
     * @date:	2017年7月29日 下午10:42:04
     */
    @RequestMapping("/changeLocatorOrder")
    public String changeLocatorOrder() {
        String id = this.getParameter("id");
        String changedid = this.getParameter("changedid");
        String msg = "移动元素顺序";
        try{
            //
            slnmCaseService.changeLocatorOrder(new Integer(id),new Integer(changedid));
        } catch(BasicException be){
            logger.error(be.getMessage());
            this.setError(msg + "失败:" + be.getMessage());
        } catch(Exception e){
            logger.error(e.getMessage());
            this.setError(msg + "失败:" + this.SYSTEM_ERROR);
        }
        
        String casecode = this.getParameter("casecode");
        return CHAPTER_LIST_URL + "?casecode=" + casecode;
    }
    
}
