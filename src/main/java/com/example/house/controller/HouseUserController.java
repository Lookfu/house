package com.example.house.controller;

import com.example.house.constants.SessionConstants;
import com.example.house.controller.viewobject.SimpleHouseVO;
import com.example.house.error.BusinessException;
import com.example.house.error.EmBusinessError;
import com.example.house.response.CommonReturnType;
import com.example.house.service.HouseUserService;
import com.example.house.service.model.HouseModel;
import com.example.house.service.model.HouseUserModel;
import com.example.house.service.model.UserModel;
import com.example.house.utils.covert.HouseConvert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("houseUser")
public class HouseUserController {
    @Autowired
    HouseUserService houseUserService;

    @PostMapping("/likeHouse")
    public CommonReturnType likeHouse(HttpServletRequest request,long houseId) throws BusinessException {
        UserModel userModel=(UserModel)request.getSession().getAttribute(SessionConstants.USER);
        HouseUserModel houseUserModel=new HouseUserModel();
        houseUserModel.setHouseId(houseId);
        houseUserModel.setUserId(userModel.getId());
        int res=houseUserService.insertItem(houseUserModel);
        if(res==0){
            throw new BusinessException(EmBusinessError.NORMAL_ERROR.setErrorMsg("收藏失败"));
        }
        return CommonReturnType.create("收藏成功");
    }

    @GetMapping("/myLikeHouses")
    public CommonReturnType myLikeHouses(HttpServletRequest request){
        UserModel userModel=(UserModel)request.getSession().getAttribute(SessionConstants.USER);
        List<HouseModel> list=houseUserService.likeHouses(userModel.getId());
        List<SimpleHouseVO> res=new ArrayList<>(list.size());
        for(HouseModel e:list){
            res.add(HouseConvert.simpleHouseVOCFhouseModel(e));
        }
        return CommonReturnType.create(res);
    }
}
