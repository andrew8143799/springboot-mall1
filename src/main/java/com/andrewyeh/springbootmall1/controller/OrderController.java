package com.andrewyeh.springbootmall1.controller;

import com.andrewyeh.springbootmall1.dto.CreateOrderRequest;
import com.andrewyeh.springbootmall1.dto.PurchaseItem;
import com.andrewyeh.springbootmall1.model.Order;
import com.andrewyeh.springbootmall1.service.OrderService;
import com.andrewyeh.springbootmall1.service.impl.OrderServiceImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderController {

    @Autowired
    private OrderService orderService;

                //
                //先擁有userID才能使用創建訂單功能
    @PostMapping("/users/{userId}/orders")
    public ResponseEntity<?> createOrder(@PathVariable Integer userId,
                                         @RequestBody @Valid
                                         CreateOrderRequest createOrderRequest){

        Integer orderId = orderService.createOrder(userId ,createOrderRequest);

        Order order = orderService.getOrderById(orderId);

        return ResponseEntity.status(HttpStatus.CREATED).body(order);
    }



}
