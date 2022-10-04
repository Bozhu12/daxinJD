package com.sans.daxinjd.model.dto;

/**
 *
 * @author Sans
 */
public class SearchRequest {

    private String query;
    private int cid;
    private int pageNum = 1;
    private int pageSize = 20;

    @Override
    public String toString() {
        return "SearchRequest{" +
                "query='" + query + '\'' +
                ", cid='" + cid + '\'' +
                ", pageNum='" + pageNum + '\'' +
                ", pageSize='" + pageSize + '\'' +
                '}';
    }

    public String getQuery() {
        return query;
    }

    public SearchRequest setQuery(String query) {
        this.query = query;
        return this;
    }

    public int getCid() {
        return cid;
    }

    public SearchRequest setCid(int cid) {
        this.cid = cid;
        return this;
    }

    public int getPageNum() {
        return pageNum;
    }

    public SearchRequest setPageNum(int pageNum) {
        this.pageNum = pageNum;
        return this;
    }

    public int getPageSize() {
        return pageSize;
    }

    public SearchRequest setPageSize(int pageSize) {
        this.pageSize = pageSize;
        return this;
    }
}
