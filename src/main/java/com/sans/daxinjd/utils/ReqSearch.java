package com.sans.daxinjd.utils;

public class ReqSearch {

    private String query;
    private int cid;
    private int pageNum = 1;
    private int pageSize = 20;

    @Override
    public String toString() {
        return "ReqSearch{" +
                "query='" + query + '\'' +
                ", cid='" + cid + '\'' +
                ", pageNum='" + pageNum + '\'' +
                ", pageSize='" + pageSize + '\'' +
                '}';
    }

    public String getQuery() {
        return query;
    }

    public ReqSearch setQuery(String query) {
        this.query = query;
        return this;
    }

    public int getCid() {
        return cid;
    }

    public ReqSearch setCid(int cid) {
        this.cid = cid;
        return this;
    }

    public int getPageNum() {
        return pageNum;
    }

    public ReqSearch setPageNum(int pageNum) {
        this.pageNum = pageNum;
        return this;
    }

    public int getPageSize() {
        return pageSize;
    }

    public ReqSearch setPageSize(int pageSize) {
        this.pageSize = pageSize;
        return this;
    }
}
