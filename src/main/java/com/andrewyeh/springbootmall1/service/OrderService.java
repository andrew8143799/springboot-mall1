package com.andrewyeh.springbootmall1.service;

import com.andrewyeh.springbootmall1.dto.CreateOrderRequest;
import com.andrewyeh.springbootmall1.model.Order;

public interface OrderService{

    Integer createOrder(Integer userId, CreateOrderRequest createOrderRequest);

    Order getOrderById(Integer orderId);
}
