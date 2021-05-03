package com.example.house.service.impl;

import com.example.house.constants.RedisConstants;
import com.example.house.entity.House;
import com.example.house.mapper.CityMapper;
import com.example.house.mapper.CommunityMapper;
import com.example.house.mapper.HouseMapper;
import com.example.house.service.HouseService;
import com.example.house.service.RedisService;
import com.example.house.service.model.HotHouseModel;
import com.example.house.service.model.HouseModel;
import com.example.house.utils.covert.HouseConvert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static com.example.house.utils.covert.HouseConvert.hotHouseModelCFhouseModel;

@Service
public class HouseServiceImpl implements HouseService {
    @Autowired(required = false)
    HouseMapper houseMapper;
    @Autowired(required = false)
    CityMapper cityMapper;
    @Autowired(required = false)
    CommunityMapper communityMapper;
    @Autowired
    RedisService redisService;

    @Override
    public List<HouseModel> houseListOfAll(int limit, int offset) {
        List<House> list=houseMapper.houseListOfAll(limit,offset);
        List<HouseModel> modelList=new ArrayList<>(list.size());
        for(House house:list){
            modelList.add(HouseConvert.houseModelCFhouse(house,cityMapper.getNameById(house.getCityId()),
                    communityMapper.getNameByCityId(house.getCommunityId())));
        }
        return modelList;
    }

    @Override
    public List<HouseModel> houseListOfRenting(int limit, int offset) {
        return null;
    }

    @Override
    public List<HouseModel> houseListOfSelling(int limit, int offset) {
        return null;
    }

    @Override
    public List<HouseModel> houseListOfSelves(int limit, int offset) {
        return null;
    }

    @Override
    public HouseModel houseDetail(int id) {
        House house=houseMapper.houseDetail(id);
        if(house==null){
            return  null;
        }
        return HouseConvert.houseModelCFhouse(house,cityMapper.getNameById(house.getCityId()),
                communityMapper.getNameByCityId(house.getCommunityId()));
    }

    @Override
    public List<HotHouseModel> hotHouse(int size) {
        Map<Integer,Integer> map=redisService.getZsetMap(RedisConstants.HOT_HOUSE,0,size-1);
        List<HotHouseModel> res=new ArrayList<>(map.size());
        for(Map.Entry<Integer,Integer> entry:map.entrySet()){
            HouseModel houseModel=houseDetail(entry.getKey());
            HotHouseModel elem=hotHouseModelCFhouseModel(houseModel,entry.getValue());
            res.add(elem);
        }
        return res;
    }
}
