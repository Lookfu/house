package com.example.house.mapper;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CityMapper {
    String getNameById(int Id);
    int getIdByName(String name);
}
