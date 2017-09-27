package com.yuminsoft.com.autoweb.selenium.dao;

import java.util.List;

import com.yuminsoft.com.autoweb.common.page.Page;
import com.yuminsoft.com.autoweb.selenium.form.SlnmChapterVo;
import com.yuminsoft.com.autoweb.selenium.model.SlnmPage;
import org.springframework.stereotype.Repository;

import com.yuminsoft.com.autoweb.base.dao.BasicHibernateDao;
import com.yuminsoft.com.autoweb.common.util.StringUtil;
import com.yuminsoft.com.autoweb.selenium.form.SlnmCaseVo;
import com.yuminsoft.com.autoweb.selenium.model.SlnmCase;
import com.yuminsoft.com.autoweb.selenium.model.SlnmChapter;
import com.yuminsoft.com.autoweb.selenium.model.SlnmLocator;

@Repository
public class SeleniumDao extends BasicHibernateDao {

    @Override
    public Class getTheEntityClass() {
        return this.getClass();
    }
    
    public Page<SlnmCase> getPage(SlnmCaseVo form) {
        StringBuilder hql = new StringBuilder("from SlnmCase where 1=1 ");
        
        //id
        if(StringUtil.isNotNull(form.getId())){
            hql.append(" and id=:id ");
        }
        //操作员代码 
        if(StringUtil.isNotNull(form.getOptcode())){
            hql.append(" and optcode=:optcode ");
        }
        
        //排序
        hql.append(" order by modifiedtime desc,createtime desc ");
        
        return this.queryPage(hql.toString(), form);
    }

    public List<SlnmCase> getSlnmCaseList() {
        StringBuilder hql = new StringBuilder("from SlnmCase order by createtime ");
        
        return this.queryListHql(hql.toString());
    }
    
    public SlnmCase getSlnmCase(Integer id) {
        StringBuilder hql = new StringBuilder("from SlnmCase where id=? ");
        
        return (SlnmCase) this.queryUniqueHql(hql.toString(), id);
    }
    
    public SlnmCase getSlnmCase(String casecode) {
        StringBuilder hql = new StringBuilder("from SlnmCase where casecode=? ");
        
        return (SlnmCase) this.queryUniqueHql(hql.toString(), casecode);
    }
    
    public SlnmCase getSlnmCaseByName(String casename) {
        StringBuilder hql = new StringBuilder("from SlnmCase where casename=? ");
        
        return (SlnmCase) this.queryUniqueHql(hql.toString(), casename);
    }
    
    public Page<SlnmChapter> getChapterPage(SlnmChapterVo form) {
        StringBuilder hql = new StringBuilder("from SlnmChapter where 1=1 ");
        
        //用例代码
        if(StringUtil.isNotNull(form.getCasecode())){
            hql.append(" and casecode=:casecode ");
        }
        
        //排序
        hql.append(" order by orderno,modifiedtime ");
        
        return this.queryPage(hql.toString(), form);
    }
    
    public List<SlnmChapter> getSlnmChapterList() {
        StringBuilder hql = new StringBuilder("from SlnmChapter order by orderno ");
        
        return this.queryListHql(hql.toString());
    }
    
    public List<SlnmChapter> getSlnmChapterList(String casecode) {
        StringBuilder hql = new StringBuilder("from SlnmChapter where casecode=? order by orderno ");
        
        return this.queryListHql(hql.toString(),casecode);
    }

    public SlnmChapter getSlnmChapter(Integer id) {
        StringBuilder hql = new StringBuilder("from SlnmChapter where id=? ");
        
        return (SlnmChapter) this.queryUniqueHql(hql.toString(), id);
    }
    
    public SlnmChapter getSlnmChapter(String chaptercode) {
        StringBuilder hql = new StringBuilder("from SlnmChapter where chaptercode=? ");
        
        return (SlnmChapter) this.queryUniqueHql(hql.toString(), chaptercode);
    }
    
    public SlnmChapter getSlnmChapter(String casecode, String chaptername) {
        StringBuilder hql = new StringBuilder("from SlnmChapter where casecode=? and chaptername=? ");
        
        return (SlnmChapter) this.queryUniqueHql(hql.toString(), casecode, chaptername);
    }
    
    public int deleteChapter(Integer id) {
        StringBuilder hql = new StringBuilder("delete from SlnmChapter where id=? ");
        return this.executeUpdateHql(hql.toString(), id);
    }
    
    public int deleteChapterByCasecode(String casecode) {
        StringBuilder hql = new StringBuilder("delete from SlnmChapter where casecode=? ");
        return this.executeUpdateHql(hql.toString(), casecode);
    }
    
    public int delete(Integer id) {
        StringBuilder hql = new StringBuilder("delete from SlnmCase where id=? ");
        return this.executeUpdateHql(hql.toString(), id);
    }
    
    public List<SlnmPage> getSlnmPageList() {
        StringBuilder hql = new StringBuilder("from SlnmPage order by orderno ");
        
        return this.queryListHql(hql.toString());
    }

    public List<SlnmPage> getSlnmPageList(String chaptercode) {
        StringBuilder hql = new StringBuilder("from SlnmPage where chaptercode=? order by orderno ");
        
        return this.queryListHql(hql.toString(), chaptercode);
    }
    
    public SlnmPage getSlnmPage(Integer id) {
        StringBuilder hql = new StringBuilder("from SlnmPage where id=? ");
        
        return (SlnmPage) this.queryUniqueHql(hql.toString(), id);
    }
    
    public SlnmPage getSlnmPage(String pagecode) {
        StringBuilder hql = new StringBuilder("from SlnmPage where pagecode=? ");
        
        return (SlnmPage) this.queryUniqueHql(hql.toString(), pagecode);
    }
    
    public SlnmPage getSlnmPage(String chaptercode,String pagename) {
        StringBuilder hql = new StringBuilder("from SlnmPage where chaptercode=? and pagename=? ");
        
        return (SlnmPage) this.queryUniqueHql(hql.toString(), chaptercode,pagename);
    }
    
    public int deletePage(Integer id) {
        StringBuilder hql = new StringBuilder("delete from SlnmPage where id=? ");
        return this.executeUpdateHql(hql.toString(), id);
    }
    
    public int deletePageByChaptercode(String chaptercode) {
        StringBuilder hql = new StringBuilder("delete from SlnmPage where chaptercode=? ");
        return this.executeUpdateHql(hql.toString(), chaptercode);
    }

    public List<SlnmLocator> getSlnmLocatorList(String pagecode) {
        StringBuilder hql = new StringBuilder("from SlnmLocator where pagecode=? order by orderno,createtime");
        
        return this.queryListHql(hql.toString(), pagecode);
    }
    
    public SlnmLocator getSlnmLocator(Integer id) {
        StringBuilder hql = new StringBuilder("from SlnmLocator where id=? ");
        
        return (SlnmLocator) this.queryUniqueHql(hql.toString(), id);
    }
    
    public SlnmLocator getSlnmLocator(String pagecode, String locatorname) {
        StringBuilder hql = new StringBuilder("from SlnmLocator where pagecode=? and locatorname=? ");
        
        return (SlnmLocator) this.queryUniqueHql(hql.toString(), pagecode,locatorname);
    }
    
    public int deleteLocator(Integer id) {
        StringBuilder hql = new StringBuilder("delete from SlnmLocator where id=? ");
        return this.executeUpdateHql(hql.toString(), id);
    }
    
    public int deleteLocatorByPagecode(String pagecode) {
        StringBuilder hql = new StringBuilder("delete from SlnmLocator where pagecode=? ");
        return this.executeUpdateHql(hql.toString(), pagecode);
    }

}
