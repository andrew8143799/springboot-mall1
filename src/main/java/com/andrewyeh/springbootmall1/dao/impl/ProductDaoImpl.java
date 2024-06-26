package com.andrewyeh.springbootmall1.dao.impl;

import com.andrewyeh.springbootmall1.constant.ProductCategory;
import com.andrewyeh.springbootmall1.dao.ProductDao;
import com.andrewyeh.springbootmall1.dto.ProductQueryParam;
import com.andrewyeh.springbootmall1.dto.ProductRequest;
import com.andrewyeh.springbootmall1.model.Product;
import com.andrewyeh.springbootmall1.rowmapper.ProductRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class ProductDaoImpl implements ProductDao {

    @Override
    public Integer countProducts(ProductQueryParam productQueryParam) {
        String sql = "SELECT count(*) FROM product WHERE 1=1";

        Map<String, Object> map = new HashMap<>();

        //查詢條件
        sql = addFilteringSql(sql, map, productQueryParam);

        Integer total = namedParameterJdbcTemplate.queryForObject(sql, map, Integer.class);

        return total;

    }

    @Override
    public List<Product> getProducts(ProductQueryParam productQueryParam) {
        String sql ="SELECT product_id, product_name, category, image_url, price, stock, description," +
                " created_date, " + "last_modified_date " +
                "FROM product WHERE 1=1";

        Map<String, Object> map = new HashMap<>();

        //查詢條件
        sql = addFilteringSql(sql, map, productQueryParam);

        //已經有defaultValue了，所以不需要if判斷
        //排序
        sql += " ORDER BY " + productQueryParam.getOrderBy() + " " + productQueryParam.getSort();

        //分頁
        sql += " LIMIT :limit OFFSET :offset";
        map.put("limit", productQueryParam.getLimit());
        map.put("offset", productQueryParam.getOffset());

        List<Product> productList = namedParameterJdbcTemplate.query(sql, map, new ProductRowMapper());

        return productList;
    }

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Override
    public Product getProductById(Integer productId) {


        String sql = "SELECT product_id, product_name, category, image_url, price, stock, description," +
                " created_date, " + "last_modified_date " +
                "FROM product WHERE product_id = :productId ";

        Map<String, Object> map = new HashMap<>();
        map.put("productId", productId);

        List<Product> productList = namedParameterJdbcTemplate.query(sql, map, new ProductRowMapper());

        if(productList.size() > 0){
            return productList.get(0);
        }else {
            return null;
        }
    }

    @Override
    public Integer createProduct(ProductRequest productRequest) {

        String sql = "INSERT INTO product(product_name, category, image_url, price, stock, description, created_date, last_modified_date) " +
                "VALUES (:productName, :category, :imageUrl, :price, :stock, :description, :createdDate, :lastModifiedDate)";

        Map<String, Object> map = new HashMap<>();
        map.put("productName", productRequest.getProductName());
        map.put("category", productRequest.getCategory().toString());
        map.put("imageUrl", productRequest.getImageUrl());
        map.put("price", productRequest.getPrice());
        map.put("stock", productRequest.getStock());
        map.put("description", productRequest.getDescription());

        Date now = new Date();
        map.put("createdDate", now);
        map.put("lastModifiedDate", now);

        KeyHolder keyHolder = new GeneratedKeyHolder();

        namedParameterJdbcTemplate.update(sql, new MapSqlParameterSource(map), keyHolder);

        int productId = keyHolder.getKey().intValue();

        return productId;

    }

    @Override
    public void updateProduct(Integer productId, ProductRequest productRequest){

        String sql = "UPDATE product SET product_name = :productName, category = :category, image_url = :imageUrl, " +
                     "price = :price, stock = :stock, description = :description," +
                     "last_modified_date = :lastModifiedDate " +
                     "WHERE product_id = :productId";

        Map<String, Object> map = new HashMap<>();
        map.put("productId", productId);
        map.put("productName", productRequest.getProductName());
        map.put("category", productRequest.getCategory().toString());
        map.put("imageUrl", productRequest.getImageUrl());
        map.put("price", productRequest.getPrice());
        map.put("stock", productRequest.getStock());
        map.put("description", productRequest.getDescription());

        Date now = new Date();
        map.put("lastModifiedDate", now);

        namedParameterJdbcTemplate.update(sql, map);

    }

    @Override
    public void deleteById(Integer productId){

        String sql = "DELETE FROM product WHERE product_id = :productId";

        Map<String, Object> map = new HashMap<>();
        map.put("productId", productId);

        namedParameterJdbcTemplate.update(sql, map);
    }

    //提煉出重複使用的程式
    private String addFilteringSql(String sql, Map<String, Object> map, ProductQueryParam productQueryParam){

        if(productQueryParam.getCategory() != null){
            sql += " AND category = :category";
            map.put("category", productQueryParam.getCategory().name());
        }

        if(productQueryParam.getSearch() != null){
            sql += " AND product_name LIKE :search";
            map.put("search", "%" + productQueryParam.getSearch() + "%");
        }

        return sql;
    }

    @Override
    public void updateStock(Integer productId, Integer stockRemain) {
        String sql = "UPDATE product SET stock = :stockRemain, " +
                "last_modified_date = :lastModifiedDate " +
                "WHERE product_id = :productId;";

        Map<String, Object> map = new HashMap<>();
        map.put("stockRemain", stockRemain);
        map.put("productId", productId);
        map.put("lastModifiedDate", new Date());

        namedParameterJdbcTemplate.update(sql, map);
    }
}
