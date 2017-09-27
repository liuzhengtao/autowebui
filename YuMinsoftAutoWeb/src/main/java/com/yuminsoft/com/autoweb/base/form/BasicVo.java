package com.yuminsoft.com.autoweb.base.form;

import com.yuminsoft.com.autoweb.common.page.PageQueryInfo;

/**
 * 
 * Filename:    BaseVo.java  
 * Description: 
 * Company:     tutu.com
 * @author: YM10095
 * Create at:   2017年6月9日 上午9:48:37
 */
public class BasicVo extends PageQueryInfo {
    //
    String jsonstring;

    public String getJsonstring() {
        return jsonstring;
    }

    public void setJsonstring(String jsonstring) {
        this.jsonstring = jsonstring;
    }
    
}
