package com.andrewyeh.springbootmall1.service.impl;

import com.andrewyeh.springbootmall1.dao.OrderDao;
import com.andrewyeh.springbootmall1.dao.ProductDao;
import com.andrewyeh.springbootmall1.dao.UserDao;
import com.andrewyeh.springbootmall1.dto.CreateOrderRequest;
import com.andrewyeh.springbootmall1.dto.OrderQueryParam;
import com.andrewyeh.springbootmall1.dto.PurchaseItem;
import com.andrewyeh.springbootmall1.model.Order;
import com.andrewyeh.springbootmall1.model.OrderItem;
import com.andrewyeh.springbootmall1.model.Product;
import com.andrewyeh.springbootmall1.model.User;
import com.andrewyeh.springbootmall1.service.OrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@Component
public class OrderServiceImpl implements OrderService {

    private final static Logger log = LoggerFactory.getLogger(OrderServiceImpl.class);

    @Autowired
    private OrderDao orderDao;

    @Autowired
    ProductDao productDao;

    @Autowired
    UserDao userDao;

    @Override

    public Integer countOrder(OrderQueryParam orderQueryParam){

        Integer count = orderDao.countOrder(orderQueryParam);

        return count;
    }

    public List<Order> getOrderListByUserId(OrderQueryParam orderQueryParam){

        List<Order> orderList = orderDao.getOrderListByUserId(orderQueryParam);

        for(Order order : orderList){

            List<OrderItem> orderItemList = orderDao.getOrderItemsByOrderId(order.getOrderId());

            order.setOrderItemList(orderItemList);

        }

        return orderList;

    }

    public Order getOrderById(Integer orderId){

        Order order = orderDao.getOrderById(orderId);

        List<OrderItem> orderItemList = orderDao.getOrderItemsByOrderId(orderId);

        order.setOrderItemList(orderItemList);

        return order;

    }


    //在service方法內call兩個dao methods，需加@Tranactional註解，以確保操作全部完成後才正式更動資料庫
    @Transactional
    @Override
    public Integer createOrder(Integer userId, CreateOrderRequest createOrderRequest) {

        User user = new User();
        user = userDao.getUserById(userId);

        if(user == null){
            log.warn("用戶 {} 不存在", userId);
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }

        List<OrderItem> orderItemList = new ArrayList<>();
        //計算這一筆訂單的總花費資訊
        int totalAmount = 0;


        for(PurchaseItem purchaseItem : createOrderRequest.getPurchaseList()){
            Product product = productDao.getProductById(purchaseItem.getProductId());

            if(product == null){
                log.warn("商品 {} 不存在", purchaseItem.getProductId());
            } else if (product.getStock() < purchaseItem.getQuantity()) {

                log.warn("商品 {} 庫存不足，欲購買數量: {}", purchaseItem.getProductId(), purchaseItem.getQuantity());
                throw new ResponseStatusException((HttpStatus.BAD_REQUEST));
            }else {

                Integer stockRemain = product.getStock() - purchaseItem.getQuantity();

                productDao.updateStock( purchaseItem.getProductId(), stockRemain);
            }

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
