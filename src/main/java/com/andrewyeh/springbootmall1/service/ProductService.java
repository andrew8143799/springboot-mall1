package com.andrewyeh.springbootmall1.service;

import com.andrewyeh.springbootmall1.dto.ProductRequest;
import com.andrewyeh.springbootmall1.model.Product;

public interface ProductService {

    Product getProductById(Integer productId);

    Integer createProduct(ProductRequest productRequest);

    void updateProduct(Integer productId, ProductRequest productRequest);

    void deleteById(Integer productId);

}
