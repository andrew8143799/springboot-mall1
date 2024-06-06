package com.andrewyeh.springbootmall1.dto;

import jakarta.validation.constraints.NotEmpty;
import org.springframework.stereotype.Component;

import java.util.List;

//json最外層
public class CreateOrderRequest {

    //提供給集合類的註解
    @NotEmpty
    private List<PurchaseItem> purchaseList;

    public List<PurchaseItem> getPurchaseList() {
        return purchaseList;
    }

    public void setPurchaseList(List<PurchaseItem> purchaseList) {
        this.purchaseList = purchaseList;
    }
}
