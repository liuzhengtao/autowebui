package com.yuminsoft.com.autoweb.user.dao;

import com.yuminsoft.com.autoweb.common.page.Page;
import com.yuminsoft.com.autoweb.user.form.SysUserVo;
import org.springframework.stereotype.Repository;

import com.yuminsoft.com.autoweb.base.dao.BasicHibernateDao;
import com.yuminsoft.com.autoweb.user.model.SysUser;

@Repository
public class SysUserDao extends BasicHibernateDao {
    @Override
    public Class getTheEntityClass() {
        return this.getClass();
    }
    
    public Page<SysUser> getPage(SysUserVo form) {
        StringBuilder hql = new StringBuilder("from SysUser order by createtime desc ");
        
        return this.queryPage(hql.toString(), form);
    }
    
    public SysUser getSysUser(Integer id) {
        StringBuilder hql = new StringBuilder("from SysUser where id=? ");
        
        return (SysUser) this.queryUniqueHql(hql.toString(), id);
    }
    
    public SysUser getSysUser(String usercode) {
        StringBuilder hql = new StringBuilder("from SysUser where usercode=? ");
        
        return (SysUser) this.queryUniqueHql(hql.toString(), usercode);
    }
    
    public int delete(Integer id) {
        StringBuilder hql = new StringBuilder("delete from SysUser where id=? ");
        return this.executeUpdateHql(hql.toString(), id);
    }
    
    public int getUserQxCount(String usercode,String mncode,String syscode) {
        String sql = "select count(1) from sys_userqx where usercode=? and mncode=? and syscode=? ";
        Object obj = this.queryUniqueSql(sql,usercode, mncode,syscode);
        int retvalue = 0;
        if(obj != null){
            retvalue = new Integer (obj.toString());
        }
        return retvalue;
    }
    
    public int deleteUserQx(String usercode,String syscode) {
        StringBuilder hql = new StringBuilder("delete from SysUserQx where usercode=? and syscode=? ");
        return this.executeUpdateHql(hql.toString(), usercode,syscode);
    }
    
}
