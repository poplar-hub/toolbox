package com.redDabbler.template.tools.model;


import java.util.List;

/**
 * @author RedDabbler
 * @create 2019-01-31 10:59
 **/
public class Page<T> {
    // 默认从1开始表示第一页
    private int pageNo = 1;
    private int pageSize =20;
    private int totalSize;
    private int totalPages;
    private List<T> data;


    public Page() {

    }

    public Page(int pageNo, int pageSize) {
        if(pageSize<=0){
            throw new IllegalArgumentException("pageSize必须大于0");
        }
        if(pageNo<0){
            throw new IllegalArgumentException("pageNo不合法");
        }
        this.pageNo = pageNo;
        this.pageSize = pageSize;
    }

    public Page(int pageNo, int pageSize, int totalSize) {
        this(pageNo,pageSize);
        this.totalSize = totalSize;
        setTotalPages();
    }

    public int getPageNo() {
        return pageNo;
    }

    public int getTotalSize() {
        return totalSize;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalSize(final int totalSize) {
        this.totalSize = totalSize;
    }

    private void setTotalPages() {
        if (totalSize % pageSize > 0) {
            this.totalPages = totalSize / pageSize + 1;
        } else {
            this.totalPages =  totalSize / pageSize;
        }
    }

    public void setData(List<T> data){
        int size = data.size();
        setTotalSize(size);
        this.data = data;
    }

    public List<T> getData() {
        return data;
    }
}
