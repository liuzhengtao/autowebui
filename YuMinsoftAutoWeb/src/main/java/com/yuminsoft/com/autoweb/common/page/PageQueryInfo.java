package com.yuminsoft.com.autoweb.common.page;

/**
 * 
 * FileName:    PageQueryInfo.java  
 * @author: YM10095
 * @version:    1.0  
 * @date:   	2017年7月29日 下午9:34:03
 */
public class PageQueryInfo {
    // 当前页数（第几页）
    public Integer pageNumber = 1;    
    // 每页显示条数
    public Integer pageSize = 10;
    
    public PageQueryInfo() {
        super();
    }

    /****
     * 
     * @param total         总记录数
     * @param pageNumber    当前页
     * @param pagesize      每页显示条数
     */
    public PageQueryInfo( Integer pageNumber,Integer totalCount, Integer pagesize) {
        //当前页
        this.pageNumber = pageNumber;        
        //每页显示条数
        this.pageSize = pagesize;        
    }
    
    public Integer getPageNumber() {
        return pageNumber;
    }
    
    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public void setPageNumber(Integer pageNumber) {
        this.pageNumber = pageNumber;
    }

}