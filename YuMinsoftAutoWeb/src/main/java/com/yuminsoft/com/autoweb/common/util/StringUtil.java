package com.yuminsoft.com.autoweb.common.util;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.security.MessageDigest;
import java.text.DecimalFormat;
import java.util.UUID;

/**
 * 字符串工具类
 * FileName:    StringUtil.java  
 * @author: YM10095
 * @version:    1.0  
 * @date:   	2017年7月29日 下午10:32:18
 */
public class StringUtil {


	/**
	 * 判断字符串是否为空(null或者"")
	 * @param str
	 * @return
	 * @author: YM10095
	 * @date:	2017年4月20日 下午1:56:54
	 */
	public static boolean isNull(Object str) {
		return isEmpty(str);
	}
	
	/**
	 * 判断字符串是否不为空
	 * @param str
	 * @return
	 * @author: YM10095
	 * @date:	2017年4月20日 下午1:56:54
	 */
	public static boolean isNotNull(Object str) {
		return !isEmpty(str);
	}
	
	/**
	 * 判断字符串是否为空
	 * @param str
	 * @return
	 * @author: YM10095
	 * @date:	2017年4月20日 下午2:00:06
	 */
	public static boolean isEmpty(Object str) {
		return (str == null || "".equals(str));
	}
	
	/**
	 * 整数左填充
	 * @param intvalue
	 * @param formatstr
	 * @return
	 * @author: YM10095
	 * @date:	2017年4月21日 上午9:58:55
	 */
	public static String formatLeft(Integer intvalue,String formatstr){
		DecimalFormat df = new DecimalFormat(formatstr);
		
		return df.format(intvalue);
	}
	
	/**
	 * 
	 * @param s
	 * @return
	 * @author: YM10095
	 * @date:	2017年6月12日 下午7:01:09
	 */
    public static final String getMD5(String s) {
        char[] hexDigits = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd',
                             'e', 'f' };
        try {
            byte[] strTemp = s.getBytes();
            MessageDigest mdTemp = MessageDigest.getInstance("MD5");
            mdTemp.update(strTemp);
            byte[] md = mdTemp.digest();
            int j = md.length;
            char[] str = new char[j * 2];
            int k = 0;
            for (int i = 0; i < j; i++) {
                byte byte0 = md[i];
                str[(k++)] = hexDigits[(byte0 >>> 4 & 0xF)];
                str[(k++)] = hexDigits[(byte0 & 0xF)];
            }
            return new String(str);
        } catch (Exception e) {
        }
        return null;
    }
    
    /**
     * 
     * @return
     * @author: YM10095
     * @date:	2017年7月29日 下午10:32:44
     */
    public static final String getGuid() {
        String str = UUID.randomUUID().toString();
        str = str.substring(0,8)+str.substring(9,13)+str.substring(14,18)+str.substring(19,23)+str.substring(24);
        return str;
    }

}

