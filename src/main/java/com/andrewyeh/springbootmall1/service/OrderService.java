package com.andrewyeh.springbootmall1.service;

import com.andrewyeh.springbootmall1.dto.CreateOrderRequest;

public interface OrderService{

    Integer createOrder(Integer userId, CreateOrderRequest createOrderRequest);
}
