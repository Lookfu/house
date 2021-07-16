package com.example.house.service;

import com.example.house.service.model.HotHouseModel;
import com.example.house.service.model.HouseModel;

import java.util.List;

public interface HouseService {

    List<HouseModel> houseListOfAll(int limit,int offset);
    List<HouseModel> searchHouse(String cityName,String areaName);

    HouseModel houseDetail(long id);

    /**
     * 下架房子
     * @param id
     * @return
     */
    int unSellHouse(long id);
    /**
     * 获取热点房产
     * @param size 房产个数
     * @return
     */
    List<HotHouseModel> hotHouse(int size);

    /**
     * 添加房子，如果插入成功返回房子的ID，否则返回0
     * @param houseModel
     * @return
     */
    long addHouse(HouseModel houseModel);

    List<HouseModel> myHouse(long userId,int limit,int offset);
}
