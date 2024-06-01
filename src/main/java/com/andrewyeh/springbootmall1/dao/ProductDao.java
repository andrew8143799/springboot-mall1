package com.andrewyeh.springbootmall1.dao;

import com.andrewyeh.springbootmall1.model.Product;

public interface ProductDao {

    Product getProductById(Integer productId);
}
