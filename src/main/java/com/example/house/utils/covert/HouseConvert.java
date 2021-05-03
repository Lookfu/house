package com.example.house.utils.covert;


import com.example.house.controller.viewobject.HotHouseVO;
import com.example.house.controller.viewobject.SimpleHotHouseVO;
import com.example.house.entity.House;
import com.example.house.service.model.HotHouseModel;
import com.example.house.service.model.HouseModel;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class HouseConvert {
    public static HouseModel houseModelCFhouse(House house,String cityName,String communityName){
        if(house==null){
            return  null;
        }
        HouseModel houseModel=new HouseModel();
        BeanUtils.copyProperties(house,houseModel);
        houseModel.setCityName(cityName);
        houseModel.setCommunityName(communityName);
        houseModel.setImages(resolveImgs(house.getImages()));
        return  houseModel;
    }

    public static HotHouseModel hotHouseModelCFhouseModel(HouseModel houseModel,Integer hot){
        if (houseModel==null){
            return null;
        }
        HotHouseModel hotHouseModel=new HotHouseModel();
        BeanUtils.copyProperties(houseModel,hotHouseModel);
        hotHouseModel.setHot(hot);
        return hotHouseModel;
    }

    public static SimpleHotHouseVO simpleHotHouseVOCFhotHouseModel(HotHouseModel hotHouseModel){
        if(hotHouseModel==null){
            return null;
        }
        SimpleHotHouseVO simpleHotHouseVO=new SimpleHotHouseVO();
        BeanUtils.copyProperties(hotHouseModel,simpleHotHouseVO);
        return simpleHotHouseVO;
    }

    /**
     *
     * @param imgs
     * @return
     */
    private static List<String> resolveImgs(String imgs){
        String[] a=imgs.split(",");
        List<String> list=new ArrayList(a.length);
        for(String e:a){
            list.add(e);
        }
        return list;
    }
}
