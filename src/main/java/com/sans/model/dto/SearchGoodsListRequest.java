package com.sans.model.dto;

import java.io.Serializable;

/**
 *
 * @author Sans
 */
public class SearchGoodsListRequest implements Serializable {

    private String query;
    private int cid = -1;
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

    public SearchGoodsListRequest setQuery(String query) {
        this.query = query;
        return this;
    }

    public int getCid() {
        return cid;
    }

    public SearchGoodsListRequest setCid(int cid) {
        this.cid = cid;
        return this;
    }

    public int getPageNum() {
        return pageNum;
    }

    public SearchGoodsListRequest setPageNum(int pageNum) {
        this.pageNum = pageNum;
        return this;
    }

    public int getPageSize() {
        return pageSize;
    }

    public SearchGoodsListRequest setPageSize(int pageSize) {
        this.pageSize = pageSize;
        return this;
    }
}
