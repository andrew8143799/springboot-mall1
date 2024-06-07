package com.andrewyeh.springbootmall1.dao;

import com.andrewyeh.springbootmall1.dto.OrderQueryParam;
import com.andrewyeh.springbootmall1.model.Order;
import com.andrewyeh.springbootmall1.model.OrderItem;

import java.util.List;

public interface OrderDao {

    Integer countOrder(OrderQueryParam orderQueryParam);

    List<Order> getOrderListByUserId(OrderQueryParam orderQueryParam);

    Integer createOrder(Integer userId, Integer Amount);

    void createOrderItems(Integer orderId, List<OrderItem> orderItemList);

    Order getOrderById(Integer orderId);

    List<OrderItem> getOrderItemsByOrderId(Integer orderId);
}
