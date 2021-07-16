package com.example.house.mapper;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CommunityMapper {
    String getCommunityName(int cityId,int communityId);
    int getCommunityId(int cityId,String name);
}
