package com.yuminsoft.com.autoweb.common.util;

import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 日期工具类
 * FileName:    DateUtil.java  
 * @author: YM10095
 * @version:    1.0  
 * @date:   	2017年7月29日 下午10:31:40
 */
public class DateUtil {
	
	/**
	 * 将时间格式字符串转换为时间，如：yyyy-MM-dd HH:mm:ss
	 * @param dateStr
	 * @param pattern
	 * @return
	 * @author: YM10095
	 * @date:	2017年6月1日 上午10:30:09
	 */
	public static Date getDate(String dateStr,String pattern) {
	   SimpleDateFormat formatter = new SimpleDateFormat(pattern);
	   ParsePosition pos = new ParsePosition(0);
	   Date strtodate = formatter.parse(dateStr, pos);
	   
	   return strtodate;
	}
	
	/**
	 * 将时间转换为字符串，如：yyyy-MM-dd HH:mm:ss
	 * @param date
	 * @param pattern
	 * @return
	 * @author: YM10095
	 * @date:	2017年6月1日 上午10:31:55
	 */
	public static String getDateStr(Date date,String pattern) {
	   SimpleDateFormat formatter = new SimpleDateFormat(pattern);
	   String dateString = formatter.format(date);
	   
	   return dateString;
	}
}
