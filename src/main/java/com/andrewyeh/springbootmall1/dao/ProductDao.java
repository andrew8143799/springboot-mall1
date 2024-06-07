package com.andrewyeh.springbootmall1.dao;

import com.andrewyeh.springbootmall1.constant.ProductCategory;
import com.andrewyeh.springbootmall1.dto.ProductQueryParam;
import com.andrewyeh.springbootmall1.dto.ProductRequest;
import com.andrewyeh.springbootmall1.model.Product;

import java.util.List;

public interface ProductDao {

    Integer countProducts(ProductQueryParam productQueryParam);

    List<Product> getProducts(ProductQueryParam productQueryParam);

    Product getProductById(Integer productId);

    Integer createProduct(ProductRequest productRequest);

    void updateProduct(Integer productId, ProductRequest productRequest);

    void updateStock(Integer productId, Integer stockRemain);

    void deleteById(Integer productId);
}
