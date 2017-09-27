package com.yuminsoft.com.autoweb.common.tags;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.BodyTagSupport;

/**
 * 
 * FileName:    BlockTag.java  
 * @author: YM10095
 * @version:    1.0  
 * @date:   	2017年7月29日 下午10:30:19
 */
public class ContentTag extends BodyTagSupport {
    private static final long serialVersionUID = 1L;
    //
    private String contentId;
    
    @Override
    public int doAfterBody() throws JspException {
        return SKIP_BODY;
    }

    @Override
    public int doStartTag() throws JspException {
        return EVAL_BODY_BUFFERED;
    }

    @Override
    public int doEndTag() throws JspException {
        String content = this.bodyContent.getString();
        try {
            this.pageContext.getRequest().setAttribute(this.getContentId(), content);
            this.bodyContent.clear();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return EVAL_PAGE;//default
    }

    public String getContentId() {
        return contentId;
    }

    public void setContentId(String contentId) {
        this.contentId = contentId;
    }
    
}