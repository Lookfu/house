package com.example.house.utils.covert;


import com.example.house.constants.HouseConstants;
import com.example.house.controller.viewobject.HotHouseVO;
import com.example.house.controller.viewobject.SimpleHotHouseVO;
import com.example.house.controller.viewobject.SimpleHouseVO;
import com.example.house.entity.House;
import com.example.house.service.model.HotHouseModel;
import com.example.house.service.model.HouseModel;
import com.example.house.validate.entity.HouseVD;
import org.springframework.beans.BeanUtils;
import org.springframework.web.multipart.MultipartFile;

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
        houseModel.setImages(resolveImgs(house.getImages(),house.getId()));
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
        if(hotHouseModel.getImages().size()>0){
            simpleHotHouseVO.setImage(hotHouseModel.getImages().get(0));
        }
        return simpleHotHouseVO;
    }

    public static SimpleHouseVO simpleHouseVOCFhouseModel(HouseModel houseModel){
        if(houseModel==null){
            return null;
        }
        SimpleHouseVO simpleHouseVO=new SimpleHotHouseVO();
        BeanUtils.copyProperties(houseModel,simpleHouseVO);
        if(houseModel.getImages().size()>0){
            simpleHouseVO.setImage(houseModel.getImages().get(0));
        }
        return simpleHouseVO;
    }

    public static House houseCFHouseModel(HouseModel houseModel,Integer cityId,Integer communityId){
        if(houseModel==null){
            return null;
        }
        House house=new House();
        BeanUtils.copyProperties(houseModel,house);
        List<String> images=houseModel.getImages();
        String res=new String();
        for(String e:images){
            res+=e+",";
        }
        res=res.substring(0,res.length()-1);
        house.setImages(res);
        house.setCityId(cityId);
        house.setCommunityId(communityId);
        return house;
    }

    public static HouseModel houseModelCFHouseVD(HouseVD houseVD,Long userId){
        if(houseVD==null){
            return null;
        }
        HouseModel houseModel=new HouseModel();
        BeanUtils.copyProperties(houseVD,houseModel);
        houseModel.setUserId(userId);
        MultipartFile[] files=houseVD.getFiles();
        List<String> images=new LinkedList<>();
        for(int i=1;i<=files.length;i++){
            images.add("pic"+i+".png");
        }
        houseModel.setImages(images);
        return houseModel;
    }

    /**
     *把数据库中字符串的图片地址转换为list类型的
     * @param imgs
     * @return
     */
    private static List<String> resolveImgs(String imgs,Long houseId){
        String[] a=imgs.split(",");
        List<String> list=new ArrayList(a.length);
        for(String e:a){
            list.add(HouseConstants.imgPrePath+houseId+"/"+e);
        }
        return list;
    }
}
