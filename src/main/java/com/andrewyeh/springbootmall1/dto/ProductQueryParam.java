package com.andrewyeh.springbootmall1.dto;

import com.andrewyeh.springbootmall1.constant.ProductCategory;

public class ProductQueryParam {

    private ProductCategory productCategory;
    private String search;
    private String orderBy;
    private String sort;

    public ProductCategory getCategory() {
        return productCategory;
    }

    public void setCategory(ProductCategory productCategory) {
        this.productCategory = productCategory;
    }

    public String getSearch() {
        return search;
    }

    public void setSearch(String search) {
        this.search = search;
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

    public String getOrderBy() {
        return orderBy;
    }

    public void setOrderBy(String orderBy) {
        this.orderBy = orderBy;
    }
}
