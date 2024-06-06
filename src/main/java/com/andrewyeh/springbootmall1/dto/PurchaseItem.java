package com.andrewyeh.springbootmall1.dto;

import jakarta.validation.constraints.NotNull;

//購買item的數據，json內層
public class PurchaseItem {

    @NotNull
    private Integer productId;
    @NotNull
    private Integer quantity;

    public @NotNull Integer getProductId() {
        return productId;
    }

    public void setProductId(@NotNull Integer productId) {
        this.productId = productId;
    }

    public @NotNull Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(@NotNull Integer quantity) {
        this.quantity = quantity;
    }
}
