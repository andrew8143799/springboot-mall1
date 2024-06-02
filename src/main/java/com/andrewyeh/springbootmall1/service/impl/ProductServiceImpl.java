package com.andrewyeh.springbootmall1.service.impl;

import com.andrewyeh.springbootmall1.dao.ProductDao;
import com.andrewyeh.springbootmall1.dto.ProductRequest;
import com.andrewyeh.springbootmall1.model.Product;
import com.andrewyeh.springbootmall1.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductDao productDao;

    @Override
    public Product getProductById(Integer productId) {

        return productDao.getProductById(productId);
    }

    @Override
    public Integer createProduct(ProductRequest productRequest) {

        return productDao.createProduct(productRequest);
    }
}
