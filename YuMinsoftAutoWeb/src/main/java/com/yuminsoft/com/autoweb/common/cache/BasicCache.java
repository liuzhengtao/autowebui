package com.yuminsoft.com.autoweb.common.cache;

import java.util.Date;
import com.opensymphony.oscache.base.NeedsRefreshException;
import com.opensymphony.oscache.general.GeneralCacheAdministrator;

/**
 * 
 * FileName:    BasicCache.java  
 * @author: YM10095
 * @version:    1.0  
 * @date:   	2017年7月29日 下午10:08:54
 */
public class BasicCache extends GeneralCacheAdministrator {
    private static final long serialVersionUID = -4397192926052141162L;
    private String            keyPrefix;                               //关键字前缀字符，区别属于哪个模块
    private int               refreshPeriod;                           //过期时间(单位为秒); 

    public BasicCache(String keyPrefix, int refreshPeriod) {
        super();
        this.keyPrefix = keyPrefix;
        this.refreshPeriod = refreshPeriod;

    }

    /**
     * 添加被缓存的对象
     * @param key
     * @param value
     * @author: YM10095
     * @date:	2017年6月21日 下午7:21:55
     */
    public void put(String key, Object value) {
        this.putInCache(this.keyPrefix + "_" + key, value);
    }

    /**
     * 删除被缓存的对象
     * @param key
     * @author: YM10095
     * @date:	2017年6月21日 下午7:22:03
     */
    public void remove(String key) {
        this.flushEntry(this.keyPrefix + "_" + key);
    }

    /**
     * 删除指定日期所有被缓存的对象
     * @param date
     * @author: YM10095
     * @date:	2017年6月21日 下午7:22:15
     */
    public void removeAll(Date date) {
        this.flushAll(date);
    }

    /**
     * 删除所有被缓存的对象
     * @author: YM10095
     * @date:	2017年6月21日 下午7:23:20
     */
    public void removeAll() {
        this.flushAll();
    }

    /**
     * 获取被缓存的对象
     * @param key
     * @return
     * @throws Exception
     * @author: YM10095
     * @date:	2017年6月21日 下午7:23:36
     */
    public Object get(String key) throws Exception {
        try {
            return this.getFromCache(this.keyPrefix + "_" + key, this.refreshPeriod);
        } catch (NeedsRefreshException e) {
            this.cancelUpdate(this.keyPrefix + "_" + key);
            e.printStackTrace();
            throw e;
        }
    }
}