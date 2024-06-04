package com.andrewyeh.springbootmall1.rowmapper;

import com.andrewyeh.springbootmall1.constant.ProductCategory;
import com.andrewyeh.springbootmall1.model.Product;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

//將mysql資料轉為java object回傳
public class ProductRowMapper implements RowMapper<Product> {

    @Override
    public Product mapRow(ResultSet resultSet, int i) throws SQLException {
        Product product = new Product();

        product.setProductId(resultSet.getInt("product_id"));
        product.setProductName(resultSet.getString("product_name"));

          //enum取得
//        String categoryStr = (resultSet.getString("category"));
//        ProductCategory category = ProductCategory.valueOf(categoryStr);
//        product.setCategory(category);

        product.setCategory(ProductCategory.valueOf(resultSet.getString("category")));


        product.setImageUrl(resultSet.getString("image_url"));
        product.setPrice(resultSet.getInt("price"));
        product.setStock(resultSet.getInt("stock"));
        product.setDescription(resultSet.getString("description"));
        product.setCreateDate(resultSet.getDate("created_date"));
        product.setLastModifiedDate(resultSet.getDate("last_modified_date"));

        return product;
    }
}
