package com.example.house.service.impl;

import com.example.house.entity.Orders;
import com.example.house.mapper.OrderMapper;
import com.example.house.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class OrderServiceImpl implements OrderService {
    @Autowired(required = false)
    private OrderMapper orderMapper;

    @Override
    public int insertItem(Orders orders) {
        return orderMapper.insertItem(orders);
    }

    @Override
    public List<Orders> listOrders(long userId) {
        return orderMapper.listOrders(userId);
    }
}
