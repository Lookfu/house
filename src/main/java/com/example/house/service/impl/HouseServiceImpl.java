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
                    communityMapper.getCommunityName(house.getCityId(),house.getCommunityId())));
        }
        return modelList;
    }

    @Override
    public List<HouseModel> searchHouse(String cityName, String areaName) {
        int cityId=cityMapper.getIdByName(cityName);
        int areaId=communityMapper.getCommunityId(cityId,areaName);
        List<House> list=houseMapper.searchHouse(cityId,areaId);
        System.out.println(cityId+" "+areaId);
        List<HouseModel> res=new ArrayList<>(list.size());
        for(House house:list){
            HouseModel houseModel=HouseConvert.houseModelCFhouse(house,cityName,areaName);
            res.add(houseModel);
        }
        return res;
    }

    @Override
    public int unSellHouse(long id) {
        return houseMapper.setState(2,id);
    }

    @Override
    public HouseModel houseDetail(long id) {
        House house=houseMapper.houseDetail(id);
        if(house==null){
            return  null;
        }
        return HouseConvert.houseModelCFhouse(house,cityMapper.getNameById(house.getCityId()),
                communityMapper.getCommunityName(house.getCityId(),house.getCommunityId()));
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

    @Override
    public long addHouse(HouseModel houseModel) {
        int cityId= cityMapper.getIdByName(houseModel.getCityName());
        House house=HouseConvert.houseCFHouseModel(houseModel,cityId,
                communityMapper.getCommunityId(cityId,houseModel.getCommunityName()));
        int res=houseMapper.insertItem(house);
        if(res==0){
            return 0;
        }else {
            return house.getId();
        }
    }

    @Override
    public List<HouseModel> myHouse(long userId,int limit,int offset) {
        List<House> list= houseMapper.myHouse(userId,limit,offset);
        List<HouseModel> res=new ArrayList<>(list.size());
        for(House e:list){
            res.add(HouseConvert.houseModelCFhouse(e,cityMapper.getNameById(e.getCityId()),
                    communityMapper.getCommunityName(e.getCityId(),e.getCommunityId())));
        }
        return res;
    }
}
