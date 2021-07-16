package com.example.house.utils.covert;

import com.example.house.controller.viewobject.OrdersVO;
import com.example.house.entity.Orders;
import org.springframework.beans.BeanUtils;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class OrdersConvert {

    public static OrdersVO ordersVOCFOrders(Orders orders){
        OrdersVO ordersVO=new OrdersVO();
        BeanUtils.copyProperties(orders,ordersVO);
        if(orders.getConfirmed()==0){
            ordersVO.setConfirmed("否");
        }else{
            ordersVO.setConfirmed("是");
        }
        DateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        try {
            Timestamp timestamp=new Timestamp(orders.getCreatedTime().getTime()-28800000);
            String time = sdf.format(timestamp);
            ordersVO.setCreatedTime(time);
        } catch (Exception e) {
            e.printStackTrace();
        }
       return ordersVO;
    }
}
