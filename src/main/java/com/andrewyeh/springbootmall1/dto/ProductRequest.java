package com.andrewyeh.springbootmall1.dto;

import com.andrewyeh.springbootmall1.constant.ProductCategory;
import jakarta.validation.constraints.NotNull;

//設定前端傳來的參數
public class ProductRequest {

    @NotNull
    private String productName;

    @NotNull
    private ProductCategory category;

    @NotNull//Enum
    private String imageUrl;

    @NotNull
    private Integer price;

    @NotNull
    private Integer stock;

    private String description;

// =============================================================

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public ProductCategory getCategory() {
        return category;
    }

    public void setCategory(ProductCategory category) {
        this.category = category;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
