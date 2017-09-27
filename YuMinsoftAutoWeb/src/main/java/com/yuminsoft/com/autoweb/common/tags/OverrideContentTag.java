package com.yuminsoft.com.autoweb.common.tags;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

/**
 * 
 * FileName:    OverrideBlockTag.java  
 * @author: YM10095
 * @version:    1.0  
 * @date:   	2017年7月29日 下午10:30:29
 */
public class OverrideContentTag extends TagSupport {
    private static final long serialVersionUID = 1L;

    @Override
    public int doEndTag() throws JspException {
        JspWriter out = pageContext.getOut();
        Object obj = this.pageContext.getRequest().getAttribute(this.getId());
        try {
            if (obj != null){
                out.write(obj.toString());
            }                
        } catch (IOException e) {
            e.printStackTrace();
        }
        return EVAL_PAGE;
    }

}