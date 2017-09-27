package com.yuminsoft.com.autoweb.common.interceptor;

import java.lang.reflect.Method;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yuminsoft.com.autoweb.common.util.DataUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

/**
 * 
 * FileName:    AccessInfoInterceptor.java  
 * @author: YM10095
 * @version:    1.0  
 * @date:   	2017年7月29日 下午10:17:19
 */
@Component
public class AccessInfoInterceptor extends HandlerInterceptorAdapter {
    private Logger logger = LoggerFactory.getLogger(AccessInfoInterceptor.class);
    
    @Value("${spring.mvc.view.prefix:null}")
    private String viewprefix;
    @Value("${spring.mvc.view.suffix:null}")
    private String viewpsuffix;
    
    private long starttimes = 0;
    private long endtimes = 0;
    
    /**
     * 在请求处理之前进行调用（Controller方法调用之前）
     * @param request
     * @param response
     * @param handler
     * @return
     * @throws Exception
     * @author: YM10095
     * @date:	2017年6月22日 下午3:53:48
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response,Object handler) throws Exception {
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        Method method = handlerMethod.getMethod();
        Class<?> clazz = method.getDeclaringClass();   
        //
        String className = clazz.getName();
        //访问类名
        //String simpleClassName = clazz.getSimpleName();
        //访问路径
        String path = request.getServletPath();
        //访问方法
        String methodStr = method.getName();
        //
        logger.info("REQUEST : path=[" + path + "] method=[" + methodStr + "] contorller=[" + className + "]");
        
        //执行开始时间
        starttimes = System.currentTimeMillis();
        
        //只有返回true才会继续向下执行，返回false取消当前请求
        return true;
    }
    
    /**
     * 请求处理之后进行调用，但是在视图被渲染之前（Controller方法调用之后）
     * @param request
     * @param response
     * @param handler
     * @param modelAndView
     * @throws Exception
     * @author: YM10095
     * @date:	2017年6月22日 下午3:54:05
     */
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        //执行结束时间
        endtimes = System.currentTimeMillis();
        long costtimes = endtimes - starttimes;
        String costtimesStr = DataUtil.formatNumber(costtimes, ",###");
        logger.info("using times : " + costtimesStr + " ms");
        
        //上下文路径
        String contextPath = request.getContextPath();
        //获取controller返回的值
        if(modelAndView != null){
            String viewname = modelAndView.getViewName();
            if(viewname.startsWith("redirect:")){
                viewname = viewname.replace("redirect:", "");
                logger.info("redirect to path : " + viewname);
            }else{
                logger.info("redirect to page : " + contextPath + viewprefix + viewname + viewpsuffix);
            }
        }
    }
}