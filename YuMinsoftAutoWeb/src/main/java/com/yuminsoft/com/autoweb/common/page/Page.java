package com.yuminsoft.com.autoweb.common.page;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * FileName:    Page.java  
 * @author: YM10095
 * @version:    1.0  
 * @date:   	2017年7月29日 下午9:33:57
 */
public class Page<T> {
    // 当前页数（第几页）
    public Integer pageNumber;
    // 总记录数
    public Integer totalCount = 0;
    // 每页显示条数
    public Integer pageSize;
    // 总页数
    public Integer pages = 0;
    //查询结果列表
    private List<T> results;
    
    /**
     * 
     * @param pageNumber
     * @param totalCount
     * @param pageSize
     * @author: YM10095
     * @date:	2017年6月12日 上午9:05:15
     */
    public Page(Integer pageNumber, Integer totalCount, Integer pageSize) {
        this(pageNumber, totalCount, pageSize, new ArrayList(0));
    }

    public Page(Integer pageNumber, Integer totalCount, Integer pageSize, List<T> results) {
        super();
        this.pageNumber = pageNumber;
        this.totalCount = totalCount;
        this.pageSize = pageSize;
        
        //总页数
        if ((totalCount % pageSize) == 0) {
            this.pages = totalCount / pageSize;
        } else {
            this.pages = totalCount / pageSize + 1;
        }
        
        setResults(results);
    }
    
    public Integer getPageNumber() {
        return pageNumber;
    }
    public void setPageNumber(Integer pageNumber) {
        this.pageNumber = pageNumber;
    }
    public Integer getPageSize() {
        return pageSize;
    }
    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }
    public Integer getPages() {
        return pages;
    }
    public void setPages(Integer pages) {
        this.pages = pages;
    }
    public List<T> getResults() {
        return results;
    }
    public void setResults(List<T> results) {
        if (results == null){
            throw new IllegalArgumentException("results can not be null"); 
        }
        this.results = results;
    }
    public Integer getTotalCount() {
        return totalCount;
    }
    public void setTotalCount(Integer totalCount) {
        this.totalCount = totalCount;
    }
    
    public Integer getFirstRecord() {
        if (pageSize <= 0) {
            throw new IllegalArgumentException("pageSize must larger than 0");
        }
        return (pageNumber - 1) * pageSize;
    }
    
}
