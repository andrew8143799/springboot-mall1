package com.andrewyeh.springbootmall1.service.impl;

import com.andrewyeh.springbootmall1.dao.OrderDao;
import com.andrewyeh.springbootmall1.dao.ProductDao;
import com.andrewyeh.springbootmall1.dto.CreateOrderRequest;
import com.andrewyeh.springbootmall1.dto.PurchaseItem;
import com.andrewyeh.springbootmall1.model.OrderItem;
import com.andrewyeh.springbootmall1.model.Product;
import com.andrewyeh.springbootmall1.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Component
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderDao orderDao;

    @Autowired
    ProductDao productDao;

    //在service方法內call兩個dao methods，需加@Tranactional註解，以確保操作全部完成後才正式更動資料庫
    @Transactional
    @Override
    public Integer createOrder(Integer userId, CreateOrderRequest createOrderRequest) {

        List<OrderItem> orderItemList = new ArrayList<>();
        //計算這一筆訂單的總花費資訊
        int totalAmount = 0;

        for(PurchaseItem purchaseItem : createOrderRequest.getPurchaseList()){
            Product product = productDao.getProductById(purchaseItem.getProductId());
            int amount = product.getPrice() * purchaseItem.getQuantity();
            totalAmount = totalAmount + amount;

            //轉換PurchaseItem到OrderItem
            OrderItem orderItem = new OrderItem();
            orderItem.setProductId(purchaseItem.getProductId());
            orderItem.setQuantity(purchaseItem.getQuantity());
            orderItem.setAmount(amount);

            orderItemList.add(orderItem);

        }
        //創建定單
        Integer orderId = orderDao.createOrder(userId, totalAmount);

//====================================================================================================================

        //在 order_item table 中也去創建這些數據出來
        orderDao.createOrderItems(orderId, orderItemList);

        return orderId;

    }
}
