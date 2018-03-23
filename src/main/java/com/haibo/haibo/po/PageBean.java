package com.haibo.haibo.po;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2017/11/4/004.
 */
public class PageBean implements Serializable {
    private int maxPage;//一共多少页
    private int currentPage;//当前页码
    private long maxRow;//一共多少行

    private int rowPerPage;//每页多少行

    public List<News> getData() {
        return data;
    }

    public void setData(List<News> data) {
        this.data = data;
    }

    private List<News> data;//当前页的数据


    public int getMaxPage() {
        return maxPage;
    }

    public void setMaxPage(int maxPage) {
        this.maxPage = maxPage;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public long getMaxRow() {
        return maxRow;
    }

    public void setMaxRow(long maxRow) {
        this.maxRow = maxRow;
    }

    public int getRowPerPage() {
        return rowPerPage;
    }

    public void setRowPerPage(int rowPerPage) {
        this.rowPerPage = rowPerPage;
    }



}