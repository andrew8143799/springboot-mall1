package com.andrewyeh.springbootmall1.rowmapper;

import com.andrewyeh.springbootmall1.model.Order;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class OrderRowMapper implements RowMapper<Order> {


    @Override
    public Order mapRow(ResultSet rs, int rowNum) throws SQLException {
        Order order = new Order();
        order.setOrderId(rs.getInt("order_Id"));
        order.setUserId(rs.getInt("user_Id"));
        order.setTotalAmount(rs.getInt("total_amount"));
        order.setCreatedDate(rs.getDate("created_date"));
        order.setLastModifiedDate(rs.getDate("last_modified_date"));

        return order;

    }
}
