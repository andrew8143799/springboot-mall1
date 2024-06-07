package com.andrewyeh.springbootmall1.service;

import com.andrewyeh.springbootmall1.dto.CreateOrderRequest;
import com.andrewyeh.springbootmall1.dto.OrderQueryParam;
import com.andrewyeh.springbootmall1.model.Order;

import java.util.List;

public interface OrderService{

    Integer countOrder(OrderQueryParam orderQueryParam);

    List<Order> getOrderListByUserId(OrderQueryParam orderQueryParam);

    Integer createOrder(Integer userId, CreateOrderRequest createOrderRequest);

    Order getOrderById(Integer orderId);

}
