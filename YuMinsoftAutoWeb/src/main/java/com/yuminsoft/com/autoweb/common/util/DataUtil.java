package com.yuminsoft.com.autoweb.common.util;

import java.text.DecimalFormat;

/**
 * 数字工具类
 * FileName:    DataUtil.java  
 * @author: YM10095
 * @version:    1.0  
 * @date:   	2017年7月29日 下午10:31:28
 */
public class DataUtil {
    
    /**
     * 数字格式化    '###,###.000'
     * @param value
     * @param formatstr
     * @return
     * @author: YM10095
     * @date:	2017年6月21日 上午11:48:02
     */
    public static String formatNumber(Long value,String formatstr){
        DecimalFormat decimalFormat = new DecimalFormat(formatstr);
        String retval = decimalFormat.format(value);
        
        return retval;
    }
}
