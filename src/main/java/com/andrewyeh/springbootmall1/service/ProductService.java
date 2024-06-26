package com.andrewyeh.springbootmall1.service;

import com.andrewyeh.springbootmall1.constant.ProductCategory;
import com.andrewyeh.springbootmall1.dto.ProductQueryParam;
import com.andrewyeh.springbootmall1.dto.ProductRequest;
import com.andrewyeh.springbootmall1.model.Product;

import java.util.List;

public interface ProductService {

    List<Product> getProducts(ProductQueryParam productQueryParam);

    Integer countProducts(ProductQueryParam productQueryParam);

    Product getProductById(Integer productId);

    Integer createProduct(ProductRequest productRequest);

    void updateProduct(Integer productId, ProductRequest productRequest);

    void deleteById(Integer productId);

}
