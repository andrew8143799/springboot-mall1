package com.andrewyeh.springbootmall1.dto;

public class OrderQueryParam {

    private Integer Limit;
    private Integer offset;
    private Integer userId;

    public Integer getLimit() {
        return Limit;
    }

    public void setLimit(Integer limit) {
        Limit = limit;
    }

    public Integer getOffset() {
        return offset;
    }

    public void setOffset(Integer offset) {
        this.offset = offset;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
}
