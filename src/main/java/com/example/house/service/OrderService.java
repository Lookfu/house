package com.example.house.service;

import com.example.house.entity.Orders;

import java.util.List;

public interface OrderService {

    int insertItem(Orders orders);
    List<Orders> listOrders(long userId);
}
