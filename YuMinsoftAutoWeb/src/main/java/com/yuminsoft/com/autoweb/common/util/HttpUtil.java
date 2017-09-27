package com.yuminsoft.com.autoweb.common.util;

import java.net.InetAddress;
import java.net.UnknownHostException;

import javax.servlet.http.HttpServletRequest;

public class HttpUtil {
    /**
     * 
     * @return
     * @author: YM10095
     * @date:	2017年8月20日 下午10:00:15
     */
    public static String getServerIp() {
        String ip = "";
        InetAddress address;
        try {
            address = InetAddress.getLocalHost();
            //
            ip = address.getHostAddress();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        System.err.println("服务器IP：" + ip);
        
        return ip;
    }
    
    /**
     * 
     * @param request
     * @return
     * @author: YM10095
     * @date:	2017年8月15日 下午9:52:18
     */
    public static String getClientIp(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
            if (ip.equals("127.0.0.1")) {
                //根据网卡取本机配置的IP   
                InetAddress inet = null;
                try {
                    inet = InetAddress.getLocalHost();
                } catch (UnknownHostException e) {
                    e.printStackTrace();
                }
                ip = inet.getHostAddress();
            }
        }
        // 对于通过多个代理的情况，第一个IP为客户端真实IP,多个IP按照','分割
        if (ip != null && ip.length() > 15) {
            if (ip.indexOf(",") > 0) {
                ip = ip.substring(0, ip.indexOf(","));
            }
        }
        System.err.println("客户端IP：" + ip);
        ip="192.168.56.1";//TODO
        return ip;
    }
    
}
