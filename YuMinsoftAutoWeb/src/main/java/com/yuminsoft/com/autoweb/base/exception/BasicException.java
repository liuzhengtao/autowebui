package com.yuminsoft.com.autoweb.base.exception;

/**
 * 
 * FileName:    BaseException.java  
 * @author: YM10095
 * @version:    1.0  
 * @date:   	2017年7月29日 下午9:53:21
 */
public class BasicException extends RuntimeException {
    private static final long serialVersionUID = 1L;
    
    //错误标
    private Integer errorFlag;
    
    /**
     * 
     * @param message
     * @author: YM10095
     * @date:	2017年6月16日 上午9:23:39
     */
    public BasicException(String message) {
        super(message);        
    }
    
    /**
     * 
     * @param message
     * @param cause
     * @author: YM10095
     * @date:	2017年6月16日 上午9:23:35
     */
    public BasicException(String message, Throwable cause) {
        super(message, cause);
    }
    
    /**
     * 
     * @param errorCode
     * @param message
     * @author: YM10095
     * @date:	2017年6月16日 上午9:24:51
     */
    public BasicException(Integer errorFlag, String message) {
        super(message);
        this.setErrorFlag(errorFlag);
    }

    public Integer getErrorFlag() {
        return errorFlag;
    }

    public void setErrorFlag(Integer errorFlag) {
        this.errorFlag = errorFlag;
    }
        
}
