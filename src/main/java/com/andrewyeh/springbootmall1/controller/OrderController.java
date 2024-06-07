package com.andrewyeh.springbootmall1.controller;

import com.andrewyeh.springbootmall1.dto.CreateOrderRequest;
import com.andrewyeh.springbootmall1.dto.OrderQueryParam;
import com.andrewyeh.springbootmall1.dto.PurchaseItem;
import com.andrewyeh.springbootmall1.model.Order;
import com.andrewyeh.springbootmall1.service.OrderService;
import com.andrewyeh.springbootmall1.service.impl.OrderServiceImpl;
import com.andrewyeh.springbootmall1.util.Page;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class OrderController {

    @Autowired
    private OrderService orderService;


    @GetMapping("users/{userId}/orders")
    public ResponseEntity<Page<Order>> getOrders(@PathVariable Integer userId,
                                                 @RequestParam(defaultValue = "10") @Max(1000) @Min(0) Integer limit,
                                                 @RequestParam(defaultValue = "0") @Min(0) Integer offset){


        OrderQueryParam orderQueryParam = new OrderQueryParam();
        orderQueryParam.setLimit(limit);
        orderQueryParam.setOffset(offset);
        orderQueryParam.setUserId(userId);

        //取得Order List

        List<Order> orderList = orderService.getOrderListByUserId(orderQueryParam);


        //取的 Order 總數

        Integer count = orderService.countOrder(orderQueryParam);

        //分頁
        Page page = new Page();
        page.setLimit(limit);
        page.setOffset(offset);
        page.setTotal(count);
        page.setResults(orderList);

        return ResponseEntity.status(HttpStatus.OK).body(page);
    }

                //
                //先擁有userID才能使用創建訂單功能
    @PostMapping("/users/{userId}/orders")
    public ResponseEntity<Order> createOrder(@PathVariable Integer userId,
                                         @RequestBody @Valid
                                         CreateOrderRequest createOrderRequest){

        Integer orderId = orderService.createOrder(userId ,createOrderRequest);

        Order order = orderService.getOrderById(orderId);

        return ResponseEntity.status(HttpStatus.CREATED).body(order);
    }
}
