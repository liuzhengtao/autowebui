package com.yuminsoft.com.autoweb.base.dao;

import java.io.Serializable;
import java.util.List;

import javax.annotation.Resource;

import com.google.gson.Gson;
import com.yuminsoft.com.autoweb.common.page.Page;
import com.yuminsoft.com.autoweb.common.page.PageQueryInfo;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate5.HibernateCallback;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;
import org.springframework.util.Assert;

import com.yuminsoft.com.autoweb.common.util.StringUtil;

public abstract class BasicHibernateDao<E, PK extends Serializable> extends HibernateDaoSupport {
    protected Logger logger = LoggerFactory.getLogger(BasicHibernateDao.class);
    
    @Resource(name = "sessionFactory")
    public void setMySessionFactory(SessionFactory sessionFactory) {
        super.setSessionFactory(sessionFactory);
    }
    
    public abstract Class getTheEntityClass();
    
    public void save(E entity) throws DataAccessException {
        this.getHibernateTemplate().save(entity);
    }
    
    public void saveOrUpdate(E entity) throws DataAccessException {
        this.getHibernateTemplate().saveOrUpdate(entity);
    }
    
    public void update(E entity) throws DataAccessException {
        this.getHibernateTemplate().update(entity);
    }
    
    public void delete(Object entity) throws DataAccessException {
        this.getHibernateTemplate().delete(entity);
    }
    
    public int executeUpdateHql(final String hql, final Object... args) throws DataAccessException {
        return ((Integer) getHibernateTemplate().execute(new HibernateCallback() {
            public Object doInHibernate(Session session) throws HibernateException {
                String new_hql = hql;
                Query query = session.createQuery(new_hql);
                BasicHibernateDao.this.setParameters(new_hql, query, args);
                int i = query.executeUpdate();
                session.flush();
                return Integer.valueOf(i);
            }
        })).intValue();
    }
    
    public int executeUpdateSql(final String sql, final Object... args) throws DataAccessException {
        return ((Integer) getHibernateTemplate().execute(new HibernateCallback() {
            public Object doInHibernate(Session session) throws HibernateException {
                String new_sql = sql;
                Query query = session.createSQLQuery(new_sql);
                BasicHibernateDao.this.setParameters(new_sql, query, args);
                int i = query.executeUpdate();
                session.flush();
                return Integer.valueOf(i);
            }
        })).intValue();
    }
    
    public Object queryUniqueHql(final String hql, final Object... args) throws DataAccessException {
        return getHibernateTemplate().execute(new HibernateCallback() {
            public Object doInHibernate(Session session) throws HibernateException {
                String new_hql = hql;
                Query query = session.createQuery(new_hql);
                BasicHibernateDao.this.setParameters(new_hql, query, args);
                return query.uniqueResult();
            }
        });
    }
    
    public Object queryUniqueSql(final String sql, final Object... args) throws DataAccessException {
        return getHibernateTemplate().execute(new HibernateCallback() {
            public Object doInHibernate(Session session) throws HibernateException {
                String new_sql = sql;
                Query query = session.createSQLQuery(new_sql);
                BasicHibernateDao.this.setParameters(new_sql, query, args);
                return query.uniqueResult();
            }
        });
    }
    
    public List<E> queryListHql(final String hql, final Object... args) throws DataAccessException {
        return (List) getHibernateTemplate().execute(new HibernateCallback() {
            public Object doInHibernate(Session session) throws HibernateException {
                String new_hql = hql;
                Query query = session.createQuery(new_hql);
                BasicHibernateDao.this.setParameters(new_hql, query, args);
                return query.list();
            }
        });
    }
    
    public List<?> queryListSql(final String sql, final Object... args) throws DataAccessException {
        return (List) getHibernateTemplate().execute(new HibernateCallback() {
            public Object doInHibernate(Session session) throws HibernateException {
                String new_sql = sql;
                SQLQuery query = session.createSQLQuery(new_sql);
                BasicHibernateDao.this.setParameters(new_sql, query, args);
                return query.list();
            }
        });
    }
    
    public Page<E> queryPage(String hql, PageQueryInfo pageQueryInfo) throws DataAccessException {
        String count_hql = "select count(1) " +  this.removeOrderKeys(this.removeSelectKeys(hql));
        return queryPage(hql, count_hql, pageQueryInfo);
    }
    
    protected Page<E> queryPage(final String hql, final String count_hql, final PageQueryInfo pageQueryInfo) {
        return (Page) getHibernateTemplate().execute(new HibernateCallback() {
            public Object doInHibernate(Session session) throws HibernateException {
                PageQueryInfo pq = null == pageQueryInfo ? new PageQueryInfo() : pageQueryInfo;
                String new_hql = hql;
                String new_count_hql = count_hql;
                Query query = session.createQuery(new_hql);
                Query countQuery = session.createQuery(new_count_hql);
                BasicHibernateDao.this.setParameters(new_hql, query, new Object[] { pq });
                BasicHibernateDao.this.setParameters(new_count_hql, countQuery, new Object[] { pq });
                Number totalCount = BasicHibernateDao.this.getTotalCounts(new_count_hql, session, countQuery);
                
                return BasicHibernateDao.this.executeQueryForPage(query, totalCount, pq.getPageNumber(), pq.getPageSize());
            }
        });
    }
    
    protected Page<E> queryPageSql(final String sql, final Number totalCount, final PageQueryInfo pageQueryInfo) {
        return (Page) getHibernateTemplate().execute(new HibernateCallback() {
            public Object doInHibernate(Session session) throws HibernateException {
                PageQueryInfo pq = null == pageQueryInfo ? new PageQueryInfo() : pageQueryInfo;
                String new_sql = sql;
                Query query = session.createSQLQuery(new_sql);
                BasicHibernateDao.this.setParameters(new_sql, query, new Object[] { pq });
                return BasicHibernateDao.this.executeQueryForPage(query, totalCount, pq.getPageNumber(), pq.getPageSize());
            }
        });
    }
    
    protected Number getTotalCounts(String count_hql, Session session, Query countQuery) {
        Number totalCount = null;
        try {
            totalCount = (Number) countQuery.uniqueResult();
        } catch (Exception e) {
            totalCount = Integer.valueOf(countQuery.list().size());
        }
        return totalCount == null ? Integer.valueOf(0) : totalCount;
    }
    
    protected Page<E> executeQueryForPage(Query query, Number totalCount, int pageNo, int pageSize) {
        Assert.isTrue(pageSize > 0, "pageSize must larger than 0");
        Page<E> page = new Page(pageNo, totalCount.intValue(), pageSize);
        if ((totalCount != null) && (totalCount.intValue() > 0)) {
            page.setResults(query.setFirstResult(page.getFirstRecord()).setMaxResults(page.getPageSize()).list());
        }
        return page;
    }
    
    public String removeSelectKeys(String hql){
        if(StringUtil.isNotNull(hql)){
            String hql0 = hql.toUpperCase();
            int select_index = hql0.indexOf("SELECT ");
            int from_index = hql0.indexOf("FROM ");
            //
            if(select_index >= 0 && from_index >= 0 && select_index < from_index){
                hql = hql.replace(hql.substring(select_index, from_index),"");
            }
        }
        
        return hql;
    }
    
    public String removeOrderKeys(String hql){
        if(StringUtil.isNotNull(hql)){
            String hql0 = hql.toUpperCase();
            int order_index = hql0.lastIndexOf(" ORDER ");
            if(order_index >= 0){
                int rightparentheses_index = hql0.lastIndexOf(")");
                if(rightparentheses_index >= 0){
                    if(order_index > rightparentheses_index){
                        hql = hql.replace(hql.substring(order_index),"");
                    }
                }else{
                    hql = hql.replace(hql.substring(order_index),"");
                }
            }
        }
        
        return hql;
    }
    
    public void refresh(Object entity) throws DataAccessException {
        this.getHibernateTemplate().refresh(entity);
    }
    
    public void flush() throws DataAccessException {
        this.getHibernateTemplate().flush();
    }

    protected void setParameters(String hql, Query query, Object... args) {
        Assert.isTrue(StringUtil.isNotNull(hql), "hql can not be null");
        if (hql.indexOf("?") >= 0) {
            this.setQueryParameters(query, args);
        } else {
            this.setQueryProperties(query, args);
        }
    }
    
    protected void setQueryParameters(Query query, Object... args) {
        if (args == null) {
            return;
        }
        int i = 0;
        for (Object arg : args) {
            query.setParameter(i++, arg);
        }
    }
    
    protected void setQueryProperties(Query query, Object... args) {
        if (args == null) {
            return;
        }
        for (Object arg : args) {
            if (null != arg) {
                query.setProperties(arg);
            }
        }
    }
    
}
