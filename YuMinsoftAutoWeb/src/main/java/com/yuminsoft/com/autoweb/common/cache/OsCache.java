package com.yuminsoft.com.autoweb.common.cache;

/**
 * 
 * FileName:    OsCache.java  
 * @author: YM10095
 * @version:    1.0  
 * @date:   	2017年7月29日 下午10:09:01
 */
public class OsCache {
    private static BasicCache basicCache;
    private static OsCache instance;

    private OsCache() {
        //根据配置文件来初始BaseCache
        basicCache = new BasicCache("", 1000000);
    }
    
    public static OsCache getInstance() {
        if (instance == null) {
            instance = new OsCache();
        }
        return instance;
    }
    
    /**
     * 将对象存入缓存中
     * @param key
     * @param value
     * @author: YM10095
     * @date:	2017年6月21日 下午7:21:27
     */
    public void put(String key, Object value) {
        basicCache.put(key, value);
    }
    
    public void remove(String key) {
        basicCache.remove(key);
    }
    
    /**
     * 删除缓存中所有的对象
     * @author: YM10095
     * @date:	2017年6月21日 下午7:22:53
     */
    public void removeAll() {
        basicCache.removeAll();
    }
    
    /**
     * 从缓存中提取对应的对象
     * @param key
     * @param obj
     * @return
     * @author: YM10095
     * @date:	2017年6月21日 下午7:26:09
     */
    public Object get(String key) {
        try {
            return basicCache.get(key);
        } catch (Exception e) {            
            e.printStackTrace();
        }
        return null;
    }

}
