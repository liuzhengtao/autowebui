package com.yuminsoft.com.autoweb.selenium.controller;

import java.io.File;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.yuminsoft.com.autoweb.base.controller.BasicController;
import com.yuminsoft.com.autoweb.base.exception.BasicException;
import com.yuminsoft.com.autoweb.common.util.HttpUtil;
import com.yuminsoft.com.autoweb.common.util.StringUtil;
import com.yuminsoft.com.autoweb.selenium.form.SlnmCaseVo;
import com.yuminsoft.com.autoweb.selenium.form.SlnmChapterVo;
import com.yuminsoft.com.autoweb.selenium.form.SlnmPageVo;
import com.yuminsoft.com.autoweb.selenium.model.SlnmCase;
import com.yuminsoft.com.autoweb.selenium.model.SlnmChapter;
import com.yuminsoft.com.autoweb.selenium.model.SlnmLocator;
import com.yuminsoft.com.autoweb.selenium.model.SlnmPage;
import com.yuminsoft.com.autoweb.selenium.service.SlnmCaseService;
import com.yuminsoft.com.autoweb.selenium.util.ExcelUtil;
import com.yuminsoft.com.autoweb.selenium.util.SeleniumBaseCase;

/**
 * 
 * FileName:    SlnmCaseAjaxController.java  
 * @author: YM10095
 * @version:    1.0  
 * @date:   	2017年7月29日 下午10:36:31
 */
@RestController
@RequestMapping("/ajax/slnmcase")
public class SlnmCaseAjaxController extends BasicController {
    private final String driverpath = "webdriver";
    private final String screenpath = "erroscreen";
    
    @Autowired
    protected SlnmCaseService slnmCaseService;
    
    /**
     * 打开浏览器Ajax
     * @return
     * @author: YM10095
     * @date:	2017年7月18日 下午6:57:57
     */
    @RequestMapping(value="/openBrowser", method=RequestMethod.POST)
    public Object openBrowser() {
        Map<String,Object> retmap = new LinkedHashMap<String,Object>();
        retmap.put("code", 0);
        
        SlnmCaseVo form = this.getFormValues(SlnmCaseVo.class);
        if(StringUtil.isNull(form.getCasecode())){
            retmap.put("msg", "用例代码为空");
            return retmap;
        }
        SlnmCase slnmCase = slnmCaseService.getSlnmCase(form.getCasecode());
        if(slnmCase == null){
            retmap.put("msg", "用例不存在");
            return retmap;
        }
        //获取项目动态绝对路径
        String realProjectPath = this.getSession().getServletContext().getRealPath("/");
        //驱动文件路径
        String driverpath = realProjectPath + File.separator + this.driverpath;
        
        String msg = "打开浏览器";
        try{
            //驱动类型 1-IE 2-Firefox 3-Chrome
            Integer drivertype = slnmCase.getDrivertype();
            //
            SeleniumBaseCase seleniumBaseCase = SeleniumBaseCase.getInstance();
            seleniumBaseCase.setDriverpath(driverpath);
            seleniumBaseCase.setup(drivertype);
            
            retmap.put("code", 1);
            retmap.put("msg", msg + "成功");            
        } catch(BasicException be){
            logger.error(be.getMessage());
            retmap.put("msg",msg + "失败:" + be.getMessage());
        } catch(Exception e){
            logger.error(e.getMessage());
            retmap.put("msg",msg + "失败:" + this.SYSTEM_ERROR);
        }
        
        return retmap;
    }
    
    /**
     * 运行用例Ajax
     * @return
     * @author: YM10095
     * @date:	2017年7月20日 下午3:32:51
     */
    @RequestMapping(value="/doRunCase", method=RequestMethod.POST)
    public Object doRunCase() {
        Map<String,Object> retmap = new LinkedHashMap<String,Object>();
        retmap.put("code", 0);
        
        SlnmCaseVo form = this.getFormValues(SlnmCaseVo.class);
        SlnmCase bean = null;
        if(StringUtil.isNotNull(form.getCasecode())){
            bean = slnmCaseService.getSlnmCase(form.getCasecode());
        }else{
            bean = slnmCaseService.getSlnmCase(form.getId());
        }
        if(bean == null){
            retmap.put("msg", "用例不存在");
            return retmap;
        }
        
        List<SlnmChapter> slnmChapterList = slnmCaseService.getSlnmChapterList(bean.getCasecode());
        if(slnmChapterList == null || slnmChapterList.isEmpty()){
            retmap.put("msg", "该用例章节列表为空");
            return retmap;
        }
        
        //获取项目绝对路径
        //String realProjectPath = this.getSession().getServletContext().getRealPath("/");
        //获取项目相对路径
        String relativeProjectPath = System.getProperty("user.dir");
        String relativeFilePath = relativeProjectPath.substring(0,relativeProjectPath.lastIndexOf(File.separator));
        //初始化基础用例
        SeleniumBaseCase seleniumBaseCase = this.initSeleniumBaseCase(bean.getCasename());
        
        String msg = "运行用例[" + bean.getCasename() + "]";
        try{
            //驱动类型 1-IE 2-Firefox 3-Chrome
            Integer drivertype = bean.getDrivertype();
            seleniumBaseCase.setup(drivertype);
            
            int i = 0;
            int datalength = 1;
            while(i <= datalength-1){
                logger.info(msg + ":第" + (i+1) + "遍");

                for(SlnmChapter slnmChapter : slnmChapterList){
                    logger.info("--------------------*******------------------------------");
                    logger.info(new Gson().toJson(slnmChapter));
                    logger.info("--------------------*******------------------------------");
                    //是否运行 0-否 1-是
                    if(slnmChapter.getIsrun() == 0){
                        continue;
                    }
                    List<SlnmPage> slnmPageList = slnmCaseService.getSlnmPageList(slnmChapter.getChaptercode());
                    if(slnmPageList == null || slnmPageList.isEmpty()){
                        retmap.put("msg", "该用例页面列表为空");
                        return retmap;
                    }
                    //
                    for(SlnmPage slnmPage : slnmPageList){
                        //是否运行 0-否 1-是
                        if(slnmPage.getIsrun() == 0){
                            continue;
                        }
                        
                        //数据文件路径
                        String datafilepath = slnmPage.getDatafilepath();
                        //文件真实路径
                        String realFilePath = relativeFilePath + File.separator + datafilepath;
                        
                        Object[][] dataarray = null;
                        //数据文件类型 1-Excel文件 2-Xml文件
                        if(slnmPage.getDatafiletype() == 1){
                            if(StringUtil.isNotNull(datafilepath)){
                                //解析Excel文件
                                dataarray = ExcelUtil.parseExcelByJxl(realFilePath);
                            }
                        }else{
                            throw new BasicException("暂时只支持Excel文件");
                        }
                        
                        //定位器信息
                        List<SlnmLocator> locatorlist = slnmCaseService.getSlnmLocatorList(slnmPage.getPagecode());
                        
                        //有数据
                        if(dataarray != null){
                            logger.info("有文件数据");
                            datalength = dataarray.length;
                            logger.info("页面[" + slnmPage.getPagename() + "]数据长度[" + datalength + "]");
                            //只取第i行
                            Object[] cols = dataarray[i];
                            //执行用例

                            seleniumBaseCase.doRun(slnmPage.getPageurl(),slnmChapter.getChaptername(),slnmPage.getPagename(), locatorlist,cols);
                        }else{
                            //执行用例
                            seleniumBaseCase.doRun(slnmPage.getPageurl(),slnmChapter.getChaptername(),slnmPage.getPagename(), locatorlist,null);
                        }
                        //等待
                        seleniumBaseCase.sleep(1);
                    }
                }
                i = i +1;
            }
            
            
            
            retmap.put("code", 1);
            retmap.put("msg", msg + "成功");
        } catch(BasicException be){
            logger.error(be.getMessage());
            retmap.put("msg",msg + "失败:" + be.getMessage());
        } catch(Exception e){
            e.printStackTrace();
            logger.error(e.getMessage());
            retmap.put("msg",msg + "失败:" + this.SYSTEM_ERROR);
        }
        
        //是否退出浏览器 0 or null-否 1-是
        if(bean.getIsquitbrowser() == 1){
            //等待
            seleniumBaseCase.sleep(3);
            seleniumBaseCase.quit();
        }
        
        return retmap;
    }
    
    /**
     * 设置是否退出浏览器Ajax
     * @return
     * @author: YM10095
     * @date:   2017年7月20日 下午3:20:32
     */
    @RequestMapping(value="/setIsquitbrowser", method=RequestMethod.POST)
    public Object setIsquitbrowser() {
        Map<String,Object> retmap = new LinkedHashMap<String,Object>();
        retmap.put("code", 0);
        
        SlnmCaseVo form = this.getFormValues(SlnmCaseVo.class);
        
        String msg = "设置是否退出浏览器";
        try{
            slnmCaseService.setIsquitbrowser(form.getId(),form.getIsquitbrowser());
            
            retmap.put("code", 1);
            retmap.put("msg", msg + "成功");            
        } catch(BasicException be){
            logger.error(be.getMessage());
            retmap.put("msg",msg + "失败:" + be.getMessage());
        } catch(Exception e){
            e.printStackTrace();
            logger.error(e.getMessage());
            retmap.put("msg",msg + "失败:" + this.SYSTEM_ERROR);
        }
        
        return retmap;
    }
    
    /**
     * 
     * @return
     * @author: YM10095
     * @date:	2017年8月10日 下午3:55:55
     */
    @RequestMapping(value="/doRunChapter", method=RequestMethod.POST)
    public Object doRunChapter() {
        Map<String,Object> retmap = new LinkedHashMap<String,Object>();
        retmap.put("code", 0);
        
        SlnmChapterVo form = this.getFormValues(SlnmChapterVo.class);
        SlnmChapter bean = slnmCaseService.getSlnmChapter(form.getId());
        if(bean == null){
            retmap.put("msg", "章节不存在");
            return retmap;
        }
        
        SlnmCase slnmCase = slnmCaseService.getSlnmCase(bean.getCasecode());
        if(slnmCase == null){
            retmap.put("msg", "用例不存在");
            return retmap;
        }
        
        List<SlnmPage> slnmPageList = slnmCaseService.getSlnmPageList(bean.getChaptercode());
        if(slnmPageList == null || slnmPageList.isEmpty()){
            retmap.put("msg", "该章节页面列表为空");
            return retmap;
        }
        
        //获取项目相对路径
        String relativeProjectPath = System.getProperty("user.dir");
        String relativeFilePath = relativeProjectPath.substring(0,relativeProjectPath.lastIndexOf(File.separator));
        
        //初始化基础用例
        SeleniumBaseCase seleniumBaseCase = this.initSeleniumBaseCase(slnmCase.getCasename());
        
        String msg = "运行用例[" + slnmCase.getCasename() + "][" + bean.getChaptername() + "]";
        try{
            //驱动类型 1-IE 2-Firefox 3-Chrome
            Integer drivertype = slnmCase.getDrivertype();
            seleniumBaseCase.setup(drivertype);
            
            int i = 0;
            int datalength = 1;
            while(i <= datalength-1){
                logger.info(msg + ":第" + (i+1) + "遍");
                
                for(SlnmPage slnmPage : slnmPageList){
                    //是否运行 0-否 1-是
                    if(slnmPage.getIsrun() == 0){
                        continue;
                    }
                    
                    //数据文件路径
                    String datafilepath = slnmPage.getDatafilepath();
                    //文件真实路径
                    String realFilePath = relativeFilePath + File.separator + datafilepath;
                    
                    Object[][] dataarray = null;
                    //数据文件类型 1-Excel文件 2-Xml文件
                    if(slnmPage.getDatafiletype() == 1){
                        if(StringUtil.isNotNull(datafilepath)){
                            //解析Excel文件
                            dataarray = ExcelUtil.parseExcelByJxl(realFilePath);
                        }
                    }else{
                        throw new BasicException("暂时只支持Excel文件");
                    }
                    
                    //定位器信息
                    List<SlnmLocator> locatorlist = slnmCaseService.getSlnmLocatorList(slnmPage.getPagecode());
                    
                    //有数据
                    if(dataarray != null){
                        datalength = dataarray.length;
                        logger.info("页面[" + slnmPage.getPagename() + "]数据长度[" + datalength + "]");
                        //只取第i行
                        Object[] cols = dataarray[i];
                        //执行用例
                        seleniumBaseCase.doRun(slnmPage.getPageurl(),bean.getChaptername(),slnmPage.getPagename(), locatorlist,cols);
                    }else{
                        //执行用例
                        seleniumBaseCase.doRun(slnmPage.getPageurl(),bean.getChaptername(),slnmPage.getPagename(), locatorlist,null);
                    }
                    //等待
                    seleniumBaseCase.sleep(1);
                }
                i = i +1;
            }
            
            
            
            retmap.put("code", 1);
            retmap.put("msg", msg + "成功");
        } catch(BasicException be){
            logger.error(be.getMessage());
            retmap.put("msg",msg + "失败:" + be.getMessage());
        } catch(Exception e){
            e.printStackTrace();
            logger.error(e.getMessage());
            retmap.put("msg",msg + "失败:" + this.SYSTEM_ERROR);
        }
        
        //是否退出浏览器 0 or null-否 1-是
        if(slnmCase.getIsquitbrowser() == 1){
            //等待
            seleniumBaseCase.sleep(3);
            seleniumBaseCase.quit();
        }
        
        return retmap;
    }
    
    @RequestMapping(value="/setIsRunChapter", method=RequestMethod.POST)
    public Object setIsRunChapter() {
        Map<String,Object> retmap = new LinkedHashMap<String,Object>();
        retmap.put("code", 0);
        
        SlnmChapterVo form = this.getFormValues(SlnmChapterVo.class);
        
        String msg = "设置章节是否运行";
        try{
            slnmCaseService.setIsRunChapter(form.getId(),form.getIsrun());
            
            retmap.put("code", 1);
            retmap.put("msg", msg + "成功");            
        } catch(BasicException be){
            logger.error(be.getMessage());
            retmap.put("msg",msg + "失败:" + be.getMessage());
        } catch(Exception e){
            e.printStackTrace();
            logger.error(e.getMessage());
            retmap.put("msg",msg + "失败:" + this.SYSTEM_ERROR);
        }
        
        return retmap;
    }
    
    /**
     * 运行页面Ajax
     * @return
     * @author: YM10095
     * @date:	2017年7月18日 下午6:58:15
     */
    @RequestMapping(value="/doRunPage", method=RequestMethod.POST)
    public Object doRunPage() {
        Map<String,Object> retmap = new LinkedHashMap<String,Object>();
        retmap.put("code", 0);
        
        SlnmPageVo form = this.getFormValues(SlnmPageVo.class);
        SlnmPage bean = slnmCaseService.getSlnmPage(form.getId());
        if(bean == null){
            retmap.put("msg", "页面不存在");
            return retmap;
        }
        SlnmChapter slnmChapter = slnmCaseService.getSlnmChapter(bean.getChaptercode());
        if(slnmChapter == null){
            retmap.put("msg", "章节不存在");
            return retmap;
        }
        //用例信息
        SlnmCase slnmCase = slnmCaseService.getSlnmCase(slnmChapter.getCasecode());
        if(slnmCase == null){
            retmap.put("msg", "页面不存在");
            return retmap;
        }
        
        //获取项目相对路径
        String relativeProjectPath = System.getProperty("user.dir");
        String relativeFilePath = relativeProjectPath.substring(0,relativeProjectPath.lastIndexOf(File.separator));
        
        //数据文件路径
        String datafilepath = bean.getDatafilepath();
        //文件真实路径
        String realFilePath = relativeFilePath + File.separator + datafilepath;
        
        //初始化基础用例
        SeleniumBaseCase seleniumBaseCase = this.initSeleniumBaseCase(slnmCase.getCasename());
        
        String msg = "运行页面[" + bean.getPagename() + "]";
        try{
            Object[][] dataarray = null;
            //数据文件类型 1-Excel文件 2-Xml文件
            if(bean.getDatafiletype() == 1){
                if(StringUtil.isNotNull(datafilepath)){
                    //解析Excel文件
                    dataarray = ExcelUtil.parseExcelByJxl(realFilePath);
                }
            }else{
                throw new BasicException("暂时只支持Excel文件");
            }
            
            //驱动类型 1-IE 2-Firefox 3-Chrome
            Integer drivertype = slnmCase.getDrivertype();
            seleniumBaseCase.setup(drivertype);
            
            //定位器信息
            List<SlnmLocator> locatorlist = slnmCaseService.getSlnmLocatorList(bean.getPagecode());
            
            //无数据
            if(dataarray != null){
                for(Object[] cols : dataarray){
                    //执行用例
                    seleniumBaseCase.doRun(bean.getPageurl(),slnmChapter.getChaptername(),bean.getPagename(), locatorlist,cols);
                    //等待
                    seleniumBaseCase.sleep(1);
                }
            }else{
                //执行用例
                seleniumBaseCase.doRun(bean.getPageurl(),slnmChapter.getChaptername(),bean.getPagename(), locatorlist,null);
            }
            
            retmap.put("code", 1);
            retmap.put("msg", msg + "成功");            
        } catch(BasicException be){
            logger.error(be.getMessage());
            retmap.put("msg",msg + "失败:" + be.getMessage());
        } catch(Exception e){
            e.printStackTrace();
            logger.error(e.getMessage());
            retmap.put("msg",msg + "失败:" + this.SYSTEM_ERROR);
        }
        
        //是否退出浏览器 0 or null-否 1-是
        if(slnmCase.getIsquitbrowser() == 1){
            //等待
            seleniumBaseCase.sleep(2);
            seleniumBaseCase.quit();
        }
        
        return retmap;
    }
    
    /**
     * 设置页面是否运行Ajax
     * @return
     * @author: YM10095
     * @date:	2017年7月20日 下午3:20:32
     */
    @RequestMapping(value="/setIsRunPage", method=RequestMethod.POST)
    public Object setIsRunPage() {
        Map<String,Object> retmap = new LinkedHashMap<String,Object>();
        retmap.put("code", 0);
        
        SlnmPageVo form = this.getFormValues(SlnmPageVo.class);
        
        String msg = "设置页面是否运行";
        try{
            slnmCaseService.setIsRunPage(form.getId(),form.getIsrun());
            
            retmap.put("code", 1);
            retmap.put("msg", msg + "成功");            
        } catch(BasicException be){
            logger.error(be.getMessage());
            retmap.put("msg",msg + "失败:" + be.getMessage());
        } catch(Exception e){
            e.printStackTrace();
            logger.error(e.getMessage());
            retmap.put("msg",msg + "失败:" + this.SYSTEM_ERROR);
        }
        
        return retmap;
    }
    
    /**
     * 初始化基础用例
     * @param casename
     * @return
     * @author: YM10095
     * @date:	2017年8月21日 上午11:35:44
     */
    private SeleniumBaseCase initSeleniumBaseCase(String casename) {
        SeleniumBaseCase seleniumBaseCase = SeleniumBaseCase.getInstance();
        //
        seleniumBaseCase.setCasename(casename);
        
        //获取项目动态绝对路径
        String realProjectPath = this.getSession().getServletContext().getRealPath("/");
        //驱动文件路径
        String driverpath = realProjectPath + File.separator + this.driverpath;


        seleniumBaseCase.setDriverpath(driverpath);
        
        //调用方式 1-本地 2-远程
        Integer invoketype = 1;
        String serverIp = HttpUtil.getServerIp();
        String clientIp = HttpUtil.getClientIp(this.getRequest());
        if(!clientIp.equals(serverIp)){
            invoketype = 2;
        }
        seleniumBaseCase.setInvoketype(invoketype);
        seleniumBaseCase.setClientIp(clientIp);
        
        return seleniumBaseCase;
    }
    
}
