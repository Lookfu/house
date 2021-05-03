package com.example.house.mapper;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CommunityMapper {
    String getNameByCityId(int cityId);
}
