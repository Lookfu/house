package com.example.house.utils;

public class PageParam {
    //默认页长度
    private static final int DEFAULT_PAGE_SIZE=10;
    //默认页号
    private static final int DEFAULT_PAGE_NUMBER=1;
    private int offset;
    private int limit;
    public PageParam(){
        this(DEFAULT_PAGE_SIZE,DEFAULT_PAGE_NUMBER);
    }

    public PageParam(int pageSize,int pageNumber){
        offset=pageSize*(pageNumber-1);
        limit=pageSize;
    }
    public PageParam(int pageNumber){
        this(DEFAULT_PAGE_SIZE,pageNumber);
    }
    public int getOffset() {
        return offset;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }
}
