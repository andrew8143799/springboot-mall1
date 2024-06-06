package com.andrewyeh.springbootmall1.dao;

import com.andrewyeh.springbootmall1.dto.CreateOrderRequest;
import com.andrewyeh.springbootmall1.model.OrderItem;

import java.util.List;

public interface OrderDao {

    Integer createOrder(Integer userId, Integer Amount);

    void createOrderItems(Integer orderId, List<OrderItem> orderItemList);

}
