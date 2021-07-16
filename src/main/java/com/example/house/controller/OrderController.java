package com.example.house.controller;

import com.example.house.constants.SessionConstants;
import com.example.house.controller.viewobject.OrdersVO;
import com.example.house.entity.Orders;
import com.example.house.response.CommonReturnType;
import com.example.house.service.OrderService;
import com.example.house.service.model.UserModel;
import com.example.house.utils.covert.OrdersConvert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/order")
public class OrderController {
    @Autowired
    OrderService orderService;

    @GetMapping("/list")
    public CommonReturnType listOrder(HttpServletRequest request){
        UserModel userModel=(UserModel)request.getSession().getAttribute(SessionConstants.USER);
        List<Orders> list=orderService.listOrders(userModel.getId());
        List<OrdersVO> res=new ArrayList<>(list.size());
        for(Orders e:list){
            OrdersVO ordersVO = OrdersConvert.ordersVOCFOrders(e);
            res.add(ordersVO);
        }
        return CommonReturnType.create(res);
    }
}
