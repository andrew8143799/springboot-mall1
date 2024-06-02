package com.andrewyeh.springbootmall1.dao;

import com.andrewyeh.springbootmall1.dto.ProductRequest;
import com.andrewyeh.springbootmall1.model.Product;

public interface ProductDao {

    Product getProductById(Integer productId);

    Integer createProduct(ProductRequest productRequest);

    void updateProduct(Integer productId, ProductRequest productRequest);

    void deleteById(Integer productId);
}
