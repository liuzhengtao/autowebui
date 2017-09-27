package com.yuminsoft.com.autoweb.selenium.service.impl;

import java.util.Date;
import java.util.List;

import com.yuminsoft.com.autoweb.base.exception.BasicException;
import com.yuminsoft.com.autoweb.selenium.form.SlnmLocatorTempVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yuminsoft.com.autoweb.common.dao.SysMaxKeyValueDao;
import com.yuminsoft.com.autoweb.common.page.Page;
import com.yuminsoft.com.autoweb.common.util.StringUtil;
import com.yuminsoft.com.autoweb.selenium.dao.SlnmPageTempDao;
import com.yuminsoft.com.autoweb.selenium.form.SlnmPageTempVo;
import com.yuminsoft.com.autoweb.selenium.model.SlnmLocatorTemp;
import com.yuminsoft.com.autoweb.selenium.model.SlnmPageTemp;
import com.yuminsoft.com.autoweb.selenium.service.SlnmPageTempService;

@Service
public class SlnmPageTempServiceImpl implements SlnmPageTempService {
    @Autowired
    protected SlnmPageTempDao slnmPageTempDao;
    @Autowired
    protected SysMaxKeyValueDao sysMaxKeyValueDao;
    
    @Override
    public Page<SlnmPageTemp> getPage(SlnmPageTempVo form) {
        Page<SlnmPageTemp> page = slnmPageTempDao.getPage(form);
        
        for(SlnmPageTemp slnmPage : page.getResults()){
            //定位器信息
            List<SlnmLocatorTemp> locatorlist = this.getSlnmLocatorTempList(slnmPage.getPagecode());
            slnmPage.setLocatorlist(locatorlist);
        }
        
        return page;
    }
    
    @Override
    public SlnmPageTemp getSlnmPageTemp(Integer id) {
        SlnmPageTemp bean = slnmPageTempDao.getSlnmPageTemp(id);
        return bean;
    }

    @Override
    public SlnmPageTemp getSlnmPageTemp(String pagecode) {
        SlnmPageTemp bean = slnmPageTempDao.getSlnmPageTemp(pagecode);
        return bean;
    }
    
    @Override
    public SlnmPageTemp getSlnmPageTemp(String casecode,String pagename){
        SlnmPageTemp bean = slnmPageTempDao.getSlnmPageTemp(casecode,pagename);
        return bean;
    }

    @Override
    public List<SlnmPageTemp> getSlnmPageTempList() {
        List<SlnmPageTemp> list = slnmPageTempDao.getSlnmPageTempList();
        return list;
    }

    @Override
    @Transactional()
    public void save(SlnmPageTempVo form) {
        SlnmPageTemp model = slnmPageTempDao.getSlnmPageTemp("0",form.getPagename());
        if(model != null){
            throw new BasicException("该页面模板已经存在");
        }
        
        SlnmPageTemp bean = new SlnmPageTemp();
        BeanUtils.copyProperties(form, bean);
        
        //页面代码
        int id = sysMaxKeyValueDao.getMaxValue("slnm_page_temp");
        String pagecode = StringUtil.formatLeft(id, "000000");
        
        bean.setPagecode(pagecode);
        bean.setIsrun(1);
        bean.setOptcode(form.getOptcode());
        bean.setCreatetime(new Date());
        bean.setModifiedtime(new Date());
        
        this.slnmPageTempDao.save(bean);
    }
    
    @Override
    @Transactional()
    public void update(SlnmPageTempVo form) {
        Integer id = form.getId();
        SlnmPageTemp model = slnmPageTempDao.getSlnmPageTemp(id);
        if(model == null){
            throw new BasicException("该页面模板不存在");
        }
        String pagename = form.getPagename();
        if(!pagename.equals(model.getPagename())){
            SlnmPageTemp model0 = slnmPageTempDao.getSlnmPageTemp(model.getCasecode(), pagename);
            if(model0 != null){
                throw new BasicException("页面模板已经存在");
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
        
        this.slnmPageTempDao.update(model);
    }
    
    @Override
    @Transactional()
    public void delete(Integer id) {
        SlnmPageTemp model = slnmPageTempDao.getSlnmPageTemp(id);
        if(model == null){
            throw new BasicException("该页面模板不存在");
        }
        int ret = this.slnmPageTempDao.delete(id);
        if(ret == 0){
            throw new BasicException("该页面模板不存在或已删除");
        }

        //删除元素定位器模板
        this.slnmPageTempDao.deleteLocatorTempByPagecode(model.getPagecode());
    }

    @Override
    @Transactional()
    public void doCopyPage(SlnmPageTempVo form) {
        Integer id = form.getId();
        SlnmPageTemp model = slnmPageTempDao.getSlnmPageTemp(id);
        if(model == null){
            throw new BasicException("该页面模板不存在");
        }
        String pagename = form.getPagename();        
        
        SlnmPageTemp bean = new SlnmPageTemp();
        //页面代码
        int id0 = sysMaxKeyValueDao.getMaxValue("slnm_page_temp");
        String pagecode = StringUtil.formatLeft(id0, "000000");
        bean.setPagecode(pagecode);
        
        bean.setCasecode(form.getCasecode());
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
        this.slnmPageTempDao.save(bean);
        
        //复制定位器
        List<SlnmLocatorTemp> locatorlist = slnmPageTempDao.getSlnmLocatorTempList(model.getPagecode());
        for(SlnmLocatorTemp slnmLocator0 : locatorlist){
            SlnmLocatorTemp slnmLocator = new SlnmLocatorTemp();
            BeanUtils.copyProperties(slnmLocator0, slnmLocator);
            slnmLocator.setId(null);
            slnmLocator.setPagecode(pagecode);
            //定位器代码
            int id1 = sysMaxKeyValueDao.getMaxValue("slnm_locator_temp");
            String locatorcode = StringUtil.formatLeft(id1, "00000000");
            slnmLocator.setLocatorcode(locatorcode);
            this.slnmPageTempDao.save(slnmLocator);
        }
    }

    @Override
    @Transactional()
    public void changePageOrder(Integer id, Integer changedid) {
        SlnmPageTemp model = slnmPageTempDao.getSlnmPageTemp(id);
        if(model == null){
            throw new BasicException("该页面模板不存在");
        }
        SlnmPageTemp model1 = slnmPageTempDao.getSlnmPageTemp(changedid);
        if(model1 == null){
            throw new BasicException("被交换页面模板不存在");
        }
        
        //互换排序号
        Integer orderno = model.getOrderno();
        model.setOrderno(model1.getOrderno());
        model1.setOrderno(orderno);
        
        this.slnmPageTempDao.update(model);        
        this.slnmPageTempDao.update(model1);
    }
    
    @Override
    public SlnmLocatorTemp getSlnmLocatorTemp(Integer id) {
        SlnmLocatorTemp bean = slnmPageTempDao.getSlnmLocatorTemp(id);
        return bean;
    }

    @Override
    public SlnmLocatorTemp getSlnmLocatorTemp(String pagecode, String locatorname) {
        SlnmLocatorTemp bean = slnmPageTempDao.getSlnmLocatorTemp(pagecode,locatorname);
        return bean;
    }
    
    @Override
    public List<SlnmLocatorTemp> getSlnmLocatorTempList(String pagecode) {
        List<SlnmLocatorTemp> list = slnmPageTempDao.getSlnmLocatorTempList(pagecode);
        return list;
    }
    
    @Override
    @Transactional()
    public void doAddLocator(SlnmLocatorTempVo form) {
        String pagecode = form.getPagecode();
        if(StringUtil.isNull(pagecode)){
            throw new BasicException("页面模板代码为空");
        }
        SlnmLocatorTemp model = slnmPageTempDao.getSlnmLocatorTemp(pagecode,form.getLocatorname());
        if(model != null){
            //throw new BasicException("该元素已经存在");
        }
        
        SlnmLocatorTemp bean = new SlnmLocatorTemp();
        BeanUtils.copyProperties(form, bean);
        
        //定位器代码
        int id = sysMaxKeyValueDao.getMaxValue("slnm_locator_temp");
        String locatorcode = StringUtil.formatLeft(id, "00000000");
        
        bean.setLocatorcode(locatorcode);        
        bean.setCreatetime(new Date());
        bean.setModifiedtime(new Date());
        
        this.slnmPageTempDao.save(bean);
    }

    @Override
    @Transactional()
    public void doUpdateLocator(SlnmLocatorTempVo form) {
        Integer id = form.getId();
        SlnmLocatorTemp model = slnmPageTempDao.getSlnmLocatorTemp(id);
        if(model == null){
            throw new BasicException("该元素不存在");
        }
        String locatorname = form.getLocatorname();
        if(!locatorname.equals(model.getLocatorname())){
            SlnmLocatorTemp model0 = slnmPageTempDao.getSlnmLocatorTemp(model.getPagecode(), locatorname);
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
        
        this.slnmPageTempDao.update(model);
    }

    @Override
    @Transactional()
    public void doCopyLocator(SlnmLocatorTempVo form) {
        Integer id = form.getId();
        SlnmLocatorTemp model = slnmPageTempDao.getSlnmLocatorTemp(id);
        if(model == null){
            throw new BasicException("该元素不存在");
        }
        
        SlnmLocatorTemp bean = new SlnmLocatorTemp();

        //定位器代码
        int id0 = sysMaxKeyValueDao.getMaxValue("slnm_locator_temp");
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
        
        this.slnmPageTempDao.save(bean);
    }

    @Override
    @Transactional()
    public void doMoveLocator(SlnmLocatorTempVo form) {
        Integer id = form.getId();
        SlnmLocatorTemp model = slnmPageTempDao.getSlnmLocatorTemp(id);
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
        
        this.slnmPageTempDao.update(model);
    }

    @Override
    @Transactional()
    public void doDeleteLocator(Integer id) {
        int ret = this.slnmPageTempDao.deleteLocator(id);
        if(ret == 0){
            throw new BasicException("该元素不存在或已删除");
        }
    }

    @Override
    @Transactional()
    public void changeLocatorOrder(Integer id, Integer changedid) {
        SlnmLocatorTemp model = slnmPageTempDao.getSlnmLocatorTemp(id);
        if(model == null){
            throw new BasicException("该元素不存在");
        }
        SlnmLocatorTemp model1 = slnmPageTempDao.getSlnmLocatorTemp(changedid);
        if(model1 == null){
            throw new BasicException("被交换元素不存在");
        }
        
        //互换排序号
        Integer orderno = model.getOrderno();
        model.setOrderno(model1.getOrderno());
        model1.setOrderno(orderno);
        
        this.slnmPageTempDao.update(model);        
        this.slnmPageTempDao.update(model1);
    }
    
}
