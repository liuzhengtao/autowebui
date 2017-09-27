package com.yuminsoft.com.autoweb.selenium.service.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.google.gson.Gson;
import com.yuminsoft.com.autoweb.base.exception.BasicException;
import com.yuminsoft.com.autoweb.common.dao.SysMaxKeyValueDao;
import com.yuminsoft.com.autoweb.common.page.Page;
import com.yuminsoft.com.autoweb.selenium.bean.SlnmChapterBean;
import com.yuminsoft.com.autoweb.selenium.bean.SlnmLocatorBean;
import com.yuminsoft.com.autoweb.selenium.dao.SeleniumDao;
import com.yuminsoft.com.autoweb.selenium.dao.SlnmPageTempDao;
import com.yuminsoft.com.autoweb.selenium.form.SlnmCaseVo;
import com.yuminsoft.com.autoweb.selenium.form.SlnmChapterVo;
import com.yuminsoft.com.autoweb.selenium.form.SlnmPageVo;
import com.yuminsoft.com.autoweb.selenium.model.SlnmCase;
import com.yuminsoft.com.autoweb.selenium.model.SlnmLocatorTemp;
import com.yuminsoft.com.autoweb.selenium.model.SlnmPage;
import com.yuminsoft.com.autoweb.selenium.service.SlnmCaseService;
import com.yuminsoft.com.autoweb.selenium.service.SlnmCaseService;
import org.apache.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.yuminsoft.com.autoweb.common.util.StringUtil;
import com.yuminsoft.com.autoweb.selenium.bean.SlnmBean;
import com.yuminsoft.com.autoweb.selenium.bean.SlnmCaseBean;
import com.yuminsoft.com.autoweb.selenium.bean.SlnmPageBean;
import com.yuminsoft.com.autoweb.selenium.form.SlnmLocatorVo;
import com.yuminsoft.com.autoweb.selenium.model.SlnmChapter;
import com.yuminsoft.com.autoweb.selenium.model.SlnmLocator;
import com.yuminsoft.com.autoweb.selenium.model.SlnmPageTemp;

@Service
public class SlnmCaseServiceImpl implements SlnmCaseService {
    Logger logger  = Logger.getLogger(this.getClass());
    @Autowired
    protected SeleniumDao seleniumDao;
    @Autowired
    protected SysMaxKeyValueDao sysMaxKeyValueDao;
    @Autowired
    protected SlnmPageTempDao slnmPageTempDao;
    
    @Override
    public Page<SlnmCase> getPage(SlnmCaseVo form) {
        Page<SlnmCase> page = seleniumDao.getPage(form);
        
        return page;
    }

    @Override
    public List<SlnmCase> getSlnmCaseList() {
        List<SlnmCase> list = seleniumDao.getSlnmCaseList();
        return list;
    }
    
    @Override
    public SlnmCase getSlnmCase(Integer id) {
        SlnmCase bean = seleniumDao.getSlnmCase(id);
        return bean;
    }

    @Override
    public SlnmCase getSlnmCase(String casecode) {
        SlnmCase bean = seleniumDao.getSlnmCase(casecode);
        return bean;
    }

    @Override
    public SlnmCase getSlnmCaseByName(String casename) {
        SlnmCase bean = seleniumDao.getSlnmCaseByName(casename);
        return bean;
    }

    @Override
    public SlnmBean getSlnmBean(SlnmCaseVo form) {
        SlnmBean bean = new SlnmBean();
        Page<SlnmCase> page = this.getPage(form);
        List<SlnmCaseBean> slnmCaseBeanList = new ArrayList<SlnmCaseBean>();
        for(SlnmCase model : page.getResults()){
            if(StringUtil.isNull(bean.getSlnmname())){
                bean.setSlnmname(model.getCasename());
            }else{
                bean.setSlnmname(bean.getSlnmname() + "," + model.getCasename());
            }
            
            SlnmCaseBean slnmCaseBean = new SlnmCaseBean();
            BeanUtils.copyProperties(model, slnmCaseBean);
            
            List<SlnmChapterBean> slnmChapterBeanList = new ArrayList<SlnmChapterBean>();
            //章节信息
            List<SlnmChapter> chapterlist = this.getSlnmChapterList(model.getCasecode());
            for(SlnmChapter slnmChapter : chapterlist){
                SlnmChapterBean slnmChapterBean = new SlnmChapterBean();
                BeanUtils.copyProperties(slnmChapter, slnmChapterBean);
                
                List<SlnmPageBean> slnmPageBeanList = new ArrayList<SlnmPageBean>();
                //页面信息                
                List<SlnmPage> pagelist = this.getSlnmPageList(slnmChapter.getChaptercode());
                for(SlnmPage slnmPage : pagelist){
                    SlnmPageBean slnmPageBean = new SlnmPageBean();
                    BeanUtils.copyProperties(slnmPage, slnmPageBean);
                    
                    List<SlnmLocatorBean> slnmLocatorBeanList = new ArrayList<SlnmLocatorBean>();
                    //定位器信息
                    List<SlnmLocator> locatorlist = this.getSlnmLocatorList(slnmPage.getPagecode());
                    for(SlnmLocator slnmLocator : locatorlist){
                        SlnmLocatorBean slnmLocatorBean = new SlnmLocatorBean();
                        BeanUtils.copyProperties(slnmLocator, slnmLocatorBean);
                        slnmLocatorBeanList.add(slnmLocatorBean);
                    }
                    slnmPageBean.setLocatorbeanlist(slnmLocatorBeanList);
                    
                    slnmPageBeanList.add(slnmPageBean);
                }
                slnmChapterBean.setPagebeanlist(slnmPageBeanList);
                slnmChapterBeanList.add(slnmChapterBean);
            }
            slnmCaseBean.setChapterbeanlist(slnmChapterBeanList);            
            slnmCaseBeanList.add(slnmCaseBean);
        }
        bean.setCasebeanlist(slnmCaseBeanList);
        
        return bean;
    }
    
    @Override
    @Transactional()
    public void save(SlnmCaseVo form) {
        SlnmCase model = seleniumDao.getSlnmCase(form.getCasename());
        if(model != null){
            throw new BasicException("该用例已经存在");
        }
        
        SlnmCase bean = new SlnmCase();
        BeanUtils.copyProperties(form, bean);
        
        //用例代码
        int id = sysMaxKeyValueDao.getMaxValue("slnm_case");
        String casecode = StringUtil.formatLeft(id, "0000");
        
        bean.setCasecode(casecode);
        bean.setOptcode(form.getOptcode());
        bean.setCreatetime(new Date());
        bean.setModifiedtime(new Date());
        
        this.seleniumDao.save(bean);
    }

    @Override
    @Transactional()
    public void update(SlnmCaseVo form) {
        Integer id = form.getId();
        SlnmCase model = seleniumDao.getSlnmCase(id);
        if(model == null){
            throw new BasicException("该用例不存在");
        }
        String casename = form.getCasename();
        if(!casename.equals(model.getCasename())){
            SlnmCase model0 = seleniumDao.getSlnmCaseByName(casename);
            if(model0 != null){
                throw new BasicException("该用例已经存在");
            }
        }
        
        model.setCasename(form.getCasename());
        model.setDrivertype(form.getDrivertype());
        model.setBrowserpath(form.getBrowserpath());
        model.setIsquitbrowser(form.getIsquitbrowser());
        model.setDescription(form.getDescription());
        model.setOptcode(form.getOptcode());
        model.setModifiedtime(new Date());
        
        this.seleniumDao.update(model);
    }
    

    @Override
    @Transactional()
    public void doImportCase(SlnmCaseVo form, SlnmBean slnmbean) {
        List<SlnmCaseBean> casebeanlist = slnmbean.getCasebeanlist();
        for(SlnmCaseBean casebean : casebeanlist){
            //用例
            SlnmCase bean = new SlnmCase();
            BeanUtils.copyProperties(casebean, bean);
            //用例代码
            int id = sysMaxKeyValueDao.getMaxValue("slnm_case");
            String casecode = StringUtil.formatLeft(id, "0000");
            bean.setCasecode(casecode);
            bean.setOptcode(form.getOptcode());
            bean.setCreatetime(new Date());
            bean.setModifiedtime(new Date());
            this.seleniumDao.save(bean);
            
            //章节
            List<SlnmChapterBean> chapterbeanlist = casebean.getChapterbeanlist();
            for(SlnmChapterBean slnmChapterBean : chapterbeanlist){
                SlnmChapter chapterbean = new SlnmChapter();
                BeanUtils.copyProperties(slnmChapterBean, chapterbean);
                //章节代码
                int id0 = sysMaxKeyValueDao.getMaxValue("slnm_chapter");
                String chaptercode = StringUtil.formatLeft(id0, "00000");
                chapterbean.setChaptercode(chaptercode);
                chapterbean.setCasecode(casecode);
                chapterbean.setOptcode(form.getOptcode());
                chapterbean.setCreatetime(new Date());
                chapterbean.setModifiedtime(new Date());
                this.seleniumDao.save(chapterbean);
                
                //页面
                List<SlnmPageBean> pagebeanlist = slnmChapterBean.getPagebeanlist();
                for(SlnmPageBean slnmPageBean : pagebeanlist){
                    SlnmPage pagebean = new SlnmPage();
                    BeanUtils.copyProperties(slnmPageBean, pagebean);
                    //页面代码
                    int id1 = sysMaxKeyValueDao.getMaxValue("slnm_page");
                    String pagecode = StringUtil.formatLeft(id1, "000000");
                    pagebean.setPagecode(pagecode);
                    pagebean.setChaptercode(chaptercode);
                    pagebean.setCasecode("0");
                    pagebean.setOptcode(form.getOptcode());
                    pagebean.setCreatetime(new Date());
                    pagebean.setModifiedtime(new Date());
                    pagebean.setDatafilepath(null);//
                    pagebean.setDatafilename(null);//
                    this.seleniumDao.save(pagebean);
                    //定位器
                    List<SlnmLocatorBean> locatorbeanlist = slnmPageBean.getLocatorbeanlist();
                    for(SlnmLocatorBean slnmLocatorBean : locatorbeanlist){
                        SlnmLocator locatorbean = new SlnmLocator();
                        BeanUtils.copyProperties(slnmLocatorBean, locatorbean);
                        //定位器代码
                        int id2 = sysMaxKeyValueDao.getMaxValue("slnm_locator");
                        String locatorcode = StringUtil.formatLeft(id2, "00000000");
                        locatorbean.setLocatorcode(locatorcode);
                        locatorbean.setPagecode(pagecode);
                        locatorbean.setCreatetime(new Date());
                        locatorbean.setModifiedtime(new Date());
                        this.seleniumDao.save(locatorbean);
                    }
                }
            }
        }
    }
    
    @Override
    @Transactional()
    public void delete(Integer id) {
        SlnmCase model = seleniumDao.getSlnmCase(id);
        if(model == null){
            throw new BasicException("该用例不存在");
        }
        //删除用例
        int ret = this.seleniumDao.delete(id);
        if(ret == 0){
            throw new BasicException("该用例不存在或已删除");
        }
        
        //章节信息
        List<SlnmChapter> chapterlist = this.getSlnmChapterList(model.getCasecode());
        //删除章节
        this.seleniumDao.deleteChapterByCasecode(model.getCasecode());
        
        for(SlnmChapter slnmChapter : chapterlist){
            List<SlnmPage> pagelist = this.getSlnmPageList(slnmChapter.getChaptercode());
            
            //删除页面
            this.seleniumDao.deletePageByChaptercode(slnmChapter.getChaptercode());
            
            //删除元素定位器
            for(SlnmPage slnmPage : pagelist){
                this.seleniumDao.deleteLocatorByPagecode(slnmPage.getPagecode());
            }
        }
        
    }

    @Override
    @Transactional()
    public void setIsquitbrowser(Integer id, Integer isquitbrowser) {
        SlnmCase model = seleniumDao.getSlnmCase(id);
        if(model == null){
            throw new BasicException("该用例不存在");
        }
        
        model.setIsquitbrowser(isquitbrowser);
        
        this.seleniumDao.update(model);
    }
    

    @Override
    public Page<SlnmChapter> getChapterPage(SlnmChapterVo form) {
        Page<SlnmChapter> page = seleniumDao.getChapterPage(form);
        
        for(SlnmChapter bean : page.getResults()){
            //页面信息
            List<SlnmPage> pagelist = this.getSlnmPageList(bean.getChaptercode());
            for(SlnmPage slnmPage : pagelist){
                //定位器信息
                List<SlnmLocator> locatorlist = this.getSlnmLocatorList(slnmPage.getPagecode());
                slnmPage.setLocatorlist(locatorlist);
            }
            bean.setPagelist(pagelist);
        }
        
        return page;
    }

    @Override
    public List<SlnmChapter> getSlnmChapterList() {
        List<SlnmChapter> list = seleniumDao.getSlnmChapterList();
        return list;
    }

    @Override
    public List<SlnmChapter> getSlnmChapterList(String casecode) {
        List<SlnmChapter> list = seleniumDao.getSlnmChapterList(casecode);
        return list;
    }

    @Override
    public SlnmChapter getSlnmChapter(Integer id) {
        SlnmChapter bean = seleniumDao.getSlnmChapter(id);
        return bean;
    }

    @Override
    public SlnmChapter getSlnmChapter(String chaptercode) {
        SlnmChapter bean = seleniumDao.getSlnmChapter(chaptercode);
        return bean;
    }

    @Override
    public SlnmChapter getSlnmChapter(String casecode, String chaptername) {
        SlnmChapter bean = seleniumDao.getSlnmChapter(casecode,chaptername);
        return bean;
    }

    @Override
    @Transactional()
    public void doAddChapter(SlnmChapterVo form) {
        String casecode = form.getCasecode();
        if(StringUtil.isNull(casecode)){
            throw new BasicException("用例代码为空");
        }
        SlnmChapter model = seleniumDao.getSlnmChapter(casecode,form.getChaptername());
        if(model != null){
            throw new BasicException("该章节已经存在");
        }
        
        SlnmChapter bean = new SlnmChapter();
        BeanUtils.copyProperties(form, bean);
        
        //章节代码
        int id = sysMaxKeyValueDao.getMaxValue("slnm_chapter");
        String chaptercode = StringUtil.formatLeft(id, "00000");
        
        bean.setChaptercode(chaptercode);
        bean.setIsrun(1);
        bean.setOptcode(form.getOptcode());
        bean.setCreatetime(new Date());
        bean.setModifiedtime(new Date());
        
        this.seleniumDao.save(bean);
    }

    @Override
    @Transactional()
    public void doUpdateChapter(SlnmChapterVo form) {
        //logger.info("updateChapter:"+new Gson().toJson(form));
        Integer id = form.getId();
        SlnmChapter model = seleniumDao.getSlnmChapter(id);
        if(model == null){
            throw new BasicException("该章节不存在");
        }
        String chaptername = form.getChaptername();
        if(!chaptername.equals(model.getChaptername())){
            SlnmChapter model0 = seleniumDao.getSlnmChapter(model.getCasecode(), chaptername);
            if(model0 != null){
                throw new BasicException("章节已经存在");
            }
        }
        model.setChaptername(form.getChaptername());
        model.setOrderno(form.getOrderno());
        model.setExpectedresults(form.getExpectedresults());
        model.setDescription(form.getDescription());
        model.setOptcode(form.getOptcode());
        model.setModifiedtime(new Date());
        this.seleniumDao.update(model);
    }

    @Override
    @Transactional()
    public void doCopyChapter(SlnmChapterVo form) {
        Integer id = form.getId();
        SlnmChapter model = seleniumDao.getSlnmChapter(id);
        if(model == null){
            throw new BasicException("该章节不存在");
        }
        String casecode = form.getCasecode();
        String chaptername = form.getChaptername();        
        
        SlnmChapter bean = new SlnmChapter();
        //章节代码
        int id0 = sysMaxKeyValueDao.getMaxValue("slnm_chapter");
        String chaptercode = StringUtil.formatLeft(id0, "00000");
        bean.setChaptercode(chaptercode);
        
        bean.setCasecode(casecode);
        bean.setChaptername(chaptername);
        bean.setOrderno(form.getOrderno());
        bean.setDescription(form.getDescription());
        bean.setOptcode(form.getOptcode());
        bean.setCreatetime(new Date());
        bean.setModifiedtime(new Date());
        this.seleniumDao.save(bean);
        
        //复制页面
        List<SlnmPage> pagelist = seleniumDao.getSlnmPageList(model.getChaptercode());
        for(SlnmPage slnmPage0 : pagelist){
            SlnmPage slnmPage = new SlnmPage();
            BeanUtils.copyProperties(slnmPage0, slnmPage);
            slnmPage.setId(null);
            slnmPage.setChaptercode(chaptercode);
            //页面代码
            int id1 = sysMaxKeyValueDao.getMaxValue("slnm_page");
            String pagecode = StringUtil.formatLeft(id1, "000000");
            slnmPage.setPagecode(pagecode);
            
            slnmPage.setDatafilepath(null);
            slnmPage.setDatafilename(null);
            slnmPage.setDescription(form.getDescription());
            slnmPage.setOptcode(form.getOptcode());
            slnmPage.setCreatetime(new Date());
            slnmPage.setModifiedtime(new Date());
            this.seleniumDao.save(slnmPage);
            
            //复制定位器
            List<SlnmLocator> locatorlist = seleniumDao.getSlnmLocatorList(slnmPage0.getPagecode());
            for(SlnmLocator slnmLocator0 : locatorlist){
                SlnmLocator slnmLocator = new SlnmLocator();
                BeanUtils.copyProperties(slnmLocator0, slnmLocator);
                slnmLocator.setId(null);
                slnmLocator.setPagecode(pagecode);
                //定位器代码
                int id2 = sysMaxKeyValueDao.getMaxValue("slnm_locator");
                String locatorcode = StringUtil.formatLeft(id2, "00000000");
                slnmLocator.setLocatorcode(locatorcode);
                this.seleniumDao.save(slnmLocator);
            }
        }
        
    }

    @Override
    @Transactional()
    public void doDeleteChapter(Integer id) {
        SlnmChapter model = seleniumDao.getSlnmChapter(id);
        if(model == null){
            throw new BasicException("该章节不存在");
        }
        int ret = this.seleniumDao.deleteChapter(id);
        if(ret == 0){
            throw new BasicException("该章节不存在或已删除");
        }
        
        //删除页面
        List<SlnmPage> pagelist = this.getSlnmPageList(model.getChaptercode());
        this.seleniumDao.deletePageByChaptercode(model.getChaptercode());
        for(SlnmPage slnmPage : pagelist){
            //删除元素定位器
            this.seleniumDao.deleteLocatorByPagecode(slnmPage.getPagecode());
        }
    }

    @Override
    @Transactional()
    public void changeChapterOrder(Integer id, Integer changedid) {
        SlnmChapter model = seleniumDao.getSlnmChapter(id);
        if(model == null){
            throw new BasicException("该章节不存在");
        }
        SlnmChapter model1 = seleniumDao.getSlnmChapter(changedid);
        if(model1 == null){
            throw new BasicException("被交换章节不存在");
        }
        
        //互换排序号
        Integer orderno = model.getOrderno();
        model.setOrderno(model1.getOrderno());
        model1.setOrderno(orderno);
        
        this.seleniumDao.update(model);        
        this.seleniumDao.update(model1);
    }

    @Override
    @Transactional()
    public void setIsRunChapter(Integer id, Integer isrun) {
        SlnmChapter model = seleniumDao.getSlnmChapter(id);
        if(model == null){
            throw new BasicException("该章节不存在");
        }
        
        model.setIsrun(isrun);
        
        this.seleniumDao.update(model);
    }
    
    @Override
    public List<SlnmPage> getSlnmPageList() {
        List<SlnmPage> list = seleniumDao.getSlnmPageList();
        return list;
    }

    @Override
    public List<SlnmPage> getSlnmPageList(String chaptercode) {
        List<SlnmPage> list = seleniumDao.getSlnmPageList(chaptercode);
        return list;
    }
    
    @Override
    public SlnmPage getSlnmPage(Integer id) {
        SlnmPage bean = seleniumDao.getSlnmPage(id);
        return bean;
    }

    @Override
    public SlnmPage getSlnmPage(String pagecode) {
        SlnmPage bean = seleniumDao.getSlnmPage(pagecode);
        return bean;
    }
    
    @Override
    public SlnmPage getSlnmPage(String chaptercode,String pagename) {
        SlnmPage bean = seleniumDao.getSlnmPage(chaptercode,pagename);
        return bean;
    }

    @Override
    @Transactional()
    public void doAddPage(SlnmPageVo form) {
        String chaptercode = form.getChaptercode();
        if(StringUtil.isNull(chaptercode)){
            throw new BasicException("章节代码为空");
        }
        SlnmPage model = seleniumDao.getSlnmPage(chaptercode,form.getPagename());
        if(model != null){
            throw new BasicException("该页面已经存在");
        }
        
        SlnmPage bean = new SlnmPage();
        BeanUtils.copyProperties(form, bean);
        
        //页面代码
        int id = sysMaxKeyValueDao.getMaxValue("slnm_page");
        String pagecode = StringUtil.formatLeft(id, "000000");
        
        bean.setPagecode(pagecode);
        bean.setCasecode("0");
        bean.setIsrun(1);
        bean.setOptcode(form.getOptcode());
        bean.setCreatetime(new Date());
        bean.setModifiedtime(new Date());
        
        this.seleniumDao.save(bean);
    }
    
    @Override
    @Transactional()
    public void doAddPageFromTemp(SlnmPageVo form) {
        String chaptercode = form.getChaptercode();
        if(StringUtil.isNull(chaptercode)){
            throw new BasicException("章节代码为空");
        }
        String jsonstring = form.getJsonstring();
        if(StringUtil.isNull(jsonstring)){
            throw new BasicException("未选择页面模板");
        }
        
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            List<Map<String,String>> list = objectMapper.readValue(jsonstring, List.class);
            for(Map<String,String> map :list){
                String tempid = map.get("tempid");
                String orderno = map.get("orderno");
                SlnmPageTemp slnmPageTemp = slnmPageTempDao.getSlnmPageTemp(new Integer(tempid));
                //
                SlnmPage bean = new SlnmPage();
                BeanUtils.copyProperties(slnmPageTemp, bean);
                bean.setId(null);
                bean.setChaptercode(chaptercode);
                //页面代码
                int id = sysMaxKeyValueDao.getMaxValue("slnm_page");
                String pagecode = StringUtil.formatLeft(id, "000000");
                bean.setPagecode(pagecode);
                bean.setCasecode("0");
                bean.setDatafilepath(null);
                bean.setDatafilename(null);
                bean.setOrderno(new Integer(orderno));
                bean.setOptcode(form.getOptcode());
                bean.setCreatetime(new Date());
                bean.setModifiedtime(new Date());
                this.seleniumDao.save(bean);
                //复制定位器
                List<SlnmLocatorTemp> locatortemplist = slnmPageTempDao.getSlnmLocatorTempList(slnmPageTemp.getPagecode());
                for(SlnmLocatorTemp slnmLocator0 : locatortemplist){
                    SlnmLocator slnmLocator = new SlnmLocator();
                    BeanUtils.copyProperties(slnmLocator0, slnmLocator);
                    slnmLocator.setId(null);
                    slnmLocator.setPagecode(pagecode);
                    //定位器代码
                    int id1 = sysMaxKeyValueDao.getMaxValue("slnm_locator");
                    String locatorcode = StringUtil.formatLeft(id1, "00000000");
                    slnmLocator.setLocatorcode(locatorcode);
                    this.seleniumDao.save(slnmLocator);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            throw new BasicException("转换json数据异常");
        }
        
    }
    
    @Override
    @Transactional()
    public void doUpdatePage(SlnmPageVo form) {
        Integer id = form.getId();
        SlnmPage model = seleniumDao.getSlnmPage(id);
        if(model == null){
            throw new BasicException("该页面不存在");
        }
        String pagename = form.getPagename();
        if(!pagename.equals(model.getPagename())){
            SlnmPage model0 = seleniumDao.getSlnmPage(model.getChaptercode(), pagename);
            if(model0 != null){
                throw new BasicException("页面已经存在");
            }
        }
        
        model.setPagename(form.getPagename());
        model.setPageurl(form.getPageurl());
        model.setDatafiletype(form.getDatafiletype());
        model.setDatafilepath(form.getDatafilepath());
        model.setDatafilename(form.getDatafilename());
        model.setOrderno(form.getOrderno());
        model.setDescription(form.getDescription());
        model.setOptcode(form.getOptcode());
        model.setModifiedtime(new Date());
        
        this.seleniumDao.update(model);
    }

    @Override
    @Transactional()
    public void doAddPageToTemp(SlnmPageVo form) {
        Integer id = form.getId();
        SlnmPage model = seleniumDao.getSlnmPage(id);
        if(model == null){
            throw new BasicException("该页面不存在");
        }
        
        SlnmPageTemp bean = new SlnmPageTemp();
        BeanUtils.copyProperties(form, bean);
        bean.setId(null);
        
        //页面代码
        int id0 = sysMaxKeyValueDao.getMaxValue("slnm_page_temp");
        String pagecode = StringUtil.formatLeft(id0, "000000");
        
        bean.setPagecode(pagecode);
        bean.setCasecode("0");
        bean.setDatafilepath(null);
        bean.setDatafilename(null);
        bean.setOptcode(form.getOptcode());
        bean.setCreatetime(new Date());
        bean.setModifiedtime(new Date());
        
        this.seleniumDao.save(bean);
        
        //复制定位器
        List<SlnmLocator> locatorlist = seleniumDao.getSlnmLocatorList(model.getPagecode());
        for(SlnmLocator slnmLocator0 : locatorlist){
            SlnmLocatorTemp slnmLocator = new SlnmLocatorTemp();
            BeanUtils.copyProperties(slnmLocator0, slnmLocator);
            slnmLocator.setId(null);
            slnmLocator.setPagecode(pagecode);
            //定位器代码
            int id1 = sysMaxKeyValueDao.getMaxValue("slnm_locator_temp");
            String locatorcode = StringUtil.formatLeft(id1, "00000000");
            slnmLocator.setLocatorcode(locatorcode);
            this.seleniumDao.save(slnmLocator);
        }
    }
    
    @Override
    @Transactional()
    public void doCopyPage(SlnmPageVo form) {
        Integer id = form.getId();
        SlnmPage model = seleniumDao.getSlnmPage(id);
        if(model == null){
            throw new BasicException("该页面不存在");
        }
        String chaptercode = form.getChaptercode();
        String pagename = form.getPagename();        
        
        SlnmPage bean = new SlnmPage();
        //页面代码
        int id0 = sysMaxKeyValueDao.getMaxValue("slnm_page");
        String pagecode = StringUtil.formatLeft(id0, "000000");
        bean.setPagecode(pagecode);
        
        bean.setChaptercode(chaptercode);
        bean.setCasecode("0");
        bean.setPagename(pagename);
        bean.setPageurl(form.getPageurl());
        bean.setDatafiletype(form.getDatafiletype());
        bean.setDatafilepath(form.getDatafilepath());
        bean.setDatafilename(form.getDatafilename());
        bean.setOrderno(form.getOrderno());
        bean.setDescription(form.getDescription());
        bean.setOptcode(form.getOptcode());
        bean.setCreatetime(new Date());
        bean.setModifiedtime(new Date());
        this.seleniumDao.save(bean);
        
        //复制定位器
        List<SlnmLocator> locatorlist = seleniumDao.getSlnmLocatorList(model.getPagecode());
        for(SlnmLocator slnmLocator0 : locatorlist){
            SlnmLocator slnmLocator = new SlnmLocator();
            BeanUtils.copyProperties(slnmLocator0, slnmLocator);
            slnmLocator.setId(null);
            slnmLocator.setPagecode(pagecode);
            //定位器代码
            int id1 = sysMaxKeyValueDao.getMaxValue("slnm_locator");
            String locatorcode = StringUtil.formatLeft(id1, "00000000");
            slnmLocator.setLocatorcode(locatorcode);
            this.seleniumDao.save(slnmLocator);
        }
    }
    
    @Override
    @Transactional()
    public void doDeletePage(Integer id) {
        SlnmPage model = seleniumDao.getSlnmPage(id);
        if(model == null){
            throw new BasicException("该页面不存在");
        }
        int ret = this.seleniumDao.deletePage(id);
        if(ret == 0){
            throw new BasicException("该页面不存在或已删除");
        }

        //删除元素定位器
        this.seleniumDao.deleteLocatorByPagecode(model.getPagecode());
    }
    
    @Override
    @Transactional()
    public void changePageOrder(Integer id, Integer changedid) {
        SlnmPage model = seleniumDao.getSlnmPage(id);
        if(model == null){
            throw new BasicException("该页面不存在");
        }
        SlnmPage model1 = seleniumDao.getSlnmPage(changedid);
        if(model1 == null){
            throw new BasicException("被交换页面不存在");
        }
        
        //互换排序号
        Integer orderno = model.getOrderno();
        model.setOrderno(model1.getOrderno());
        model1.setOrderno(orderno);
        
        this.seleniumDao.update(model);        
        this.seleniumDao.update(model1);
    }
    
    @Override
    @Transactional()
    public void setIsRunPage(Integer id, Integer isrun) {
        SlnmPage model = seleniumDao.getSlnmPage(id);
        if(model == null){
            throw new BasicException("该页面不存在");
        }
        
        model.setIsrun(isrun);
        
        this.seleniumDao.update(model);
    }
    
    @Override
    public List<SlnmLocator> getSlnmLocatorList(String pagecode) {
        List<SlnmLocator> list = seleniumDao.getSlnmLocatorList(pagecode);
        return list;
    }
    
    @Override
    public SlnmLocator getSlnmLocator(Integer id) {
        SlnmLocator bean = seleniumDao.getSlnmLocator(id);
        return bean;
    }

    @Override
    public SlnmLocator getSlnmLocator(String pagecode, String locatorname) {
        SlnmLocator bean = seleniumDao.getSlnmLocator(pagecode,locatorname);
        return bean;
    }

    @Override
    @Transactional()
    public void doAddLocator(SlnmLocatorVo form) {
        String pagecode = form.getPagecode();
        if(StringUtil.isNull(pagecode)){
            throw new BasicException("页面代码为空");
        }
        SlnmLocator model = seleniumDao.getSlnmLocator(pagecode,form.getLocatorname());
        if(model != null){
            //throw new BasicException("该元素已经存在");
        }
        
        SlnmLocator bean = new SlnmLocator();
        BeanUtils.copyProperties(form, bean);
        
        //定位器代码
        int id = sysMaxKeyValueDao.getMaxValue("slnm_locator");
        String locatorcode = StringUtil.formatLeft(id, "00000000");
        
        bean.setLocatorcode(locatorcode);        
        bean.setCreatetime(new Date());
        bean.setModifiedtime(new Date());
        
        this.seleniumDao.save(bean);
    }
    
    @Override
    @Transactional()
    public void doUpdateLocator(SlnmLocatorVo form) {
        Integer id = form.getId();
        SlnmLocator model = seleniumDao.getSlnmLocator(id);
        if(model == null){
            throw new BasicException("该元素不存在");
        }
        String locatorname = form.getLocatorname();
        if(!locatorname.equals(model.getLocatorname())){
            SlnmLocator model0 = seleniumDao.getSlnmLocator(model.getPagecode(), locatorname);
            if(model0 != null){
                //throw new BasicException("元素已经存在");
            }
        }
        
        model.setLocatorname(form.getLocatorname());
        model.setBytype(form.getBytype());
        model.setByvalue(form.getByvalue());
        model.setTimeout(form.getTimeout());
        model.setWaittime(form.getWaittime());
        model.setOpttype(form.getOpttype());
        model.setDatavalue(form.getDatavalue());
        model.setOrderno(form.getOrderno());
        model.setDescription(form.getDescription());
        model.setModifiedtime(new Date());
        
        this.seleniumDao.update(model);
    }
    
    @Override
    @Transactional()
    public void doCopyLocator(SlnmLocatorVo form) {
        Integer id = form.getId();
        SlnmLocator model = seleniumDao.getSlnmLocator(id);
        if(model == null){
            throw new BasicException("该元素不存在");
        }
        
        SlnmLocator bean = new SlnmLocator();

        //定位器代码
        int id0 = sysMaxKeyValueDao.getMaxValue("slnm_locator");
        String locatorcode = StringUtil.formatLeft(id0, "00000000");
        bean.setLocatorcode(locatorcode);
        
        bean.setPagecode(form.getPagecode());
        bean.setLocatorname(form.getLocatorname());
        bean.setBytype(form.getBytype());
        bean.setByvalue(form.getByvalue());
        bean.setTimeout(form.getTimeout());
        bean.setWaittime(form.getWaittime());
        bean.setOpttype(form.getOpttype());
        bean.setDatavalue(form.getDatavalue());
        bean.setOrderno(form.getOrderno());
        bean.setDescription(form.getDescription());
        bean.setCreatetime(new Date());
        bean.setModifiedtime(new Date());
        
        this.seleniumDao.save(bean);
    }

    @Override
    @Transactional()
    public void doMoveLocator(SlnmLocatorVo form) {
        Integer id = form.getId();
        SlnmLocator model = seleniumDao.getSlnmLocator(id);
        if(model == null){
            throw new BasicException("该元素不存在");
        }
        
        model.setPagecode(form.getPagecode());
        model.setLocatorname(form.getLocatorname());
        model.setBytype(form.getBytype());
        model.setByvalue(form.getByvalue());
        model.setTimeout(form.getTimeout());
        model.setWaittime(form.getWaittime());
        model.setOpttype(form.getOpttype());
        model.setDatavalue(form.getDatavalue());
        model.setOrderno(form.getOrderno());
        model.setDescription(form.getDescription());
        model.setModifiedtime(new Date());
        
        this.seleniumDao.update(model);
    }
    
    @Override
    @Transactional()
    public void doDeleteLocator(Integer id) {
        int ret = this.seleniumDao.deleteLocator(id);
        if(ret == 0){
            throw new BasicException("该元素不存在或已删除");
        }
    }

    @Override
    @Transactional()
    public void changeLocatorOrder(Integer id, Integer changedid) {
        SlnmLocator model = seleniumDao.getSlnmLocator(id);
        if(model == null){
            throw new BasicException("该元素不存在");
        }
        SlnmLocator model1 = seleniumDao.getSlnmLocator(changedid);
        if(model1 == null){
            throw new BasicException("被交换元素不存在");
        }
        
        //互换排序号
        Integer orderno = model.getOrderno();
        model.setOrderno(model1.getOrderno());
        model1.setOrderno(orderno);
        
        this.seleniumDao.update(model);        
        this.seleniumDao.update(model1);
    }
    
}
