package com.example.house.service.impl;

import com.example.house.entity.HouseUser;
import com.example.house.mapper.HouseMapper;
import com.example.house.mapper.HouseUserMapper;
import com.example.house.service.HouseUserService;
import com.example.house.service.model.HouseModel;
import com.example.house.service.model.HouseUserModel;
import com.example.house.utils.covert.HouseConvert;
import com.example.house.utils.covert.HouseUserConvert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class HouseUserServiceImpl implements HouseUserService {
    @Autowired(required = false)
    HouseUserMapper houseUserMapper;
    @Autowired(required = false)
    HouseMapper houseMapper;
    @Override
    public int insertItem(HouseUserModel houseUserModel) {
        houseUserModel.setType(2);
        //判断是否已经存在
        if(houseUserMapper.countItem(houseUserModel.getUserId(),houseUserModel.getHouseId(),houseUserModel.getType())>0){
            return 1;
        }
        return houseUserMapper.insertItem(HouseUserConvert.houseUserCFhouseUserModel(houseUserModel));
    }

    @Override
    public List<HouseModel> likeHouses(long userId) {
        List<HouseUser> list=houseUserMapper.selectLikeHouses(userId);
        List<HouseModel> res=new ArrayList<>(list.size());
        for(HouseUser e:list){
            HouseModel houseModel= HouseConvert.houseModelCFhouse(
                    houseMapper.houseDetail(e.getHouseId()),null,null
            );
            res.add(houseModel);
        }
        return res;
    }
}
