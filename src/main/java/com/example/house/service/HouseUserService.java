package com.example.house.service;

import com.example.house.service.model.HouseModel;
import com.example.house.service.model.HouseUserModel;

import java.util.List;

public interface HouseUserService {

    int insertItem(HouseUserModel houseUserModel);

    List<HouseModel> likeHouses(long userId);
}
