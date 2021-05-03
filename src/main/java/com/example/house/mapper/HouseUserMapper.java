package com.example.house.mapper;

import com.example.house.entity.HouseUser;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface HouseUserMapper {

    int insertItem(HouseUser houseUser);
    int countItem(long userId,long houseId,int type);
    List<HouseUser> selectLikeHouses(long userId);
}
