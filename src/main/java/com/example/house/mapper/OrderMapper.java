package com.example.house.mapper;

import com.example.house.entity.Orders;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface OrderMapper {

    int insertItem(Orders orders);
    List<Orders> listOrders(long userId);
    
}
