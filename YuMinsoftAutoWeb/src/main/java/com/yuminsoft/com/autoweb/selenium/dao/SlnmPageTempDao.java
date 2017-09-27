package com.yuminsoft.com.autoweb.selenium.dao;

import java.util.List;

import com.yuminsoft.com.autoweb.common.page.Page;
import org.springframework.stereotype.Repository;

import com.yuminsoft.com.autoweb.base.dao.BasicHibernateDao;
import com.yuminsoft.com.autoweb.common.util.StringUtil;
import com.yuminsoft.com.autoweb.selenium.form.SlnmPageTempVo;
import com.yuminsoft.com.autoweb.selenium.model.SlnmLocatorTemp;
import com.yuminsoft.com.autoweb.selenium.model.SlnmPageTemp;

@Repository
public class SlnmPageTempDao extends BasicHibernateDao {

    @Override
    public Class getTheEntityClass() {
        return this.getClass();
    }
    
    public Page<SlnmPageTemp> getPage(SlnmPageTempVo form) {
        StringBuilder hql = new StringBuilder("from SlnmPageTemp where 1=1 ");
        
        //id
        if(StringUtil.isNotNull(form.getId())){
            hql.append(" and id=:id ");
        }
        //操作员代码 
        if(StringUtil.isNotNull(form.getOptcode())){
            hql.append(" and optcode=:optcode ");
        }
        
        //排序
        hql.append(" order by orderno,modifiedtime,createtime ");
        
        return this.queryPage(hql.toString(), form);
    }
    
    public SlnmPageTemp getSlnmPageTemp(Integer id) {
        StringBuilder hql = new StringBuilder("from SlnmPageTemp where id=? ");
        
        return (SlnmPageTemp) this.queryUniqueHql(hql.toString(), id);
    }
    
    public SlnmPageTemp getSlnmPageTemp(String pagecode) {
        StringBuilder hql = new StringBuilder("from SlnmPageTemp where pagecode=? ");
        
        return (SlnmPageTemp) this.queryUniqueHql(hql.toString(), pagecode);
    }
    
    public SlnmPageTemp getSlnmPageTemp(String casecode,String pagename) {
        StringBuilder hql = new StringBuilder("from SlnmPageTemp where casecode=? and pagename=? ");
        
        return (SlnmPageTemp) this.queryUniqueHql(hql.toString(), casecode,pagename);
    }
    
    public List<SlnmPageTemp> getSlnmPageTempList() {
        StringBuilder hql = new StringBuilder("from SlnmPageTemp order by orderno ");
        
        return this.queryListHql(hql.toString());
    }
    
    public int delete(Integer id) {
        StringBuilder hql = new StringBuilder("delete from SlnmPageTemp where id=? ");
        return this.executeUpdateHql(hql.toString(), id);
    }
    
    public int deleteLocatorTempByPagecode(String pagecode) {
        StringBuilder hql = new StringBuilder("delete from SlnmLocatorTemp where pagecode=? ");
        return this.executeUpdateHql(hql.toString(), pagecode);
    }
    
    public SlnmLocatorTemp getSlnmLocatorTemp(Integer id) {
        StringBuilder hql = new StringBuilder("from SlnmLocatorTemp where id=? ");
        
        return (SlnmLocatorTemp) this.queryUniqueHql(hql.toString(), id);
    }
    
    public SlnmLocatorTemp getSlnmLocatorTemp(String pagecode, String locatorname) {
        StringBuilder hql = new StringBuilder("from SlnmLocatorTemp where pagecode=? and locatorname=? ");
        
        return (SlnmLocatorTemp) this.queryUniqueHql(hql.toString(), pagecode,locatorname);
    }
    
    public List<SlnmLocatorTemp> getSlnmLocatorTempList(String pagecode) {
        StringBuilder hql = new StringBuilder("from SlnmLocatorTemp where pagecode=? order by orderno,createtime ");
        
        return this.queryListHql(hql.toString(), pagecode);
    }
    
    public int deleteLocator(Integer id) {
        StringBuilder hql = new StringBuilder("delete from SlnmLocatorTemp where id=? ");
        return this.executeUpdateHql(hql.toString(), id);
    }
    
}
