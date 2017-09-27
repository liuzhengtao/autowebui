package com.yuminsoft.com.autoweb.common.dao;

import java.util.Date;

import org.springframework.stereotype.Repository;

import com.yuminsoft.com.autoweb.base.dao.BasicHibernateDao;
import com.yuminsoft.com.autoweb.common.util.DateUtil;

@Repository
public class SysMaxKeyValueDao extends BasicHibernateDao {

    @Override
    public Class getTheEntityClass() {
        return this.getClass();
    }
    
    public int getMaxValue(String keyname) {
        String sql = "select keyvalue from sys_maxkeyvalue where keyname=? and keyprefix is null ";
        Object obj = this.queryUniqueSql(sql, keyname);
        
        int maxvalue = 0;
        if(obj == null){
            sql = "insert into sys_maxkeyvalue(keyname,keyvalue,createtime) values (?,1,now()) ";
            this.executeUpdateSql(sql, keyname);
            maxvalue = 1;
        }else{
            Integer ret = (Integer) obj;
            maxvalue = ret + 1;
            sql = "update sys_maxkeyvalue set keyvalue=keyvalue+1,modifiedtime=now() where keyname=? and keyprefix is null ";
            this.executeUpdateSql(sql, keyname);
        }
        
        return maxvalue;
    }
    
    public int getMaxValue(String keyname, String keyprefix) {
        String sql = "select keyvalue from sys_maxkeyvalue where keyname=? and keyprefix=? ";
        Object obj = this.queryUniqueSql(sql, keyname,keyprefix);
        
        int maxvalue = 0;
        if(obj == null){
            sql = "insert into sys_maxkeyvalue(keyname,keyprefix,keyvalue,createtime) values (?,?,1,now()) ";
            this.executeUpdateSql(sql, keyname,keyprefix);
            maxvalue = 1;
        }else{
            Integer ret = (Integer) obj;
            maxvalue = ret + 1;
            sql = "update sys_maxkeyvalue set keyvalue=keyvalue+1,modifiedtime=now() where keyname=? and keyprefix=? ";
            this.executeUpdateSql(sql, keyname, keyprefix);
        }
        
        return maxvalue;
    }
    
    public int getMaxValueByDate(String keyname) {
        String keyprefix = DateUtil.getDateStr(new Date(), "yyyy-MM-dd");
        
        String sql = "select keyvalue from sys_maxkeyvalue where keyname=? and keyprefix=? ";
        Object obj = this.queryUniqueSql(sql, keyname,keyprefix);
        
        int maxvalue = 0;
        if(obj == null){
            sql = "insert into sys_maxkeyvalue(keyname,keyprefix,keyvalue,createtime) values (?,?,1,now()) ";
            this.executeUpdateSql(sql, keyname,keyprefix);
            maxvalue = 1;
        }else{
            Integer ret = (Integer) obj;
            maxvalue = ret + 1;
            sql = "update sys_maxkeyvalue set keyvalue=keyvalue+1,modifiedtime=now() where keyname=? and keyprefix=? ";
            this.executeUpdateSql(sql, keyname, keyprefix);
        }
        
        return maxvalue;
    }
    
}
