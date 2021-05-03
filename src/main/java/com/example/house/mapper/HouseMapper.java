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
    //获取所有已上架的房屋列表
    List<House> houseListOfSelves(int limit,int offset);
    //获取正在出售的房屋列表（未下架）
    List<House> houseListOfSelling(int limit,int offset);
    //获取正在出租的房屋列表（未下架）
    List<House> houseListOfRenting(int limit,int offset);

    House houseDetail(long id);

}
