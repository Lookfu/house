package com.example.house.service;

import com.example.house.service.model.HotHouseModel;
import com.example.house.service.model.HouseModel;

import java.util.List;

public interface HouseService {

    List<HouseModel> houseListOfAll(int pageSize,int pageNumber);
    List<HouseModel> houseListOfRenting(int pageSize,int pageNumber);
    List<HouseModel> houseListOfSelling(int pageSize,int pageNumber);
    List<HouseModel> houseListOfSelves(int pageSize,int pageNumber);

    HouseModel houseDetail(int id);

    /**
     * 获取热点房产
     * @param size 房产个数
     * @return
     */
    List<HotHouseModel> hotHouse(int size);
}
