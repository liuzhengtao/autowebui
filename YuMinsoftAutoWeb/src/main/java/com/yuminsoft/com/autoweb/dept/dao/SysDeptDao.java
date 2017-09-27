package com.yuminsoft.com.autoweb.dept.dao;

import java.util.List;

import com.yuminsoft.com.autoweb.base.dao.BasicHibernateDao;
import com.yuminsoft.com.autoweb.common.page.Page;
import com.yuminsoft.com.autoweb.dept.form.SysDeptVo;
import com.yuminsoft.com.autoweb.dept.model.SysDept;
import org.springframework.stereotype.Repository;

@Repository
public class SysDeptDao extends BasicHibernateDao {

    @Override
    public Class getTheEntityClass() {
        return this.getClass();
    }
    
    public Page<SysDept> getPage(SysDeptVo form) {
        StringBuilder hql = new StringBuilder("from SysDept order by createtime desc ");
        
        return this.queryPage(hql.toString(), form);
    }
    
    public List<SysDept> getSysDeptList() {
        StringBuilder hql = new StringBuilder("from SysDept order by createtime desc ");
        
        return this.queryListHql(hql.toString());
    }
    
    public SysDept getSysDept(Integer id) {
        StringBuilder hql = new StringBuilder("from SysDept where id=? ");
        
        return (SysDept) this.queryUniqueHql(hql.toString(), id);
    }
    
    public SysDept getSysDept(String deptcode) {
        StringBuilder hql = new StringBuilder("from SysDept where deptcode=? ");
        
        return (SysDept) this.queryUniqueHql(hql.toString(), deptcode);
    }
    
    public SysDept getSysDeptByName(String deptname) {
        StringBuilder hql = new StringBuilder("from SysDept where deptname=? ");
        
        return (SysDept) this.queryUniqueHql(hql.toString(), deptname);
    }
    
    public int delete(Integer id) {
        StringBuilder hql = new StringBuilder("delete from SysDept where id=? ");
        return this.executeUpdateHql(hql.toString(), id);
    }
    
    public int deleteByPdeptcode(String pdeptcode) {
        StringBuilder hql = new StringBuilder("delete from SysDept where pdeptcode=? ");
        return this.executeUpdateHql(hql.toString(), pdeptcode);
    }

}
