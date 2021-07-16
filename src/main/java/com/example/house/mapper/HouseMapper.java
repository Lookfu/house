package com.example.house.mapper;

import com.example.house.entity.House;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface HouseMapper {
    /**
     *获取所有的房屋列表
     * @param limit
     * @param offset
     * @return
     */
    List<House> houseListOfAll(int limit,int offset);
    List<House> searchHouse(int cityId,int areaId);
    House houseDetail(long id);
    int setState(int state,long houseId);

    /**
     * 把数据插入数据库
     * @param house
     * @return
     */
    int insertItem(House house);

    List<House> myHouse(long userId,int limit,int offset);
}
