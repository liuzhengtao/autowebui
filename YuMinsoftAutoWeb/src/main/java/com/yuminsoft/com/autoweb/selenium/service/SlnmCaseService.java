package com.yuminsoft.com.autoweb.selenium.service;

import java.util.List;

import com.yuminsoft.com.autoweb.common.page.Page;
import com.yuminsoft.com.autoweb.selenium.bean.SlnmBean;
import com.yuminsoft.com.autoweb.selenium.form.SlnmCaseVo;
import com.yuminsoft.com.autoweb.selenium.form.SlnmChapterVo;
import com.yuminsoft.com.autoweb.selenium.form.SlnmLocatorVo;
import com.yuminsoft.com.autoweb.selenium.form.SlnmPageVo;
import com.yuminsoft.com.autoweb.selenium.model.SlnmCase;
import com.yuminsoft.com.autoweb.selenium.model.SlnmChapter;
import com.yuminsoft.com.autoweb.selenium.model.SlnmLocator;
import com.yuminsoft.com.autoweb.selenium.model.SlnmPage;

/**
 * 页面信息
 * FileName:    SlnmCaseService.java  
 * @author: YM10095
 * @version:    1.0  
 * @date:   	2017年7月29日 下午10:43:41
 */
public interface SlnmCaseService {
    /**
     * 
     * @param form
     * @return
     * @author: YM10095
     * @date:	2017年7月29日 下午10:43:47
     */
    public Page<SlnmCase> getPage(SlnmCaseVo form);
    
    /**
     * 
     * @return
     * @author: YM10095
     * @date:	2017年7月29日 下午10:43:52
     */
    public List<SlnmCase> getSlnmCaseList();
    
    /**
     * 
     * @param id
     * @return
     * @author: YM10095
     * @date:	2017年7月29日 下午10:43:56
     */
    public SlnmCase getSlnmCase(Integer id);
    
    /**
     * 
     * @param casecode
     * @return
     * @author: YM10095
     * @date:	2017年7月29日 下午10:44:01
     */
    public SlnmCase getSlnmCase(String casecode);
    
    /**
     * 
     * @param casename
     * @return
     * @author: YM10095
     * @date:	2017年7月29日 下午10:44:06
     */
    public SlnmCase getSlnmCaseByName(String casename);
    
    /**
     * 
     * @param id
     * @return
     * @author: YM10095
     * @date:	2017年7月31日 下午3:10:58
     */
    public SlnmBean getSlnmBean(SlnmCaseVo form);
    
    /**
     * 
     * @param form
     * @author: YM10095
     * @date:	2017年7月29日 下午10:44:10
     */
    public void save(SlnmCaseVo form);
    
    /**
     * 
     * @param form
     * @author: YM10095
     * @date:	2017年7月29日 下午10:44:14
     */
    public void update(SlnmCaseVo form);
    
    /**
     * 
     * @param form
     * @param casebean
     * @author: YM10095
     * @date:	2017年7月31日 下午5:34:26
     */
    public void doImportCase(SlnmCaseVo form, SlnmBean slnmbean);
    
    /**
     * 
     * @param id
     * @author: YM10095
     * @date:	2017年7月29日 下午10:44:20
     */
    public void delete(Integer id);
    
    /**
     * 
     * @param id
     * @param isquitbrowser
     * @author: YM10095
     * @date:	2017年7月20日 下午3:55:12
     */
    public void setIsquitbrowser(Integer id,Integer isquitbrowser);
    
    public Page<SlnmChapter> getChapterPage(SlnmChapterVo form);
    
    public List<SlnmChapter> getSlnmChapterList();
    
    public List<SlnmChapter> getSlnmChapterList(String casecode);
    
    public SlnmChapter getSlnmChapter(Integer id);
    
    public SlnmChapter getSlnmChapter(String chaptercode);
    
    public SlnmChapter getSlnmChapter(String casecode,String chaptername);
    
    public void doAddChapter(SlnmChapterVo form);
    
    public void doUpdateChapter(SlnmChapterVo form);
    
    public void doCopyChapter(SlnmChapterVo form);
    
    public void doDeleteChapter(Integer id);
    
    public void changeChapterOrder(Integer id,Integer changedid);
    
    public void setIsRunChapter(Integer id,Integer isrun);
    
    /**
     * 
     * @return
     * @author: YM10095
     * @date:	2017年8月2日 下午3:17:36
     */
    public List<SlnmPage> getSlnmPageList();
    
    /**
     * 
     * @param chaptercode
     * @return
     * @author: YM10095
     * @date:	2017年7月10日 上午11:17:03
     */
    public List<SlnmPage> getSlnmPageList(String chaptercode);
    
    /**
     * 
     * @param id
     * @return
     * @author: YM10095
     * @date:	2017年7月29日 下午10:44:33
     */
    public SlnmPage getSlnmPage(Integer id);
    
    /**
     * 
     * @param pagecode
     * @return
     * @author: YM10095
     * @date:	2017年7月10日 下午3:34:31
     */
    public SlnmPage getSlnmPage(String pagecode);
    
    /**
     * 
     * @param casecode
     * @param pagename
     * @return
     * @author: YM10095
     * @date:	2017年7月29日 下午10:44:38
     */
    public SlnmPage getSlnmPage(String casecode,String pagename);
    
    /**
     * 
     * @param form
     * @author: YM10095
     * @date:	2017年7月29日 下午10:44:42
     */
    public void doAddPage(SlnmPageVo form);
    
    /**
     * 
     * @param form
     * @author: YM10095
     * @date:	2017年8月11日 下午2:37:08
     */
    public void doAddPageFromTemp(SlnmPageVo form);
    
    /**
     * 
     * @param form
     * @author: YM10095
     * @date:	2017年7月10日 下午1:27:59
     */
    public void doUpdatePage(SlnmPageVo form);

    /**
     * 
     * @param form
     * @author: YM10095
     * @date:   2017年8月11日 下午1:26:08
     */
    public void doAddPageToTemp(SlnmPageVo form);
    
    /**
     * 
     * @param form
     * @author: YM10095
     * @date:	2017年7月10日 下午7:18:02
     */
    public void doCopyPage(SlnmPageVo form);
    
    /**
     * 
     * @param id
     * @author: YM10095
     * @date:	2017年7月10日 下午1:28:17
     */
    public void doDeletePage(Integer id);
    
    /**
     * 
     * @param id
     * @param changedid
     * @author: YM10095
     * @date:	2017年7月19日 下午3:50:44
     */
    public void changePageOrder(Integer id,Integer changedid);
    
    /**
     * 
     * @param id
     * @param isrun
     * @author: YM10095
     * @date:	2017年7月20日 下午3:34:19
     */
    public void setIsRunPage(Integer id,Integer isrun);
    
    /**
     * 
     * @param pagecode
     * @return
     * @author: YM10095
     * @date:	2017年7月10日 下午4:14:45
     */
    public List<SlnmLocator> getSlnmLocatorList(String pagecode);
    
    /**
     * 
     * @param id
     * @return
     * @author: YM10095
     * @date:	2017年7月10日 下午3:52:33
     */
    public SlnmLocator getSlnmLocator(Integer id);
    
    /**
     * 
     * @param pagecode
     * @param locatorname
     * @return
     * @author: YM10095
     * @date:	2017年7月10日 下午3:52:38
     */
    public SlnmLocator getSlnmLocator(String pagecode,String locatorname);
    
    /**
     * 
     * @param form
     * @author: YM10095
     * @date:	2017年7月10日 下午3:52:42
     */
    public void doAddLocator(SlnmLocatorVo form);
    
    /**
     * 
     * @param form
     * @author: YM10095
     * @date:	2017年7月10日 下午3:52:46
     */
    public void doUpdateLocator(SlnmLocatorVo form);
    
    /**
     * 
     * @param form
     * @author: YM10095
     * @date:	2017年7月29日 下午10:44:56
     */
    public void doCopyLocator(SlnmLocatorVo form);
    
    /**
     * 
     * @param form
     * @author: YM10095
     * @date:	2017年8月2日 下午7:24:31
     */
    public void doMoveLocator(SlnmLocatorVo form);
    
    /**
     * 
     * @param id
     * @author: YM10095
     * @date:	2017年7月10日 下午3:52:49
     */
    public void doDeleteLocator(Integer id);
    
    /**
     * 
     * @param id
     * @param changedid
     * @author: YM10095
     * @date:	2017年7月29日 下午10:45:00
     */
    public void changeLocatorOrder(Integer id,Integer changedid);
    
}
