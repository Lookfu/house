package com.example.house.utils.covert;

import com.example.house.entity.HouseUser;
import com.example.house.service.model.HouseUserModel;
import org.springframework.beans.BeanUtils;

public class HouseUserConvert {

    public static HouseUser houseUserCFhouseUserModel(HouseUserModel houseUserModel){
        if(houseUserModel==null){
            return null;
        }
        HouseUser houseUser=new HouseUser();
        BeanUtils.copyProperties(houseUserModel,houseUser);
        return houseUser;
    }
}
